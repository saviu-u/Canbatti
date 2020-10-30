/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controllers.LoginController;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import static models.DAO.em;
import org.springframework.util.StringUtils;

/**
 *
 * @author savio
 */
@Entity
@Table(name = "tb_pessoa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pessoa.findAll", query = "SELECT p FROM Pessoa p where p.ativo = true")
    , @NamedQuery(name = "Pessoa.findAllPaged", query = "SELECT p FROM Pessoa p where p.ativo = true")
    , @NamedQuery(name = "Pessoa.findAllPagedCount", query = "SELECT COUNT(1) FROM Pessoa p where p.ativo = true")
        
    , @NamedQuery(name = "Pessoa.findByIdPes", query = "SELECT p FROM Pessoa p WHERE p.idPes = ?1")
    , @NamedQuery(name = "Pessoa.findByNomePes", query = "SELECT p FROM Pessoa p WHERE p.nomePes = :nomePes")
    , @NamedQuery(name = "Pessoa.findByCpf", query = "SELECT p FROM Pessoa p WHERE p.cpf = :cpf")
    , @NamedQuery(name = "Pessoa.findByEmail", query = "SELECT p FROM Pessoa p WHERE p.email = :email")
    , @NamedQuery(name = "Pessoa.findByTelefone1", query = "SELECT p FROM Pessoa p WHERE p.telefone1 = :telefone1")
    , @NamedQuery(name = "Pessoa.findByTelefone2", query = "SELECT p FROM Pessoa p WHERE p.telefone2 = :telefone2")
    , @NamedQuery(name = "Pessoa.findBySexo", query = "SELECT p FROM Pessoa p WHERE p.sexo = :sexo")
    , @NamedQuery(name = "Pessoa.findByCustomer", query = "SELECT p FROM Pessoa p WHERE p.customer = :customer")
    , @NamedQuery(name = "Pessoa.findByAtivo", query = "SELECT p FROM Pessoa p WHERE p.ativo = :ativo")

    , @NamedQuery(name = "Pessoa.findByAuth", query = "SELECT p FROM Pessoa p WHERE p.email = :email and p.senha = :senha and p.ativo = true")
    , @NamedQuery(name = "Pessoa.findUniqueness", query = "SELECT p FROM Pessoa p WHERE (p.email = :email or p.cpf = :cpf)") // Required for uniqueness
})
public class Pessoa extends DAO implements Serializable {

    @OneToMany(mappedBy = "idPes")
    private List<Pedidos> pedidosList;
    
    @Override
    protected Integer getPKvalue(){
        return getIdPes();
    }
    
    // Resources columns
    protected String[] getColumns(){
        return new String[] {"nomePes", "email", "cpf", "customer", "idPes"};
    }
    
    // Values with uniqueness validation
    @Override
    protected String[] getUniqueParams(){
        return new String[] {"email", "cpf"};
    }
    
    // Listing possible models
    @Override
    public boolean valid(){
        List<Object> classes = new ArrayList<>();
        if(idEnd != null) classes.add(idEnd);
        return valid(classes);
    }

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "telefone_1")
    private String telefone1;
    @Size(max = 16)
    @Column(name = "telefone_2")
    private String telefone2;

    @JoinColumn(name = "id_end", referencedColumnName = "id_end")
    @OneToOne(cascade = CascadeType.PERSIST)
    private Endereco idEnd;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 44)
    @Column(name = "senha")
    private String senha;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pessoa")
    private Authentication authentication;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pes")
    private Integer idPes;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nome_pes")
    private String nomePes;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Pattern(regexp="([0-9]{3}\\.){2}[0-9]{3}\\-[0-9]{2}", message="invalido")
    @Column(name = "cpf")
    private String cpf;
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "sexo")
    private String sexo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "customer")
    private boolean customer;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ativo")
    private boolean ativo;

    public Pessoa() {
    }

    public Pessoa(Integer idPes) {
        this.idPes = idPes;
    }

    public Pessoa(Integer idPes, String nomePes, String cpf, String email, String telefone1, String sexo, boolean customer, boolean ativo) {
        this.idPes = idPes;
        this.nomePes = nomePes;
        this.cpf = cpf;
        this.email = email;
        this.telefone1 = telefone1;
        this.sexo = sexo;
        this.customer = customer;
        this.ativo = ativo;
    }
    
    public static Pessoa find(Integer idPes){
        return (Pessoa) new Pessoa().genericQuery("Pessoa.findByIdPes", idPes);
    }

    public static Pessoa Auth(String email, String pass){
        Map<Object, Object> params = new HashMap(){{
            put("email", email);
            put("senha", convertPassword(pass));
        }};
        
        return (Pessoa) new Pessoa().genericQuery("Pessoa.findByAuth", params);
    }

    public Integer getIdPes() {
        return idPes;
    }

    public void setIdPes(Integer idPes) {
        this.idPes = idPes;
    }

    public String getNomePes() {
        return nomePes;
    }

    public void setNomePes(String nomePes) {
        this.nomePes = nomePes;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public boolean getCustomer() {
        return customer;
    }

    public void setCustomer(boolean customer) {
        this.customer = customer;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = true;
    }
    
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        if(senha == null) return;
        this.senha = Pessoa.convertPassword(senha);
    }
    
    public Endereco getIdEnd() {
        return idEnd;
    }

    public void setIdEnd(Endereco idEnd) {
        this.idEnd = idEnd;
    }
    
    public static String convertPassword(String pass){
        String password = null;
        try {
            password = Base64.getEncoder().encodeToString(MessageDigest.getInstance("SHA-256").digest(pass.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return password;
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
        if (!(object instanceof Pessoa)) {
            return false;
        }
        Pessoa other = (Pessoa) object;
        if ((this.idPes == null && other.idPes != null) || (this.idPes != null && !this.idPes.equals(other.idPes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controllers.Pessoa[ idPes=" + idPes + " ]";
    }

    public Authentication getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }

    @XmlTransient
    public List<Pedidos> getPedidosList() {
        return pedidosList;
    }

    public void setPedidosList(List<Pedidos> pedidosList) {
        this.pedidosList = pedidosList;
    }
}
