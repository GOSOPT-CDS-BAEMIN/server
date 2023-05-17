package sopt.cds.baemin.exception.model;

import sopt.cds.baemin.exception.Error;

public class ConflictException extends CustomException {
    public ConflictException(Error error, String message) {
        super(error, message);
    }
}
