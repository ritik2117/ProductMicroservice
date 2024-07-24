package com.scaler.productmicroservice.representingInheritance.joinedTable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Data;

@Data
@Inheritance(strategy = InheritanceType.JOINED)
@Entity(name = "jt_user")
public class User {
    @Id
    private Long id;
    private String name;
    private String email;
    private String password;
}
