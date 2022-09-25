package com.dag.homerentwebservice.repository;

import com.dag.homerentwebservice.model.entity.pagecontent.TextField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TextFieldRepository extends JpaRepository<TextField,Integer> {
    List<TextField> findAllByFormId(int formId);
}
