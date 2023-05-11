package com.bedevenamdua.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "messages")
public class Message {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private int id;

    @Column(name = "url")
    private String url;

    @Column(name = "use_case_text")
    private String useCaseText;

    @Column(name = "response_rate")
    private String responseRate;

    @Column(name = "response_time")
    private String responseTime;
}
