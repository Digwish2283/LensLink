package com.lenslink.category.entity;

import com.lenslink.common.entity.BaseEntity;
import com.lenslink.profile.entity.Profile;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "photographer_categories")
public class PhotographerCategory extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}