package com.project.management.projectmanagement.repository;

import com.project.management.projectmanagement.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    public List<Project> findAllByOrderByProjectNameAsc();
    public List<Project> findByStartDateBetweenOrderByEndDateAsc(Date startDate, Date endDate);
}
