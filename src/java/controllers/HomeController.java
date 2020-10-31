/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.*;
import models.Pedidos;
import models.Produtos;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author savio
 */

@Controller
public class HomeController extends ControllerBase {
    private static final String[] ITENS_PARAMS = {"nomeProd", "tipoProd", "descProd", "quantidade", "precoProd"};

    @RequestMapping("/")
    public String index(HttpServletRequest request, HttpServletResponse response){
        if(!LoginController.Authentication(request, response, true)) return "redirect:login";
        
        request.setAttribute("resources", new Produtos().getResources(page(request)));
        request.setAttribute("pageCount", new Produtos().getResourcesCount());
        return "index";
    }
    
    @RequestMapping(value="/", method={RequestMethod.POST})
    public String indexPost(HttpServletRequest request, HttpServletResponse response){
        if(!LoginController.Authentication(request, response, true)) return "redirect:login";
        
        List produtos = new Produtos().genericListQuery("Produtos.findAll");
        Pedidos pedido = new Pedidos();
        Produtos produto = new Produtos();
        formActions(pedido, produto, produtos, request);
        return "redirect:";
    }
    
    private void formActions(Pedidos pedido, Produtos produto, List produtos, HttpServletRequest request){
        BigDecimal total = paramsToObject(produto, produtos, ITENS_PARAMS, request);
        pedido.setItemPedidoCollection(produtos);
        pedido.setPrecoProd(total);
        
        produto.update();
    }
    
    protected BigDecimal paramsToObject(Object object, List produtos, String[] params, HttpServletRequest request){
        BigDecimal total = new BigDecimal(0);
        for (Produtos produto : (List<Produtos>) produtos){
            produto.setQuantidade(request.getParameter(produto.getIdProd().toString()));
            total = total.add(produto.getPrecoProd().multiply(new BigDecimal(produto.getQuantidade())));
        }
        return total;
    }
}
