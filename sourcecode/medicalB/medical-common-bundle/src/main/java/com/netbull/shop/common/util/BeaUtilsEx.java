package com.netbull.shop.common.util;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

public class BeaUtilsEx extends BeanUtils{
    static {   
        ConvertUtils.register(new DateConvert(), java.util.Date.class);   
        ConvertUtils.register(new DateConvert(), java.sql.Date.class);   
    }   
 
   public static void copyProperties(Object dest, Object orig) {   
       try {   
            BeanUtils.copyProperties(dest, orig);   
        } catch (IllegalAccessException ex) {   
            ex.printStackTrace();   
        } catch (InvocationTargetException ex) {   
            ex.printStackTrace();   
        }   
    }   
}

class DateConvert implements Converter {   
	  
    public Object convert(Class arg0, Object arg1) {   
         String p = (String)arg1;   
        if(p== null || p.trim().length()==0){   
            return null;   
         }      
        try{   
             SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
            return df.parse(p.trim());   
         }   
        catch(Exception e){   
            try {   
                 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");   
                return df.parse(p.trim());   
             } catch (ParseException ex) {   
                return null;   
             }   
         }   
           
     }   
  
}  
