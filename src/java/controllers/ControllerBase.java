package controllers;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;


public abstract class ControllerBase {
    protected Integer page(HttpServletRequest request){
        Integer page;
        try{ page = Integer.parseInt(request.getParameter("page")); }
        catch(NumberFormatException e){ page = 0; }
        return page;
    }
    
    protected void paramsToObject(Object object, String[] params, HttpServletRequest request){
        for (String param : params){
            try {
                java.lang.reflect.Method method = object.getClass().getMethod("set" + StringUtils.capitalize(param), String.class);
                String result = request.getParameter(param);
                if(!StringUtils.hasText(result)) result = null;
                method.invoke(object, result);
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    protected void convertAttributes(Map<String, Object> input, HttpServletRequest request){
        input.keySet().forEach((param) -> {
            request.setAttribute(param, input.get(param));
        });
    }
}
