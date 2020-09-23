/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author savio
 */

@Controller
public class CustomerController {
    
    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response){
        return "login";
    }

    @RequestMapping("/")
    public String index(HttpServletRequest request, HttpServletResponse response){
        if(Authentication(request, response)) return "redirect:login";
        
        return "index";
    }
    
    public boolean Authentication(HttpServletRequest request, HttpServletResponse response){
        return true;
    }
}
