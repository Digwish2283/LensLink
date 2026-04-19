package com.lenslink.service.entity;

import com.lenslink.common.entity.BaseEntity;
import com.lenslink.category.entity.Category;
import com.lenslink.profile.entity.Profile;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "services")
public class Service extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String title;
    private String description;

    private Integer price;
    private Integer deliveryDays;

    private boolean isActive;
}