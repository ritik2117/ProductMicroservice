package com.scaler.productmicroservice.representingInheritance.singleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "st_ta")
@DiscriminatorValue(value = "3")
public class TA extends User {
    private int numberOfSessions;
    private double avgRating;
}
