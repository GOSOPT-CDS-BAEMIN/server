package sopt.cds.baemin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sopt.cds.baemin.common.dto.ApiResponse;
import sopt.cds.baemin.exception.Success;
import sopt.cds.baemin.tools.slack.SlackUtil;

@RestController
@RequestMapping("/slack")
@RequiredArgsConstructor
public class SlackController {
    private final SlackUtil slackUtil;

    @PostMapping(value = "/callback")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse callBackButtonClickResult(@RequestParam String payload) {
        slackUtil.doIssueButtonAction(payload);

        return ApiResponse.success(Success.SLACK_ISSUE_PROCESS_SUCCESS);
    }
}
