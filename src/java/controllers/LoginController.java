/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.nio.charset.StandardCharsets;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.*;
import java.util.Base64;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import models.Authentication;
import models.Pessoa;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author savio
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    @RequestMapping(method={RequestMethod.GET})
    public String index(HttpServletRequest request, HttpServletResponse response){
        if(LoginController.Authentication(request, response)) return "redirect:";

        return "login";
    }
    
    @RequestMapping(method={RequestMethod.POST})
    public String indexPost(@RequestParam("email") String email,@RequestParam("pass") String pass, HttpServletRequest request, HttpServletResponse response){
        if(LoginController.Authentication(request, response)) return "redirect:";
        
        Pessoa user = Pessoa.Auth(email, pass);
        if(user.getIdPes() != null){
            Authentication.findByUser(user.getIdPes()).destroy();
            
            String token = Pessoa.convertPassword(pass + email + Long.toString(System.currentTimeMillis()));

            Authentication auth = new Authentication();
            auth.setIdPes(user.getIdPes());
            auth.setCreatedAt(new Date(System.currentTimeMillis()));
            auth.setCookieToken(token);
            if(auth.save()){
                Cookie cookie = new Cookie("Authorization", token);
                response.addCookie(cookie);
                return "redirect:";
            }
            else System.out.println(auth.getErrors());
        }
        else{
            System.out.println("INVALIDO");
        }
        return "login";
    }
    
    public static boolean Authentication(HttpServletRequest request, HttpServletResponse response){
        if(request.getCookies() != null){
            for(Cookie cookie : request.getCookies()){
                if(cookie.getName().equals("Authorization")){
                    return Authentication.findToken(cookie.getValue()).getIdPes() != null;
                }
            }
        }
        return false;
    }
}
