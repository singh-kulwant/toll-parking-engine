package com.kulsin.models.request;

import lombok.Data;

@Data
public class UnparkRequest {

    private String plateNumber;
    private String slotNumber;

}
