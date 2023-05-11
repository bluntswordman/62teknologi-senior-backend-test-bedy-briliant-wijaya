package com.bedevenamdua.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "special_hours")
public class SpecialHour {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "special_hour_id")
    private int id;

    @Column(name = "date_closed")
    private String dateClosed;

    @Column(name = "end_time")
    private int endTime;

    @Column(name = "is_closed")
    private boolean isClosed;

    @Column(name = "is_overnight")
    private boolean isOvernight;

    @Column(name = "start_time")
    private int startTime;
}
