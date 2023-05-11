package com.bedevenamdua.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "photos")
public class Photo {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "photo_id")
    private UUID id;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "caption")
    private String caption;

    @Column(name = "width")
    private double width;

    @Column(name = "height")
    private double height;

    @Column(name = "is_user_submitted")
    private boolean isUserSubmitted;

    @Column(name = "label")
    private String label;
}
