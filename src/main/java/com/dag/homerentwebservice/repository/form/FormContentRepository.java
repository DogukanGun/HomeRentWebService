package com.dag.homerentwebservice.repository.form;

import com.dag.homerentwebservice.model.entity.pagecontent.FormContent;
import com.dag.homerentwebservice.model.enums.FormContentPages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FormContentRepository extends JpaRepository<FormContent,Integer> {
    @Query("SELECT f FROM FormContent f WHERE f.pageName = :pageName")
    Optional<FormContent> checkForm(FormContentPages pageName);
}
