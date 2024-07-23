package com.scaler.productmicroservice.representingInheritance.mappedSuperClass;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "msc_instructor")
public class Instructor extends User {
    private String specialization;
}
