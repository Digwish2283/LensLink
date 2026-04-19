package com.lenslink.profile.entity;

import com.lenslink.common.entity.BaseEntity;
import com.lenslink.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profiles")
public class Profile extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String bio;

    @Column(unique = true)
    private String slug;

    private boolean isVerified;

    private Integer minPrice;
    private Integer maxPrice;
}