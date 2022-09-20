package com.dag.homerentwebservice.model.entity.home;

import com.dag.homerentwebservice.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_home_relation")
public class UserHomeRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "home_id")
    private int homeId;

    @ManyToOne
    @JoinColumn(name = "user_id",insertable = false,updatable = false)
    User user;

    @ManyToOne
    @JoinColumn(name = "home_id",insertable = false,updatable = false)
    Home home;

    String status;
}
