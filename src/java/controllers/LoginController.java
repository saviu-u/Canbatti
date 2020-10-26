/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.lang.reflect.InvocationTargetException;
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
import models.Endereco;
import models.Pessoa;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
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
    private static final String[] USER_PARAMS = {"nomePes", "cpf", "email", "telefone1", "telefone2", "senha"};
    private static final String[] END_PARAMS = {"bairro", "numResidencia", "cidade", "estado", "complemento", "rua"};  
    
    @RequestMapping(method={RequestMethod.GET})
    public String index(HttpServletRequest request, HttpServletResponse response){
        if(LoginController.Authentication(request, response, true)) return "redirect:/";

        return "login";
    }
    
    @RequestMapping(value="/new", method={RequestMethod.GET})
    public String register(HttpServletRequest request, HttpServletResponse response){
        if(LoginController.Authentication(request, response, true)) return "redirect:/";

        return "register";
    }
    
    @RequestMapping(method={RequestMethod.POST})
    public String indexPost(@RequestParam("email") String email,@RequestParam("pass") String pass, HttpServletRequest request, HttpServletResponse response){
        if(LoginController.Authentication(request, response, true)) return "redirect:/";
        
        // Finds person on database
        Pessoa user = Pessoa.Auth(email, pass);
        if(user.getIdPes() != null){
            String redirect = resetTokens(user, response);
            if(redirect != null) return redirect;
        }
        else{
            // Invalid
            request.setAttribute("Erro", true);
        }
        return "login";
    }
    
    @RequestMapping(value="/new", method={RequestMethod.POST})
    public String postRegister(HttpServletRequest request, HttpServletResponse response){
        if(LoginController.Authentication(request, response, true)) return "redirect:/";
        
        Pessoa user = new Pessoa();
        Endereco end = new Endereco();

        for (String param : USER_PARAMS){
            try {
                java.lang.reflect.Method method = user.getClass().getMethod("set" + StringUtils.capitalize(param), String.class);
                String result = request.getParameter(param);
                if(!StringUtils.hasText(result)) result = null;
                method.invoke(user, result);
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        user.setSenha("senha@123");
        user.setSexo("M");
        
        for (String param : END_PARAMS){
            try {
                java.lang.reflect.Method method = end.getClass().getMethod("set" + StringUtils.capitalize(param), String.class);
                String result = request.getParameter(param);
                if(!StringUtils.hasText(result)) result = null;
                method.invoke(end, result);
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        user.setAtivo(true);
        user.setCustomer(true);
        user.setIdEnd(end);
        
        if(user.valid()){
            if(user.save()){
                String redirect = resetTokens(user, response);
                if(redirect != null) return redirect;
            }
        }
        else{
            System.out.println(user.getErrors());
            // Send errors to frontEnd
            request.setAttribute("errors", user.getErrors());
            request.setAttribute("oldParams", request.getParameterMap());
        }

        return "register";
    }
    
    public static boolean Authentication(HttpServletRequest request, HttpServletResponse response, boolean customer){
        if(request.getCookies() != null){
            for(Cookie cookie : request.getCookies()){
                if(cookie.getName().equals("Authorization")){
                    return Authentication.findToken(cookie.getValue(), customer).getIdPes() != null;
                }
            }
        }
        return false;
    }
    
    private String resetTokens(Pessoa user, HttpServletResponse response){
        // Destroy all tokens
        Authentication.findByUser(user.getIdPes()).destroy();

        // Creates a random 
        String token = Pessoa.convertPassword(user.getSenha() + user.getEmail() + Long.toString(System.currentTimeMillis()));

        // Creates a new authentication
        Authentication auth = new Authentication();
        auth.setIdPes(user.getIdPes());
        auth.setCreatedAt(new Date(System.currentTimeMillis()));
        auth.setCookieToken(token);
        // Makes sure it saves
        if(auth.save()){
            // Add cookie to browser
            Cookie cookie = new Cookie("Authorization", token);
            response.addCookie(cookie);

            // Redirects user to right path
            String path = "redirect:";
            if(!user.getCustomer()) path += "admin";
            else path += "/";
            return path;
        }
        else System.out.println(auth.getErrors());
        return null;
    }
}
