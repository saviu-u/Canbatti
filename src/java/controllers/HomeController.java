/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javax.servlet.http.*;
import models.Pessoa;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author savio
 */

@Controller
public class HomeController extends ControllerBase {

    @RequestMapping("/")
    public String index(HttpServletRequest request, HttpServletResponse response){
        if(!LoginController.Authentication(request, response, true)) return "redirect:login";
        
        return "index";
    }
}
