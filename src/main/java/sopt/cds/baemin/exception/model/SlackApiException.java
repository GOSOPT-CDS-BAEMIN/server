package sopt.cds.baemin.exception.model;


import sopt.cds.baemin.exception.Error;

public class SlackApiException extends CustomException {
    public SlackApiException(Error error, String message) {
        super(error, message);
    }
}