package sopt.cds.baemin.tools.slack;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import sopt.cds.baemin.config.SlackConfig;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class SlackHelper {

    private RestTemplate restTemplate = new RestTemplate();
    private final Environment env;

    private final SlackConfig slackConfig;

    public void sendNotification(Exception error) {

        // 현재 프로파일이 set1이 아니면 알림보내지 않기
        if (!env.getActiveProfiles()[0].equals("set1")) {
            return;
        }

        Map<String, Object> payload = new HashMap<>();
        payload.put("username", "🚨 예외 발생 알림이");
        payload.put("text", "서버 측 오류로 예상되는 예외 상황이 발생하였습니다.\n\n" +
                "[🚨 Exception ] \n" + error.toString() + "\n\n" +
                "[💭 From ] \n" + readRootStackTrace(error));
        payload.put("icon_url", "https://yt3.googleusercontent.com/ytc/AGIKgqMVUzRrhoo1gDQcqvPo0PxaJz7e0gqDXT0D78R5VQ=s900-c-k-c0x00ffffff-no-rj");

        restTemplate.postForObject(slackConfig.getWebhookUrl(), payload, String.class);

    }

    private String readRootStackTrace(Exception error) {
        return error.getStackTrace()[0].toString();
    }
}
