/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author savio
 */
@Entity
@Table(name = "tb_item_pedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemPedido.findAll", query = "SELECT i FROM ItemPedido i")
    , @NamedQuery(name = "ItemPedido.findByIdItem", query = "SELECT i FROM ItemPedido i WHERE i.idItem = :idItem")
    , @NamedQuery(name = "ItemPedido.findByQuantidade", query = "SELECT i FROM ItemPedido i WHERE i.quantidade = :quantidade")
    , @NamedQuery(name = "ItemPedido.findByValorTotal", query = "SELECT i FROM ItemPedido i WHERE i.valorTotal = :valorTotal")})
public class ItemPedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_item")
    private Integer idItem;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantidade")
    private int quantidade;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_total")
    private BigDecimal valorTotal;
    @JoinColumn(name = "id_pedido", referencedColumnName = "id_pedido")
    @ManyToOne
    private Pedidos idPedido;
    @JoinColumn(name = "id_prod", referencedColumnName = "id_prod")
    @ManyToOne
    private Produtos idProd;

    public ItemPedido() {
    }

    public ItemPedido(Integer idItem) {
        this.idItem = idItem;
    }

    public ItemPedido(Integer idItem, int quantidade) {
        this.idItem = idItem;
        this.quantidade = quantidade;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Pedidos getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Pedidos idPedido) {
        this.idPedido = idPedido;
    }

    public Produtos getIdProd() {
        return idProd;
    }

    public void setIdProd(Produtos idProd) {
        this.idProd = idProd;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idItem != null ? idItem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemPedido)) {
            return false;
        }
        ItemPedido other = (ItemPedido) object;
        if ((this.idItem == null && other.idItem != null) || (this.idItem != null && !this.idItem.equals(other.idItem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.ItemPedido[ idItem=" + idItem + " ]";
    }
    
}
