package com.dag.homerentwebservice.model.entity.home;
import com.dag.homerentwebservice.model.entity.BaseEntity;
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

    @Column(length = 1350000)
    private String homePhoto;

    private double price;

    private int bedroomCount;

    private String propertyType;

    private double size;

    private String facilities;

    @OneToMany(mappedBy = "home")
    List<UserHomeRelation> users;
}
