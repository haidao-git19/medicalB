package com.netbull.shop.common.util;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;

import com.netbull.shop.common.log.LoggerFactory;
import com.netbull.shop.common.service.LatnService;
import com.netbull.shop.common.vo.Latn;

public class LatnUtil {

    private static final Logger logger = LoggerFactory.getLogger(LatnUtil.class);

    private static LatnService latnService;

    public static void setSystemConfigsService(LatnService latnService) {
        LatnUtil.latnService = latnService;
    }

    /**
     * 初始化latnService
     * 
     * @param cxt void
     * @exception
     * @since 1.0.0
     */
    public static void init(WebApplicationContext cxt) {
        try {
            latnService = (LatnService) cxt.getBean("latnService");
        } catch (Exception e) {
            logger.error("没有实现或配置 地市服务");
        }
    }

    /**
     * 查询所有的地市
     * 
     * @return List<Latn>
     * @exception
     * @since 1.0.0
     */
    public static List<Latn> findAllLatn() {
        Latn latn = new Latn();
        return latnService.findAllLatn(latn);
    }

    public List<Latn> findAllLatns() {
        Latn latn = new Latn();
        return latnService.findAllLatn(latn);
    }

    /**
     * 根据地市Id查询地市
     * 
     * @param latnId
     * @return Latn
     * @exception
     * @since 1.0.0
     */
    public static Latn findLatnById(String latnId) {
        return latnService.finLatnById(latnId);
    }

    /**
     * 根据条件查询所有的地市
     * 
     * @return List<Latn>
     * @exception
     * @since 1.0.0
     */
    public static List<Latn> findAllLatn(Latn latn) {

        return latnService.findAllLatn(latn);
    }
    
    /**
     * 根据条件查询所有的地市
     * 
     * @return List<Latn>
     * @exception
     * @since 1.0.0
     */
    public static Latn findLatnByName(String latnName) {

        return latnService.findLatnByName(latnName);
    }
    
    public static List<Latn> searchOrderLatnByObj(int userId){
    	return latnService.searchOrderLatnByObj(userId);
    }

}
