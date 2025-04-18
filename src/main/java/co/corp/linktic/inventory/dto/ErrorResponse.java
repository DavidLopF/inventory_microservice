package co.corp.linktic.inventory.dto;

import lombok.Data;

@Data
public class ErrorResponse {

    private String message;
    private int status;

    public ErrorResponse() {}

    public ErrorResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }

}
