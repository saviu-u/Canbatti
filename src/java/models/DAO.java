/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    
    public void rollbackConnnection(){
        em.getTransaction().rollback();
        em.close();
        emf.close();
    }
    
    public Map<String, String> getErrors(){
        return errors;
    }
    
    public boolean valid(){
        return valid(new ArrayList<Object>());
    }
    
    public boolean valid(List<Object> entities){
        boolean result = true;
        this.errors = new HashMap();

        entities.add(this);
        
        for(Object obj : entities){
        this.openConnection();
            try{
                em.persist(obj);
            }
            catch (ConstraintViolationException e) {
                e.getConstraintViolations().forEach((cv) -> {
                    errors.put(cv.getPropertyPath().toString(), cv.getMessage());
                });
                result = false;
            }
            catch (Exception e){
                System.out.println(e);
                return false;
            }
        }
        this.rollbackConnnection();
        return result;
    }
    
    public boolean save(){
        this.errors = new HashMap();
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
