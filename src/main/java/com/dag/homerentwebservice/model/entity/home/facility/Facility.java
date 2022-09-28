package com.dag.homerentwebservice.model.entity.home.facility;

import com.dag.homerentwebservice.model.entity.home.Home;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "facility")
@Entity
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "facility_home_id")
    private int homeId;

    @Enumerated(value = EnumType.STRING)
    private FacilityType facilityType;

    @ManyToOne
    @JoinColumn(name = "facility_home_id",insertable = false,nullable = false,updatable = false)
    private Home home;
}
