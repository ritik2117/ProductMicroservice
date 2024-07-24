package com.scaler.productmicroservice.representingInheritance.tablePerClass;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "tpc_ta")
public class TA extends User {
    private int numberOfSessions;
    private double avgRating;
}
