package com.project.management.projectmanagement.service;

import com.project.management.projectmanagement.model.Project;
import com.project.management.projectmanagement.model.User;
import com.project.management.projectmanagement.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProjectService
{
    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> getAll() {
        return projectRepository.findAllByOrderByProjectNameAsc();
    }
    public Project addNew(Project project) {
        project.setStartDate( project.getStartDate() );
        project.setEndDate( project.getEndDate() );
        project.setStatus(project.getStatus());
        //project.getUsers().add(project);
        return projectRepository.save(project);
    }
    public List<Project> searchByDate(Date StartDate, Date EndDate) {
        return  projectRepository.findByStartDateBetweenOrderByEndDateAsc(StartDate,EndDate);
    }
    public Project getById(Long id) {
        return projectRepository.findById(id).get();
    }
    public Project update(Project project) {
        return projectRepository.save( project );
    }
    public Project save(Project project) {
        return projectRepository.save( project );
    }
    public boolean hasUsage(Project project) {
//		return issueService.getCountByMember(user) > 0;
        return false;
    }
    public void delete(Project project) {
        projectRepository.delete(project);
    }
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }
}
