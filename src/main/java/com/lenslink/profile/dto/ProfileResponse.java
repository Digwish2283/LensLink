package com.lenslink.profile.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfileResponse {
    private Long id;
    private String bio;
    private String slug;
    private boolean isVerified;
    private Integer minPrice;
    private Integer maxPrice;
}