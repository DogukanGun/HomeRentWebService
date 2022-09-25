package com.dag.homerentwebservice.repository;

import com.dag.homerentwebservice.model.entity.pagecontent.FormContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormContentRepository extends JpaRepository<FormContent,Integer> { }
