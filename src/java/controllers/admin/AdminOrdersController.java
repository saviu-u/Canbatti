/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.admin;

import javax.servlet.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author savio
 */
@Controller
@RequestMapping("/admin/orders")
public class AdminOrdersController {
    
    @RequestMapping
    public String index(HttpServletRequest request, HttpServletResponse response){
        if(!AdminLoginController.Authentication(request, response)) return "redirect:login";
        
        return "admin/orders";
    }
    
}
