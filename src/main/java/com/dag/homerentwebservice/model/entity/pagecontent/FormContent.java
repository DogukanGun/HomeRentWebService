package com.dag.homerentwebservice.model.entity.pagecontent;
import com.dag.homerentwebservice.model.entity.BaseEntity;
import com.dag.homerentwebservice.model.enums.FormContentPages;
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
@Table(name = "formcontents")
@Entity
@SuperBuilder
@SQLDelete(sql="Update formcontents SET is_deleted = true where id = ?")
@Where(clause = "is_deleted=false")
public class FormContent extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(value = EnumType.STRING)
    private FormContentPages pageName;

    private String title;

    @OneToMany(mappedBy = "formContent")
    private List<TextField> textFields;
}
