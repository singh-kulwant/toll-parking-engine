package com.kulsin.models.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenerateBillResponse {

    private Long billAmount;

}
