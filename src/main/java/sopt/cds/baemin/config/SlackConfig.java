package sopt.cds.baemin.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SlackConfig {

    @Value("${slack.webhook.url}")
    private String webhookUrl;

    @Value("${slack.channel.id}")
    private String channelId;

    public String getWebhookUrl() {
        return webhookUrl;
    }

    public String getChannelId() {
        return channelId;
    }
}
