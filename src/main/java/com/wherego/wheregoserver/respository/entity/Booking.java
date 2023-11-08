package com.wherego.wheregoserver.respository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="booking")
public class Booking {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="booking_id")
    private Long id;

    @Column(name="booking_book_date", nullable = false)
    private Date bookDate;

    @Column(name="booking_price", nullable = false)
    private Long price;

    @Column(name="booking_people", nullable = false)
    private int people;

    @Column(name="booking_checkin", nullable = false)
    private Date checkin;

    @Column(name="booking_checkout", nullable = false)
    private Date checkout;

    @ManyToOne
    @JoinColumn(name="traverler_id", nullable = false)
    private Traveler traveler;

    @ManyToOne
    @JoinColumn(name="hotel_id", nullable = false)
    private Hotel hotel;

}
