package com.su.shorterUrl.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="url")
public class Url implements Serializable {

    @Id
    @Column(name="idUrl")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUrl;
    @Column(name="url")
    private String url;
    @Column(name="shortCode")
    private String shortCode;
    @Column(name="createdAt")
    private LocalDateTime createdAt;
    @Column(name="updatedAt")
    private LocalDateTime updatedAt;

}
