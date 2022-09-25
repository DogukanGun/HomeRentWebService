package com.dag.homerentwebservice.model.entity.pagecontent;

import com.dag.homerentwebservice.model.enums.TextFieldType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "textfields")
@Entity
public class TextField {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private TextFieldType type;

    private String hint;

    private int formId;

    @ManyToOne(targetEntity = FormContent.class)
    @JoinColumn(name = "form_conten_id",insertable = false,updatable = false)
    private FormContent formContent;


}
