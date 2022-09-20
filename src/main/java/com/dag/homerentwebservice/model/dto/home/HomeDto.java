

package com.dag.homerentwebservice.model.dto.home;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HomeDto {

    private Integer id;

    private String homeName;

    @Column(length = 1350000)
    private String homePhoto;

    private double price;

    private int bedroomCount;

    private String propertyType;

    private double size;

    private String facilities;

}
