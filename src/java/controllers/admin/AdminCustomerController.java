/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.admin;

import controllers.LoginController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author savio
 */
@Controller
@RequestMapping("/admin/customer")
public class AdminCustomerController {
    @RequestMapping
    public String index(HttpServletRequest request, HttpServletResponse response){
        if(!LoginController.Authentication(request, response, false)) return "redirect:../login";
        
        return "admin/customer/index";
    }
    
    @RequestMapping(value="/new", method={RequestMethod.GET})
    public String newGetAction(HttpServletRequest request, HttpServletResponse response){
        if(!LoginController.Authentication(request, response, false)) return "redirect:../login";
        
        return "admin/customer/form";
    }
    
    @RequestMapping(value="/new", method={RequestMethod.POST})
    public String newPostAction(HttpServletRequest request, HttpServletResponse response){
        if(!LoginController.Authentication(request, response, false)) return "redirect:../login";
        
        return "admin/customer/form";
    }
    
    @RequestMapping(value="/#{id}", method={RequestMethod.GET})
    public String editGetAction(HttpServletRequest request, HttpServletResponse response){
        if(!LoginController.Authentication(request, response, false)) return "redirect:../login";
        
        return "admin/customer/form";
    }
    
    @RequestMapping(value="/#{id}", method={RequestMethod.PUT})
    public String editPutAction(HttpServletRequest request, HttpServletResponse response){
        if(!LoginController.Authentication(request, response, false)) return "redirect:../login";
        
        return "admin/customer/form";
    }
    
    @RequestMapping(value="/#{id}", method={RequestMethod.DELETE})
    public String delete(HttpServletRequest request, HttpServletResponse response){
        if(!LoginController.Authentication(request, response, false)) return "redirect:../login";
        
        return "admin/customer/form";
    }
}
