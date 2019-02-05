package it.youthclub.exception;

public class DecodeException extends RuntimeException {
    private int code;
    private String cause;
    public DecodeException(int code,String cause){
        super(cause);
        this.code=code;
        this.cause=cause;
    }


}
