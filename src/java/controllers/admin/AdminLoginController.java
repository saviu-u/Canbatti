/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.admin;

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
@RequestMapping("/admin/login")
public class AdminLoginController {
    @RequestMapping(method={RequestMethod.GET})
    public String index(HttpServletRequest request, HttpServletResponse response){
        if(AdminLoginController.Authentication(request, response)) return "redirect:";
        
        return "admin/login";
    }
    
    public static boolean Authentication(HttpServletRequest request, HttpServletResponse response){
        return true;
    }
}
