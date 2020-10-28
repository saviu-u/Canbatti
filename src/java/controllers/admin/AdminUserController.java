/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.admin;

import controllers.ControllerBase;
import controllers.LoginController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Endereco;
import models.Pessoa;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author savio
 */
@Controller
@RequestMapping("/admin/user")
public class AdminUserController extends ControllerBase {
    private static final String[] USER_PARAMS = {"nomePes", "cpf", "email", "telefone1", "telefone2", "senha"};
    private static final String[] END_PARAMS = {"bairro", "numResidencia", "cidade", "estado", "complemento", "rua"};  
    
    @RequestMapping
    public String index(HttpServletRequest request, HttpServletResponse response){
        if(!LoginController.Authentication(request, response, false)) return "redirect:../login";
        
        request.setAttribute("resources", new Pessoa().getResources(page(request), true));
        request.setAttribute("pageCount", new Pessoa().getResourcesCount(true));
        System.out.println(new Pessoa().getResources(page(request), true));
        System.out.println(new Pessoa().getResourcesCount(true));
        
        return "admin/user/index";
    }
    
    @RequestMapping(value="/new", method={RequestMethod.GET})
    public String newGetAction(HttpServletRequest request, HttpServletResponse response){
        if(!LoginController.Authentication(request, response, false)) return "redirect:../login";
        
        return "admin/user/form";
    }
    
    @RequestMapping(value="/new", method={RequestMethod.POST})
    public String newPostAction(HttpServletRequest request, HttpServletResponse response){
        if(!LoginController.Authentication(request, response, false)) return "redirect:../login";
        if(formActions(request)) return "redirect:";
        
        return "admin/user/form";
    }
    
    @RequestMapping(value="/#{id}", method={RequestMethod.GET})
    public String editGetAction(HttpServletRequest request, HttpServletResponse response){
        if(!LoginController.Authentication(request, response, false)) return "redirect:../login";
        
        return "admin/user/form";
    }
    
    @RequestMapping(value="/#{id}", method={RequestMethod.PUT})
    public String editPutAction(HttpServletRequest request, HttpServletResponse response){
        if(!LoginController.Authentication(request, response, false)) return "redirect:../login";
        
        return "admin/user/form";
    }
    
    @RequestMapping(value="/#{id}", method={RequestMethod.DELETE})
    public String delete(HttpServletRequest request, HttpServletResponse response){
        if(!LoginController.Authentication(request, response, false)) return "redirect:../login";
        
        return "admin/user/form";
    }
    
    private boolean formActions(HttpServletRequest request){
        Pessoa pessoa = new Pessoa();
        Endereco end = new Endereco();
        paramsToObject(pessoa, USER_PARAMS, request);
        paramsToObject(end, END_PARAMS, request);
        
        if(pessoa.save()){
            return true;
        }
        else{
            request.setAttribute("errors", pessoa.getErrors());
            request.setAttribute("oldParams", request.getParameterMap());
        }
        return false;
    }
}
