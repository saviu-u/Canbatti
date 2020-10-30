/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.admin;

import controllers.ControllerBase;
import controllers.LoginController;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Endereco;
import models.Pessoa;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author savio
 */
@Controller
@RequestMapping("/admin/user")
public class AdminUserController extends ControllerBase {
    private static final String[] USER_PARAMS = {"nomePes", "cpf", "sexo", "email", "telefone1", "telefone2", "senha"};
    private static final String[] END_PARAMS = {"bairro", "numResidencia", "cidade", "estado", "complemento", "rua"};  
    
    @RequestMapping
    public String index(HttpServletRequest request, HttpServletResponse response){
        if(!LoginController.Authentication(request, response, false)) return "redirect:../login";
        
        request.setAttribute("resources", new Pessoa().getResources(page(request)));
        request.setAttribute("pageCount", new Pessoa().getResourcesCount());
        System.out.println(new Pessoa().getResources(page(request)));
        System.out.println(new Pessoa().getResourcesCount());
        
        return "admin/user/index";
    }
    
    @RequestMapping(value="/new", method={RequestMethod.GET})
    public String newGetAction(HttpServletRequest request, HttpServletResponse response){
        if(!LoginController.Authentication(request, response, false)) return "redirect:../login";
        
        return "register";
    }
    
    @RequestMapping(value="/new", method={RequestMethod.POST})
    public String newPostAction(HttpServletRequest request, HttpServletResponse response){
        if(!LoginController.Authentication(request, response, false)) return "redirect:../login";
        Pessoa pessoa = new Pessoa();
        pessoa.setCustomer(false);
        if(formActions(pessoa, new Endereco(), request)) return "redirect:";
        
        return "register";
    }
    
    @RequestMapping(value="/{id}", method={RequestMethod.GET})
    public String editGetAction(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response){
        if(!LoginController.Authentication(request, response, false)) return "redirect:../login";
        Pessoa user = Pessoa.find(id);
        Map<String, Object> oldParams = user.getAttributes();
        oldParams.remove("senha");
        convertAttributes(oldParams, request);
        
        return "register";
    }
    
    @RequestMapping(value="/{id}", method={RequestMethod.POST})
    public String editPutAction(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response){
        if(!LoginController.Authentication(request, response, false)) return "redirect:../login";
        Pessoa pessoa = Pessoa.find(id);
        pessoa.setCustomer(false);
        if(formActions(pessoa, new Endereco(), request)) return "redirect:";
        
        return "register";
    }
    
    @RequestMapping(value="/{id}", method={RequestMethod.DELETE})
    public String delete(HttpServletRequest request, HttpServletResponse response){
        if(!LoginController.Authentication(request, response, false)) return "redirect:../login";
        
        return "admin/user/form";
    }
    
    private boolean formActions(Pessoa pessoa, Endereco end, HttpServletRequest request){
        paramsToObject(pessoa, USER_PARAMS, request);
        paramsToObject(end, END_PARAMS, request);
        
        if(pessoa.save())
            return true;
        else{
            System.out.println(pessoa.getErrors());
            request.setAttribute("errors", pessoa.getErrors());
        }
        return false;
    }
}
