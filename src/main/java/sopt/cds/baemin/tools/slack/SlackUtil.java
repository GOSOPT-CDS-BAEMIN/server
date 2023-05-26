package sopt.cds.baemin.tools.slack;

import com.slack.api.Slack;
import com.slack.api.model.block.Blocks;
import com.slack.api.model.block.LayoutBlock;
import com.slack.api.model.block.composition.BlockCompositions;
import com.slack.api.model.block.element.BlockElement;
import com.slack.api.model.block.element.BlockElements;
import com.slack.api.webhook.WebhookPayloads;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import sopt.cds.baemin.config.SlackConfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.slack.api.model.block.composition.BlockCompositions.plainText;

@Component
@RequiredArgsConstructor
@Slf4j
public class SlackUtil {

    private RestTemplate restTemplate = new RestTemplate();
    private final Environment env;

    private final SlackConfig slackConfig;

    private List<BlockElement> getActionBlocks() {
        List<BlockElement> actions = new ArrayList<>();
        actions.add(getActionButton("담당하기", "takeError", "primary", "action_takeError"));
        actions.add(getActionButton("처리완료", "isDone", "primary", "action_isDone"));
        return actions;
    }

    public void process(String message, Exception error) throws IOException {

        // 현재 프로파일이 set1이 아니면 알림보내지 않기
        if (!env.getActiveProfiles()[0].equals("set1")) {
            return;
        }

        List<LayoutBlock> layoutBlocks = Blocks.asBlocks(
                getHeader("서버 측 오류로 예상되는 예외 상황이 발생하였습니다."),
                Blocks.divider(),
                getSection(generateErrorMessage(error) + message),
                Blocks.divider(),
                Blocks.actions(getActionBlocks())
        );

        Slack.getInstance().send(slackConfig.getWebhookUrl(), WebhookPayloads
                .payload(p -> p.username("🚨 예외 발생 알림이")
                        .iconUrl("https://yt3.googleusercontent.com/ytc/AGIKgqMVUzRrhoo1gDQcqvPo0PxaJz7e0gqDXT0D78R5VQ=s900-c-k-c0x00ffffff-no-rj")
                        .blocks(layoutBlocks)));
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
}
