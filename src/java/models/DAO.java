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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
                    java.lang.reflect.Method queryParamMethod = tempQuery.getClass().getMethod("setParameter", String.class, Object.class);
                    for(String param : getUniqueParams()){
                        java.lang.reflect.Method objectAttrMethod = obj.getClass().getMethod("get" + StringUtils.capitalize(param));
                        tempQuery = (TypedQuery) queryParamMethod.invoke(tempQuery, param, objectAttrMethod.invoke(obj));
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
    
    protected String[] getUniqueParams(){
        return null;
    }
}
