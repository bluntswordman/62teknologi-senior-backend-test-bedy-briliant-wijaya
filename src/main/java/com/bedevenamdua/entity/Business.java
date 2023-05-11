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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_coordinate_id", referencedColumnName = "coordinate_id")
    private Coordinate coordinate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_location_id", referencedColumnName = "location_id")
    private Location location;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_business_id", referencedColumnName = "business_id")
    private List<Transaction> transactions;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "business_category",
            joinColumns = @JoinColumn(name = "fk_business_id"),
            inverseJoinColumns = @JoinColumn(name = "fk_category_id")
    )
    private List<Category> categories;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_attributes_id", referencedColumnName = "attributes_id")
    private Attributes attributes;
}
