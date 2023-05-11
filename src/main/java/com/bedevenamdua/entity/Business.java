package com.bedevenamdua.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "businesses")
public class Business {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "business_id")
    private UUID id;

    @Column(name = "alias")
    private String alias;

    @Column(name = "display_phone")
    private String displayPhone;

    @Column(name = "distance")
    private double distance;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "is_closed")
    private boolean isClosed;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "price")
    private long price;

    @Column(name = "rating")
    private double rating;

    @Column(name = "review_count")
    private long reviewCount;

    @Column(name = "url")
    private String url;

    @Column(name = "is_claimed")
    private boolean isClaimed;

    @Column(name = "date_opened")
    private String dateOpened;

    @Column(name = "date_closed")
    private String dateClosed;

    @Column(name = "yelp_menu_url")
    private String yelpMenuUrl;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_coordinate_id", referencedColumnName = "coordinate_id")
    private Coordinate coordinate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_location_id", referencedColumnName = "location_id")
    private Location location;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_business_id", referencedColumnName = "business_id")
    private List<Transaction> transactions;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_business_id", referencedColumnName = "business_id")
    private List<Category> categories;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_attribute_id", referencedColumnName = "attribute_id")
    private Attribute attribute;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_business_id", referencedColumnName = "business_id")
    private List<Photo> photos;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_business_id", referencedColumnName = "business_id")
    private List<SpecialHour> specialHours;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_message_id", referencedColumnName = "message_id")
    private Message message;
}
