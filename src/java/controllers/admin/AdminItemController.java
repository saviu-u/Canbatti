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
import models.Produtos;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author savio
 */
@Controller
@RequestMapping("/admin/item")
public class AdminItemController extends ControllerBase {
    private static final String[] ITENS_PARAMS = {"nomeProd", "tipoProd", "descProd", "quantidade", "precoProd"};

    @RequestMapping
    public String index(HttpServletRequest request, HttpServletResponse response){
        if(!LoginController.Authentication(request, response, false)) return "redirect:/login";
        
        request.setAttribute("resources", new Produtos().getResources(page(request)));
        request.setAttribute("pageCount", new Produtos().getResourcesCount());
        System.out.println(new Produtos().getResources(page(request)));
        System.out.println(new Produtos().getResourcesCount());
        
        return "admin/items/produtos";
    }
    
    @RequestMapping(value="/new", method={RequestMethod.GET})
    public String newGetAction(HttpServletRequest request, HttpServletResponse response){
        if(!LoginController.Authentication(request, response, false)) return "redirect:/login";
        
        return "admin/items/produtoNew";
    }
    
    @RequestMapping(value="/new", method={RequestMethod.POST})
    public String newPostAction(HttpServletRequest request, HttpServletResponse response){
        if(!LoginController.Authentication(request, response, false)) return "redirect:/login";
        Produtos produto = new Produtos();
        if(formActions(produto, request)) return "redirect:";
        
        return "admin/items/produtoNew";
    }
    
    @RequestMapping(value="/{id}", method={RequestMethod.GET})
    public String editGetAction(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response){
        if(!LoginController.Authentication(request, response, false)) return "redirect:/login";
        Produtos produto = Produtos.find(id);
        Map<String, Object> oldParams = produto.getAttributes();
        convertAttributes(oldParams, request);
        System.out.println(oldParams);
        
        return "admin/items/produtoNew";
    }
    
    @RequestMapping(value="/{id}", method={RequestMethod.POST})
    public String editPutAction(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response){
        if(!LoginController.Authentication(request, response, false)) return "redirect:/login";
        Produtos produto = Produtos.find(id);
        if(formActions(produto, request)) return "redirect:";
        
        return "admin/items/produtoNew";
    }
    
    @RequestMapping(value="/#{id}", method={RequestMethod.DELETE})
    public String delete(HttpServletRequest request, HttpServletResponse response){
        if(!LoginController.Authentication(request, response, false)) return "redirect:/login";
        
        return "admin/items/form";
    }
    
    private boolean formActions(Produtos produto, HttpServletRequest request){
        paramsToObject(produto, ITENS_PARAMS, request);
        
        if(produto.update())
            return true;
        else
            request.setAttribute("errors", produto.getErrors());
        System.out.println(produto.getErrors());
        return false;
    }
}
