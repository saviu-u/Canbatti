/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author savio
 */
@Entity
@Table(name = "tb_pedidos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedidos.findAll", query = "SELECT p FROM Pedidos p")
    , @NamedQuery(name = "Pedidos.findAllPaged", query = "SELECT p FROM Pedidos p ORDER BY p.statusPed")
    , @NamedQuery(name = "Pedidos.findByIdPes", query = "SELECT p FROM Pedidos p WHERE p.idPes = ?1 ORDER BY p.statusPed")
    , @NamedQuery(name = "Pedidos.findByIdPedido", query = "SELECT p FROM Pedidos p WHERE p.idPedido = ?1")
    , @NamedQuery(name = "Pedidos.findByDataPed", query = "SELECT p FROM Pedidos p WHERE p.dataPed = :dataPed")
    , @NamedQuery(name = "Pedidos.findByStatusPed", query = "SELECT p FROM Pedidos p WHERE p.statusPed = :statusPed")
    , @NamedQuery(name = "Pedidos.findByPrecoProd", query = "SELECT p FROM Pedidos p WHERE p.precoProd = :precoProd")})
public class Pedidos extends DAO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pedido")
    private Integer idPedido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_ped")
    @Temporal(TemporalType.DATE)
    private Date dataPed;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "status_ped")
    private String statusPed;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "preco_prod")
    private BigDecimal precoProd;
    @JoinColumn(name = "id_pes", referencedColumnName = "id_pes")
    @ManyToOne
    private Pessoa idPes;
    @OneToMany(mappedBy = "idPedido")
    private List<ItemPedido> itemPedidoCollection;
    
    @Override
    protected String[] getColumns(){
        return new String[] {"idPedido", "readableDate", "precoProd", "status", "nomePes"};
    }
    
    public String getNomePes(){
        return getIdPes().getNomePes();
    }
    
    public String getReadableDate(){
        return new SimpleDateFormat("dd/MM/yyyy").format(dataPed);
    }
    
    public String getStatus(){
        Map<String, String> convertionMap = new HashMap(){{
            put("A", "Em andamento");
            put("B", "Finalizado");
        }};
        
        return convertionMap.get(getStatusPed());
    }

    public Pedidos() {
    }

    public Pedidos(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Pedidos(Integer idPedido, Date dataPed, String statusPed) {
        this.idPedido = idPedido;
        this.dataPed = dataPed;
        this.statusPed = statusPed;
    }
    
    public static Pedidos find(Integer idPed){
        return (Pedidos) new Pedidos().genericQuery("Pedidos.findByIdPedido", idPed);
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Date getDataPed() {
        return dataPed;
    }

    public void setDataPed(Date dataPed) {
        this.dataPed = dataPed;
    }

    public String getStatusPed() {
        return statusPed;
    }

    public void setStatusPed(String statusPed) {
        this.statusPed = statusPed;
    }

    public BigDecimal getPrecoProd() {
        return precoProd;
    }

    public void setPrecoProd(BigDecimal precoProd) {
        this.precoProd = precoProd;
    }

    public Pessoa getIdPes() {
        return idPes;
    }

    public void setIdPes(Pessoa idPes) {
        this.idPes = idPes;
    }

    @XmlTransient
    public List<ItemPedido> getItemPedidoCollection() {
        return itemPedidoCollection;
    }

    public void setItemPedidoCollection(List<ItemPedido> itemPedidoCollection) {
        this.itemPedidoCollection = itemPedidoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPedido != null ? idPedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedidos)) {
            return false;
        }
        Pedidos other = (Pedidos) object;
        if ((this.idPedido == null && other.idPedido != null) || (this.idPedido != null && !this.idPedido.equals(other.idPedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Pedidos[ idPedido=" + idPedido + " ]";
    }
    
}
