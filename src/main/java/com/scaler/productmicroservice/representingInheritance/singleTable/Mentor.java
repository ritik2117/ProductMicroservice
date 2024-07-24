package com.scaler.productmicroservice.representingInheritance.singleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "st_mentor")
@DiscriminatorValue(value = "2")
public class Mentor extends User {
    private double mentorRating;
}
