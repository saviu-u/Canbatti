/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;
import org.springframework.util.StringUtils;

/**
 *
 * @author savio
 */
public class DAO {
    protected static EntityManagerFactory emf;
    protected static EntityManager em;
    Map<String, String> errors = new HashMap();
    
    private final Integer LIMIT = 12;
    private final String UNIQUE_MESSAGE = "já existe";
    
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
        this.rollbackConnnection();
        this.openConnection();
            if(((DAO) obj).getUniqueParams() != null){
                try{
                    Map<java.lang.reflect.Method, String> paramDif = new HashMap<>();
                    TypedQuery tempQuery = em.createNamedQuery(obj.getClass().getSimpleName() + ".findUniqueness", obj.getClass());
                    for(String param : getUniqueParams()){
                        java.lang.reflect.Method objectAttrMethod = obj.getClass().getMethod("get" + StringUtils.capitalize(param));
                        tempQuery = tempQuery.setParameter(param, objectAttrMethod.invoke(obj));
                        paramDif.put(objectAttrMethod, param);
                    }
                    
                    for(Object instance : tempQuery.getResultList()){
                        for(java.lang.reflect.Method method : paramDif.keySet()){
                            Object objValue = method.invoke(this);
                            Object insValue = method.invoke(instance);
                            if(objValue != null && objValue.equals(insValue)) {
                                errors.put(paramDif.get(method), UNIQUE_MESSAGE);
                                result = false;
                            }
                        }
                    }
                }
                catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException e){
                    System.out.println(e);
                }
            }
        }
        this.rollbackConnnection();
        return result;
    }
    
    public boolean save(){
        this.errors = new HashMap();
        if(valid()){
            this.openConnection();
            em.persist(this);
            this.closeConnnection();
            return true;
        }
        return false;
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
    
    public Integer getResourcesCount(Object... args){
        Integer result;

        this.openConnection();
        Query tempQuery = em.createNamedQuery(this.getClass().getSimpleName() + ".findAllPagedCount");
        for(Object arg : args) tempQuery = tempQuery.setParameter(1, arg);
        result = ((Long) tempQuery.getSingleResult()).intValue();
        this.closeConnnection();

        return result;
    }
    
    public List<Map<String, String>> getResources(Integer page, Object... args){
        if(getColumns() == null){
            System.out.println("Define the columns first");
            return null;
        }
        
        this.openConnection();
        TypedQuery tempQuery = em.createNamedQuery(this.getClass().getSimpleName() + ".findAllPaged", this.getClass());
        for(Object arg : args) tempQuery = tempQuery.setParameter(1, arg);
        
        Integer offset = (page - 1) * LIMIT;
        tempQuery.setFirstResult(page);
        tempQuery.setMaxResults(LIMIT);
        
        Map<java.lang.reflect.Method, String> paramDif = new HashMap<>();
        for(String param : getColumns()){
            try {
                java.lang.reflect.Method method = this.getClass().getMethod("get" + StringUtils.capitalize(param));
                paramDif.put(method, param);
            } catch (NoSuchMethodException | SecurityException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        List<Map<String, String>> result = new ArrayList();
        for(Object object : tempQuery.getResultList()){
            Map<String, String> objectHash = new HashMap();
            for(java.lang.reflect.Method method : paramDif.keySet()){
                try {
                    objectHash.put(paramDif.get(method), (String) method.invoke(object));
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            result.add(objectHash);
        }

        this.closeConnnection();
        return result;
    }


    
    protected String[] getColumns(){
        return null;
    }
    
    protected String[] getUniqueParams(){
        return null;
    }
}
