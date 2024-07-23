package com.scaler.productmicroservice.representingInheritance.mappedSuperClass;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "msc_mentor")
public class Mentor extends User {
    private double mentorRating;
}
