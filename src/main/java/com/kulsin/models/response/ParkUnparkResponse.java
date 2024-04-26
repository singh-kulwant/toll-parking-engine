package com.kulsin.models.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParkUnparkResponse {

    private boolean success;
    private String message;

}