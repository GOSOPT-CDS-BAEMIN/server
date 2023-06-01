package sopt.cds.baemin.tools.slack;

import com.slack.api.Slack;
import com.slack.api.app_backend.interactive_components.ActionResponseSender;
import com.slack.api.app_backend.interactive_components.payload.BlockActionPayload;
import com.slack.api.app_backend.interactive_components.response.ActionResponse;
import com.slack.api.model.block.Blocks;
import com.slack.api.model.block.LayoutBlock;
import com.slack.api.model.block.composition.BlockCompositions;
import com.slack.api.model.block.element.BlockElement;
import com.slack.api.model.block.element.BlockElements;
import com.slack.api.util.json.GsonFactory;
import com.slack.api.webhook.WebhookPayloads;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import sopt.cds.baemin.config.SlackConfig;
import sopt.cds.baemin.exception.model.SlackApiException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.slack.api.model.block.Blocks.section;
import static com.slack.api.model.block.composition.BlockCompositions.markdownText;
import static com.slack.api.model.block.composition.BlockCompositions.plainText;
import static sopt.cds.baemin.exception.Error.SLACK_API_CONNECTING_ERROR;

@Component
@RequiredArgsConstructor
@Slf4j
public class SlackUtil {

    private final Environment env;
    private final SlackConfig slackConfig;

    private List<BlockElement> getActionBlocks() {
        List<BlockElement> actions = new ArrayList<>();
        actions.add(getActionButton("담당하기", "takeIssue", "primary", "action_takeIssue"));
        actions.add(getActionButton("처리완료", "clearIssue", "primary", "action_clearIssue"));
        return actions;
    }

    public void sendAlert(Exception error) throws IOException {

        // 현재 프로파일이 set1이 아니면 알림보내지 않기
        if (!env.getActiveProfiles()[0].equals("set1")) {
            return;
        }

        List<LayoutBlock> layoutBlocks = generateLayoutBlock(error);

        Slack.getInstance().send(slackConfig.getWebhookUrl(), WebhookPayloads
                .payload(p -> p.username("🚨 예외 발생 알림이")
                        .iconUrl("https://yt3.googleusercontent.com/ytc/AGIKgqMVUzRrhoo1gDQcqvPo0PxaJz7e0gqDXT0D78R5VQ=s900-c-k-c0x00ffffff-no-rj")
                        .blocks(layoutBlocks)));
    }

    private List<LayoutBlock> generateLayoutBlock(Exception error) {
        return Blocks.asBlocks(
                getHeader("서버 측 오류로 예상되는 예외 상황이 발생하였습니다."),
                Blocks.divider(),
                getSection(generateErrorMessage(error)),
                Blocks.divider(),
                Blocks.actions(getActionBlocks()),
                getSection("<https://github.com/GOSOPT-CDS-BAEMIN/server/issues|이슈 생성하러 가기>")
        );
    }

    private String generateErrorMessage(Exception error) {
        return "[⚠️ Exception] \n" + error.toString() + "\n\n" +
                "[📌 From] \n" + readRootStackTrace(error) + "\n\n";
    }

    private String readRootStackTrace(Exception error) {
        return error.getStackTrace()[0].toString();
    }

    private LayoutBlock getHeader(String text) {
        return Blocks.header(h -> h.text(
                plainText(pt -> pt.emoji(true)
                        .text(text))));
    }

    private LayoutBlock getSection(String message) {
        return Blocks.section(s ->
                s.text(BlockCompositions.markdownText(message)));
    }

    private BlockElement getActionButton(String plainText, String value, String style, String actionId) {
        return BlockElements.button(b -> b.text(plainText(plainText, true))
                .value(value)
                .style(style)
                .actionId(actionId));
    }

    // HTTPS 환경에서만 작동
    public void doIssueButtonAction(String payload) {
        
        // Json String -> BlockActionPayload 변경
        BlockActionPayload blockActionPayload = changeJsonToBlockActionPayload(payload);

        String userName = blockActionPayload.getUser().getName();

        // Block 수정
        blockActionPayload.getMessage().getBlocks().remove(0);
        blockActionPayload.getActions().forEach(action -> {

            if (action.getActionId().equals("action_takeIssue")) {
                blockActionPayload.getMessage().getBlocks().add(0,
                        section(section ->
                                section.text(markdownText("담당자: " + userName))
                        )
                );
            } else if (action.getActionId().equals("action_clearIssue")) {
                blockActionPayload.getMessage().getBlocks().add(0,
                        section(section ->
                                section.text(markdownText("해결: " + userName))
                        )
                );
            }
        });

        ActionResponse response = getActionResponse(blockActionPayload);

        Slack slack = Slack.getInstance();
        ActionResponseSender sender = new ActionResponseSender(slack);
        try {
            sender.send(blockActionPayload.getResponseUrl(), response);
        } catch (IOException error) {
            throw new SlackApiException(SLACK_API_CONNECTING_ERROR, SLACK_API_CONNECTING_ERROR.getMessage());
        }
    }

    private ActionResponse getActionResponse(BlockActionPayload blockActionPayload) {
        return ActionResponse.builder()
                .replaceOriginal(true)
                .blocks(blockActionPayload.getMessage().getBlocks())
                .build();
    }

    private BlockActionPayload changeJsonToBlockActionPayload(String payload) {
        return GsonFactory.createSnakeCase()
                .fromJson(payload, BlockActionPayload.class);
    }
}
