package com.lenslink.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServiceResponse {
    private Long id;
    private String title;
    private String description;
    private Integer price;
    private Integer deliveryDays;
}