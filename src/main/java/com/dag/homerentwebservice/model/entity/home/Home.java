package com.dag.homerentwebservice.model.entity.home;
import com.dag.homerentwebservice.model.entity.BaseEntity;
import com.dag.homerentwebservice.model.entity.account.LandlordAccount;
import com.dag.homerentwebservice.model.entity.home.facility.Facility;
import com.dag.homerentwebservice.model.enums.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "homes")
@Entity
@SuperBuilder
@SQLDelete(sql="Update homes SET is_deleted = true where id = ?")
@Where(clause = "is_deleted=false")
public class Home extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String homeName;

    private double price;

    private int bedroomCount;

    @Enumerated(value = EnumType.STRING)
    private PropertyType propertyType;

    private double size;

    private boolean waterBillIncluded;

    private boolean electricityBillIncluded;

    private boolean autoPay;

    private String message;

    @OneToOne(mappedBy = "home")
    LandlordAccount landlordAccount;

    @OneToMany(mappedBy = "home")
    List<HomeImage> homeImages;

    @OneToMany(mappedBy = "home")
    List<UserHomeRelation> users;

    @OneToMany(mappedBy = "home")
    List<Facility> facilities;
}
