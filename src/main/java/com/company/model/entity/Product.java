package com.company.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String name;

    private double price;

    private String description;

    @Column(columnDefinition = "TEXT")
    private String imageUrl;

    @Column(name = "category_id")
    private int categoryId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private Category category;

//    @Column(name = "user_id")
//    private int userId;

//    @ManyToOne
//    @JoinColumn(name = "user_id", insertable = false, updatable = false)
//    private UserEntity user;


    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;


    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;
}
