package co.corp.linktic.inventory.dto;

import lombok.Data;

@Data
public class ResponseService<T> {
    private T data;
}
