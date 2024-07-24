package com.scaler.productmicroservice.representingInheritance.tablePerClass;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "tpc_instructor")
public class Instructor extends User {
    private String specialization;
}
