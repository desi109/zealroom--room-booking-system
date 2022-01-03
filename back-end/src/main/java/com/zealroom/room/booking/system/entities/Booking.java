package com.zealroom.room.booking.system.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @Column(name = "booking_uuid", nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "room_uuid", nullable = false)
    private Room room;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_uuid", nullable = false)
    private User userUuid;

    @Column(name = "is_booked", nullable = false)
    private Boolean isBooked = false;

    @Column(name = "check_in", nullable = false)
    private LocalDateTime checkIn;

    @Column(name = "check_out", nullable = false)
    private LocalDateTime checkOut;

    public LocalDateTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDateTime checkOut) {
        this.checkOut = checkOut;
    }

    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDateTime checkIn) {
        this.checkIn = checkIn;
    }

    public Boolean getIsBooked() {
        return isBooked;
    }

    public void setIsBooked(Boolean isBooked) {
        this.isBooked = isBooked;
    }

    public User getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(User userUuid) {
        this.userUuid = userUuid;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}