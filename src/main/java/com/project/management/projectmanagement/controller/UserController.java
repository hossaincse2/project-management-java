package com.project.management.projectmanagement.controller;

import com.project.management.projectmanagement.common.Constants;
import com.project.management.projectmanagement.model.User;
import com.project.management.projectmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping( value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ModelAttribute(name = "userRoles")
    public List<String> userRoles (){
        return Constants.USER_ROLES;
    }

    @RequestMapping(value = {"/","/list"}, method = RequestMethod.GET)
    public String showUsersPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addUserPage(Model model) {
        model.addAttribute("user", new User());
        return "user/form";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editUserPage(@PathVariable(name = "id") Long id, Model model) {
        User user = userService.getById( id );
        if( user != null ) {
            model.addAttribute("user", user);
            return "/user/form";
        } else {
            return "redirect:/user/add";
        }
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if( bindingResult.hasErrors() ) {
            return "/user/form";
        }

        if( user.getId() == null ) {
            userService.addNew(user);
            redirectAttributes.addFlashAttribute("successMsg", "'" + user.getDisplayName() + "' is added as a new member.");
            return "redirect:/user/add";
        } else {
            User updatedMember = userService.save( user );
            redirectAttributes.addFlashAttribute("successMsg", "Changes for '" + user.getDisplayName() + "' are saved successfully. ");
            return "redirect:/user/edit/" + updatedMember.getId();
        }
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public String removeUser(@PathVariable(name = "id") Long id, Model model) {
        User user = userService.getById( id );
        if( user != null ) {
            if( userService.hasUsage(user) ) {
                model.addAttribute("memberInUse", true);
                return showUsersPage(model);
            } else {
                userService.delete(id);
            }
        }
        return "redirect:/user/list";
    }
}
