package com.lenslink.portfolio.entity;

import com.lenslink.common.entity.BaseEntity;
import com.lenslink.profile.entity.Profile;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "portfolio")
public class Portfolio extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    private String mediaUrl;

    private boolean isFeatured;
}