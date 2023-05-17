package sopt.cds.baemin.exception.model;


import sopt.cds.baemin.exception.Error;

public class NotFoundException extends CustomException {
    public NotFoundException(Error error, String message) {
        super(error, message);
    }
}