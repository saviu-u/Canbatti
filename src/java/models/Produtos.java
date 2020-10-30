/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author savio
 */
@Entity
@Table(name = "tb_produtos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produtos.findAll", query = "SELECT p FROM Produtos p")
    , @NamedQuery(name = "Produtos.findAllPaged", query = "SELECT p FROM Produtos p")
    , @NamedQuery(name = "Produtos.findAllPagedCount", query = "SELECT COUNT(1) FROM Produtos p")
    , @NamedQuery(name = "Produtos.findByIdProd", query = "SELECT p FROM Produtos p WHERE p.idProd = :idProd")

    , @NamedQuery(name = "Produtos.findByNomeProd", query = "SELECT p FROM Produtos p WHERE p.nomeProd = :nomeProd")
    , @NamedQuery(name = "Produtos.findByTipoProd", query = "SELECT p FROM Produtos p WHERE p.tipoProd = :tipoProd")
    , @NamedQuery(name = "Produtos.findByDescProd", query = "SELECT p FROM Produtos p WHERE p.descProd = :descProd")
    , @NamedQuery(name = "Produtos.findByPrecoProd", query = "SELECT p FROM Produtos p WHERE p.precoProd = :precoProd")
    , @NamedQuery(name = "Produtos.findByQuantidade", query = "SELECT p FROM Produtos p WHERE p.quantidade = :quantidade")})
public class Produtos extends DAO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_prod")
    private Integer idProd;
    @Size(max = 30)
    @NotNull
    @Column(name = "nome_prod")
    private String nomeProd;
    @Size(max = 25)
    @NotNull
    @Column(name = "tipo_prod")
    @Pattern(regexp="\\A(Pão|Carne|Acompanhamento|Molho|Bebida|)\\Z", message="invalido")
    private String tipoProd;
    @Size(max = 2147483647)
    @Column(name = "desc_prod")
    private String descProd;
    // @Max(value=?) 
    @Min(value=0)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "preco_prod")
    private BigDecimal precoProd;
    @Basic(optional = false)
    @Column(name = "quantidade")
    @NotNull
    private Integer quantidade;
    @OneToMany(mappedBy = "idProd")
    private Collection<ItemPedido> itemPedidoCollection;
    
    // Resources columns
    protected String[] getColumns(){
        return new String[] {"nomeProd", "descProd", "tipoProd", "quantidade", "idProd"};
    }
    
    public static Produtos find(Integer idProd){
        return (Produtos) new Produtos().genericQuery("Produtos.findByIdProd", idProd);
    }

    public Produtos() {
    }

    public Produtos(Integer idProd) {
        this.idProd = idProd;
    }

    public Integer getIdProd() {
        return idProd;
    }

    public void setIdProd(Integer idProd) {
        this.idProd = idProd;
    }

    public String getNomeProd() {
        return nomeProd;
    }

    public void setNomeProd(String nomeProd) {
        this.nomeProd = nomeProd;
    }

    public String getTipoProd() {
        return tipoProd;
    }

    public void setTipoProd(String tipoProd) {
        this.tipoProd = tipoProd;
    }

    public String getDescProd() {
        return descProd;
    }

    public void setDescProd(String descProd) {
        this.descProd = descProd;
    }

    public BigDecimal getPrecoProd() {
        return precoProd;
    }

    public void setPrecoProd(BigDecimal precoProd) {
        this.precoProd = precoProd;
    }
    
    public void setPrecoProd(String precoProd) {
        this.precoProd = new BigDecimal(precoProd);
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    
    public void setQuantidade(String quantidade) {
        this.quantidade = Integer.parseInt(quantidade);
    }

    @XmlTransient
    public Collection<ItemPedido> getItemPedidoCollection() {
        return itemPedidoCollection;
    }

    public void setItemPedidoCollection(Collection<ItemPedido> itemPedidoCollection) {
        this.itemPedidoCollection = itemPedidoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProd != null ? idProd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produtos)) {
            return false;
        }
        Produtos other = (Produtos) object;
        if ((this.idProd == null && other.idProd != null) || (this.idProd != null && !this.idProd.equals(other.idProd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Produtos[ idProd=" + idProd + " ]";
    }
    
}
