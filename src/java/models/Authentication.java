/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import static models.DAO.em;
import static models.Pessoa.convertPassword;

/**
 *
 * @author savio
 */
@Entity
@Table(name = "tb_auth")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Authentication.findAll", query = "SELECT a FROM Authentication a")
    , @NamedQuery(name = "Authentication.findByIdPes", query = "SELECT a FROM Authentication a WHERE a.idPes = :idPes")
    , @NamedQuery(name = "Authentication.findByCookieToken", query = "SELECT a FROM Authentication a WHERE a.cookieToken = :cookieToken and now() < a.created_at + interval '30 minutes'")
    , @NamedQuery(name = "Authentication.findByCreatedAt", query = "SELECT a FROM Authentication a WHERE a.createdAt = :createdAt")
})
public class Authentication extends DAO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_pes")
    private Integer idPes;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "cookie_token")
    private String cookieToken;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @JoinColumn(name = "id_pes", referencedColumnName = "id_pes", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Pessoa pessoa;
    
    public static Authentication findByUser(Integer id){
        return findGeneric("Authentication.findByIdPes", "idPes", id);
    }
    public static Authentication findToken(String token){
        return findGeneric("Authentication.findByCookieToken", "cookieToken", token);
    }

    private static Authentication findGeneric(String query, String parameter, Object id){
        Authentication result;
        DAO dao = new DAO();
        dao.openConnection();
        try{
            result = em.createNamedQuery(query, Authentication.class).setParameter(parameter, id).getSingleResult();
        }
        catch (NoResultException e){
            result = new Authentication();
        }
        return result;
    }

    public Authentication() {
    }

    public Authentication(Integer idPes) {
        this.idPes = idPes;
    }

    public Authentication(Integer idPes, String cookieToken) {
        this.idPes = idPes;
        this.cookieToken = cookieToken;
    }

    public Integer getIdPes() {
        return idPes;
    }

    public void setIdPes(Integer idPes) {
        this.idPes = idPes;
    }

    public String getCookieToken() {
        return cookieToken;
    }

    public void setCookieToken(String cookieToken) {
        this.cookieToken = cookieToken;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPes != null ? idPes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Authentication)) {
            return false;
        }
        Authentication other = (Authentication) object;
        if ((this.idPes == null && other.idPes != null) || (this.idPes != null && !this.idPes.equals(other.idPes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Authentication[ idPes=" + idPes + " ]";
    }
    
}
