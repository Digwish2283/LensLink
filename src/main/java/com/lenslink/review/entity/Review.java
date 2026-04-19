package com.lenslink.review.entity;

import com.lenslink.common.entity.BaseEntity;
import com.lenslink.booking.entity.Booking;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reviews")
public class Review extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "booking_id", unique = true)
    private Booking booking;

    private Integer rating;

    private String comment;
}