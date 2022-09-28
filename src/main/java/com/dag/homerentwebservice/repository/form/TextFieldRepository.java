package com.dag.homerentwebservice.repository.form;

import com.dag.homerentwebservice.model.entity.pagecontent.FormContent;
import com.dag.homerentwebservice.model.entity.pagecontent.TextField;
import com.dag.homerentwebservice.model.enums.FormContentPages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TextFieldRepository extends JpaRepository<TextField,Integer> {
    @Query("SELECT f FROM TextField f WHERE f.formContent.id = :formId")
    List<TextField> getAllTextfieldsById(int formId);
}
