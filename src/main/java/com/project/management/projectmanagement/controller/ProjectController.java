package com.project.management.projectmanagement.controller;

import com.project.management.projectmanagement.common.Constants;
import com.project.management.projectmanagement.model.Project;
import com.project.management.projectmanagement.model.User;
import com.project.management.projectmanagement.service.ProjectService;
import com.project.management.projectmanagement.service.ReportService;
import com.project.management.projectmanagement.service.UserService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private UserService userService;

    @ModelAttribute(name = "projectStatus")
    public List<String> projectStatus(){
        return Constants.PROJECT_STATUS;
    }

    @RequestMapping(value = {"/","/list"}, method = RequestMethod.GET)
    public String showProjectPage(Model model, @RequestParam(value = "startDate",required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, @RequestParam(value = "endDate",required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        if (startDate != null){
            model.addAttribute("projects", projectService.searchByDate(startDate,endDate));
        }else{
            model.addAttribute("projects", projectService.getAll());
        }
        return "project/list";
    }

//    @RequestMapping(value ="/report", method = RequestMethod.GET)
//    public String generateReport(@RequestParam(value = "format",required = false) String format) throws JRException, IOException {
//        String fileLink = reportService.generateReport(format);
//        return "redirect:/"+fileLink;
//    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addProjectPage(Model model){
        model.addAttribute("project",new Project());
        model.addAttribute("users",userService.getAllActiveUsers());
        return "project/form";
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProject(@Valid Project project, @RequestParam Map<String, String> payload, BindingResult bindingResult, final RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            return "project/form";
        }

        if (project.getId() == null){
//            User user = new User();
            projectService.addNew( project );
            redirectAttributes.addFlashAttribute("successMsg","'" + project.getProjectName() + "' is added as a new member.");
            return "redirect:/project/add";
        }else{
            Project updatedProject = projectService.save( project );
            redirectAttributes.addFlashAttribute("successMsg", "Changes for '" + project.getProjectName() + "' are saved successfully. ");
            return "redirect:/project/edit/" + updatedProject.getId();
        }

    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editUserPage(@PathVariable(name = "id") Long id, Model model) {
        Project project = projectService.getById( id );
        if( project != null ) {
            model.addAttribute("project", project);
            model.addAttribute("users",userService.getAllActiveUsers());
            return "/project/form";
        } else {
            return "redirect:/project/add";
        }
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public String removeProject(@PathVariable(name = "id") Long id, Model model) {
        Project project = projectService.getById( id );
        if( project != null ) {
            if( projectService.hasUsage(project) ) {
                model.addAttribute("memberInUse", true);
                return showProjectPage(model,null,null);
            } else {
                projectService.delete(id);
            }
        }
        return "redirect:/project/list";
    }
}
