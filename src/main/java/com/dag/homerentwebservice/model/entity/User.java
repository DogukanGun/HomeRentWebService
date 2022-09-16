package com.dag.homerentwebservice.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Entity
@SuperBuilder
@SQLDelete(sql="Update users SET is_deleted = true where id = ?")
@Where(clause = "is_deleted=false")
public class User extends BaseEntity{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    private String name;

    private String surname;

    @Column(unique = true)
    private String username;

    private String password;

    private String email;

    private String phoneNumber;

    private String userType;

    @Column(length = 1350000)
    private String userPhoto;
}
