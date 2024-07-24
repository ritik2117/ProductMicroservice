package com.scaler.productmicroservice.representingInheritance.singleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

import javax.management.DescriptorKey;

@Data
@Entity(name = "st_instructor")
@DiscriminatorValue(value = "1")
public class Instructor extends User {
    private String specialization;
}
