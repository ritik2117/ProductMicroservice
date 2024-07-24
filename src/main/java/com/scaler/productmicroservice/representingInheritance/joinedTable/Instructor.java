package com.scaler.productmicroservice.representingInheritance.joinedTable;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;

@Data
@Entity(name = "jt_instructor")
@PrimaryKeyJoinColumn(name = "user_id")
public class Instructor extends User {
    private String specialization;
}
