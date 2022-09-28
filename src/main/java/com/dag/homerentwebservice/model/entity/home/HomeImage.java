package com.dag.homerentwebservice.model.entity.home;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "home_images")
public class HomeImage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "home_id")
    private Integer homeId;

    @ManyToOne
    @JoinColumn(name = "home_id",insertable = false,nullable = false,updatable = false)
    private Home home;

    @Column(length = 1350000)
    private String image;
}
