/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author savio
 */
public class DAO {
    protected static EntityManagerFactory emf;
    protected static EntityManager em;
    Map<String, String> errors = new HashMap();
    
    public void openConnection(){
        emf = Persistence.createEntityManagerFactory("CanbattiPU");
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }
    
    public void closeConnnection(){
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
    
    public Map<String, String> getErrors(){
        return errors;
    }
    
    public boolean save(){
        this.openConnection();
        try{
            em.persist(this);
        }
        catch (ConstraintViolationException e) {
            System.out.println("Validation errors");
            e.getConstraintViolations().forEach((cv) -> {
                errors.put(cv.getPropertyPath().toString(), cv.getMessage());
            });
            return false;
        }
        catch (Exception e){
            errors.put("Erro:", e.getMessage());
            return false;
        }
        this.closeConnnection();
        return true;
    }
    
    public boolean destroy(){
        this.openConnection();
        try{
            em.remove(em.merge(this));
        }
        catch (ConstraintViolationException e) {
            System.out.println("Validation errors");
            e.getConstraintViolations().forEach((cv) -> {
                errors.put(cv.getPropertyPath().toString(), cv.getMessage());
            });
            return false;
        }
        catch (Exception e){
            errors.put("Erro:", e.getMessage());
            return false;
        }
        this.closeConnnection();
        return true;
    }
}
