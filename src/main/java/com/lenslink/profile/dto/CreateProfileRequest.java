package com.lenslink.profile.dto;

import lombok.Data;

@Data
public class CreateProfileRequest {
    private String bio;
    private String slug;
    private Integer minPrice;
    private Integer maxPrice;
}