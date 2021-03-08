package kg.nurtelecom.grpc.models;

import lombok.Getter;

@Getter
public class ErrorModel {
    String error;
    String stackTrace;

    public ErrorModel(Exception e){
        this.error=e.getMessage();
        this.stackTrace=e.getStackTrace()[0].toString();
    }
}
