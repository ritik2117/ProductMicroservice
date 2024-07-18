package com.scaler.productmicroservice.models;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Category extends BaseModel {
    private String title;
    private String description;
}
