package com.test.meetroomBooking.exception;

public class ErrorCode {
    public static final String INTEGRITY_VIOLATION = "I-01";
    public static final String MEETROOM_NOT_FOUND = "I-02";
    public static final String BAD_REQUEST = "I-03";
    public static final String DUPLICATED_MEETROOM= "I-04";
    public static final String MEETROOM_RESERVED_IN_PERIOD = "I-05";
    public static final String NO_VALID_DATA = "I-06";
    public static final String MEETROOMBOOKING_NOT_FOUND = "I-07";

    private ErrorCode(){
        throw new IllegalStateException("Utility class");
    }
}
