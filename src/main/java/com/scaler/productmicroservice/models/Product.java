package com.scaler.productmicroservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Product extends BaseModel {
    private String title;
    private String description;
    private double price;
    private String image;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id")
    private Category category;
    private int qty;
}

/**
 * 1      ->         1
 * Product -------- Category ==> M:1
 *   M          <-     1
 * ==========================
 *    M               1
 *
 *   1                 M
 * Movie ------------ Actor ==> M:M
 *   M                  1
 */
