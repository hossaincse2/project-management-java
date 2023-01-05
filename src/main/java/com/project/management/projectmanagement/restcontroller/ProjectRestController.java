package com.project.management.projectmanagement.restcontroller;

import com.project.management.projectmanagement.model.Project;
import com.project.management.projectmanagement.model.User;
import com.project.management.projectmanagement.service.ProjectService;
import com.project.management.projectmanagement.service.ReportService;
import com.project.management.projectmanagement.service.UserService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

@RestController
//@RequestMapping(value = "/api/v1/project")
public class ProjectRestController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReportService reportService;

    @GetMapping(value = "/api/v1/projects")
    public List <Project> allProjects(){
        //return  projectService.getAll();
        //finds all the products
        List<Project> products = projectService.getAll();
        return products;
    }
    @GetMapping(value = "/api/v1/users/{id}")
    public User UserDataById(@PathVariable(value = "id") Long id){
            return userService.getById(id);
    }
    @GetMapping(value = "/api/v1/user-by-user-name/{username}")
    public User UserDataByUserName(@PathVariable(value = "username") String username){
            return userService.getByUsername(username);
    }

}
