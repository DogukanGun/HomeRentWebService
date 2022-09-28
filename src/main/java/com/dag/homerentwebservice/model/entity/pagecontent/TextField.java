package com.dag.homerentwebservice.model.entity.pagecontent;

import com.dag.homerentwebservice.model.entity.BaseEntity;
import com.dag.homerentwebservice.model.enums.TextFieldType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "textfields")
@Entity
@SuperBuilder
@SQLDelete(sql="Update formcontents SET is_deleted = true where id = ?")
@Where(clause = "is_deleted=false")
public class TextField extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private TextFieldType type;

    private String title;

    private String hint;

    @Column(name = "model_key")
    private String key;

    @Column(name = "form_content_id")
    private int form_content_id;

    @ManyToOne(targetEntity = FormContent.class)
    @JoinColumn(name = "form_content_id",insertable = false,updatable = false)
    private FormContent formContent;


}
