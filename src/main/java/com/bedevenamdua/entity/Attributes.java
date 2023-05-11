package com.bedevenamdua.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "attributes")
public class Attributes {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attributes_id")
    private int id;

    @Column(name = "business_temp_closed")
    private int businessTempClosed;

    @Column(name = "outdoor_seating")
    private boolean outdoorSeating;

    @Column(name = "liked_by_vegans")
    private boolean likedByVegans;

    @Column(name = "liked_by_vegetarians")
    private boolean likedByVegetarians;

    @Column(name = "hot_and_new")
    private String hotAndNew;
}
