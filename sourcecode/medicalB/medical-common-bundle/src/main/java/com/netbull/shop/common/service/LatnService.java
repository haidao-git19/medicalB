package com.netbull.shop.common.service;

import java.util.List;

import com.netbull.shop.common.dao.EntityManager;
import com.netbull.shop.common.vo.Latn;

public interface LatnService  extends EntityManager<Latn, String>{

    /**   
     * 根据条件获取到地市 
     * @param latn
     * @return    
     *List<Latn>   
     * @exception    
     * @since  1.0.0   
    */
    List<Latn> findAllLatn(Latn latn);
    
    /**   
     * 根据地市编码获取地市对象
     * @param lantId
     * @return    
     *Latn   
     * @exception    
     * @since  1.0.0   
    */
    Latn finLatnById(String lantId);
    
    Latn findLatnByName(String latnName); 
    
    List<Latn> searchOrderLatnByObj(int userId);

}
