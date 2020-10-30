/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.Collection;
import java.util.List;
import javax.servlet.http.*;
import models.Produtos;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author savio
 */

@Controller
public class HomeController extends ControllerBase {
    private static final String[] ITENS_PARAMS = {"nomeProd", "tipoProd", "descProd", "quantidade", "precoProd"};

//    @RequestMapping("/")
//    public String index(HttpServletRequest request, HttpServletResponse response){
//        if(!LoginController.Authentication(request, response, true)) return "redirect:login";
//        
//        request.setAttribute("resources", new Produtos().getResources(page(request)));
//        request.setAttribute("pageCount", new Produtos().getResourcesCount());
//        System.out.println(new Produtos().getResources(page(request)));
//        System.out.println(new Produtos().getResourcesCount());
//        System.out.println(request.getRemoteHost());
//        System.out.println(request.getRequestURI());
//        System.out.println(request.getRequestURL());
//        System.out.println(request.getServletPath());
//        
//        System.out.println(request.getRequestURI() + request.getContextPath());
//        
//        return "index";
//    }
//    
    @RequestMapping(value="/", method={RequestMethod.GET})
    public String indexPost(HttpServletRequest request, HttpServletResponse response){
        if(!LoginController.Authentication(request, response, true)) return "redirect:login";
        
        List produtos = new Produtos().genericListQuery("Pedidos.findAll");
        System.out.println(produtos);
        Produtos produto = new Produtos();
//        if(formActions(produto, request)) return "redirect:";
        
        return "index";
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
