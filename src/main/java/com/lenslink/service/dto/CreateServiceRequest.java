package com.lenslink.service.dto;

import lombok.Data;

@Data
public class CreateServiceRequest {
    private Long categoryId;
    private String title;
    private String description;
    private Integer price;
    private Integer deliveryDays;
}