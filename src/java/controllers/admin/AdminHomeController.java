/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.admin;

import controllers.ControllerBase;
import controllers.LoginController;
import java.util.Map;
import javax.servlet.http.*;
import models.Pedidos;
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
@RequestMapping("/admin")
public class AdminHomeController extends ControllerBase {
    
    @RequestMapping
    public String index(HttpServletRequest request, HttpServletResponse response){
        if(!LoginController.Authentication(request, response, false)) return "redirect:../login";
        request.setAttribute("resources", new Pedidos().getResources(page(request)));
        System.out.println(new Pedidos().getResources(page(request)));
        
        return "admin/index";
    }
    
    @RequestMapping(value="/{id}", method={RequestMethod.GET})
    public String editGetAction(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response){
        if(!LoginController.Authentication(request, response, false)) return "redirect:/login";
        Pedidos pedido = Pedidos.find(id);
        pedido.setStatusPed("B");
        
        pedido.update();
        
        return "redirect:";
    }
    
}
