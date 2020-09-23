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
@RequestMapping("/admin")
public class HelloWorld {
    
    @RequestMapping("/teste")
    public String teste(HttpServletRequest request, HttpServletResponse response){
        System.out.println("******");
        System.out.println(request.getCookies().length);
        Cookie cookie = new Cookie("teste", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "index";
    }
    
}
