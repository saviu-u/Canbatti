/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.*;
import models.ItemPedido;
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
        System.out.println(new Produtos().getResources(page(request)));
        return "index";
    }
    
    @RequestMapping(value="/", method={RequestMethod.POST})
    public String indexPost(HttpServletRequest request, HttpServletResponse response){
        if(!LoginController.Authentication(request, response, true)) return "redirect:login";
        
        List produtos = new Produtos().genericListQuery("Produtos.findAll");
        Pedidos pedido = new Pedidos();
        formActions(pedido, produtos, request);
        return "redirect:";
    }
    
    private void formActions(Pedidos pedido, List produtos, HttpServletRequest request){
        BigDecimal total = paramsToObject(pedido, produtos, request);
        pedido.setPrecoProd(total);
        pedido.setDataPed(new Date(System.currentTimeMillis()));
        pedido.setStatusPed("A");
        pedido.setIdPes(getUser(request));
        
        pedido.update();
    }
    
    protected BigDecimal paramsToObject(Pedidos pedido, List produtos, HttpServletRequest request){
        List<ItemPedido> itemPedidos = new ArrayList();
        BigDecimal total = new BigDecimal(0);
        for (Produtos produto : (List<Produtos>) produtos){
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setIdProd(produto);
            itemPedido.setQuantidade(request.getParameter(produto.getIdProd().toString()));
            itemPedido.setIdPedido(pedido);
            
            if(itemPedido.getQuantidade() == 0) continue;
            
            produto.setQuantidade(produto.getQuantidade() - itemPedido.getQuantidade());
            produto.update();
            
            BigDecimal valor = produto.getPrecoProd().multiply(new BigDecimal(itemPedido.getQuantidade()));

            itemPedido.setValorTotal(valor);
            itemPedidos.add(itemPedido);
            
            total = total.add(valor);
        }

        pedido.setItemPedidoCollection(itemPedidos);
        return total;
    }
}
