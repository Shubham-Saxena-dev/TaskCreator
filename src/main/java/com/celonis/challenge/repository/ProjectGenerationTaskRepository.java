package com.celonis.challenge.repository;

import com.celonis.challenge.model.ProjectGenerationTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProjectGenerationTaskRepository extends JpaRepository<ProjectGenerationTask, String> {

  /*  @Query("SELECT creationDate FROM ProjectGenerationTask pgt WHERE pgt.creationDate > :date")
    List<ProjectGenerationTask> findByCreationDateGreaterThan(Date date);*/

    List<ProjectGenerationTask> findByCreationDateBefore(Date weekAgo);

}
