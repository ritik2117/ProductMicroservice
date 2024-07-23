package com.scaler.productmicroservice.representingInheritance.mappedSuperClass;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "msc_ta")
public class TA extends User {
    private int numberOfSessions;
    private double avgRating;
}
