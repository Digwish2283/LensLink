package com.lenslink.booking.entity;

import com.lenslink.common.entity.BaseEntity;
import com.lenslink.booking.enums.BookingStatus;
import com.lenslink.service.entity.Service;
import com.lenslink.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bookings")
public class Booking extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    private String requirements;
}