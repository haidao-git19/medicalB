package com.netbull.shop.common.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.netbull.shop.common.vo.Page;

/**
 * 服务层实现类
 * @author zhouyang
 * 
 * @param <T>
 * @param <PK>
 */
@SuppressWarnings("rawtypes")
public class EntityManagerImpl<T, PK extends Serializable> implements EntityManager<T, PK> {

	protected EntityIbaDao<T, PK> entityDao;
	
	public EntityManagerImpl() {
        
    }

	public EntityManagerImpl(EntityIbaDao<T, PK> entityDao) {
		this.entityDao = entityDao;
	}

	public void setEntityDao(EntityIbaDao<T, PK> entityDao) {
		this.entityDao = entityDao;
	}

	public boolean exists(String statementName, PK id) {
		return entityDao.exists(statementName,id);
	}

	/**
     * 根据参数条件查询
     * 
     * @param statementName 查询sql语句id
     * @param map 参数值map
     */
	public List find(String statementName, Map map) {
		return entityDao.find(statementName, map);
	}
	
	/**
     * 根据参数条件查询
     * 
     * @param statementName 查询sql语句id
     * @param obj 参数值
     */
    public List find(String statementName, Object obj) {
        return entityDao.find(statementName, obj);
    }
	
	@SuppressWarnings("unchecked")
	public List<T> findBy(String statementName, Map map){
		
		return entityDao.find(statementName, map);
	}

	public List<T> findBy(String statementName, Object value) {
		return entityDao.findBy(statementName, value);
	}

	/**
     * 根据ID获取对象
     * @param statementName sql语句id
     * @param id 对象主键
     * @return 获取对象
     */
	public T findById(String statementName, PK id) {
	    
		return entityDao.findById(statementName, id);
	}
	
	/**
     * 根据参数获取对象
     * @param statementName sql语句id
     * @param obj 参数对象
     * @return 获取对象
     */
    public T findObj(String statementName, T obj){
        
        return (T)entityDao.findObj(statementName, obj);
    }
    
    /**
     * 根据参数获取对象
     * @param statementName sql语句id
     * @param parameterMap 参数map
     * @return 获取对象
     */
    public T findObj(String statementName, Map parameterMap) {
        
        return (T)entityDao.findObj(statementName, parameterMap);
    }

	public List<T> findAll(String statementName) {
		return entityDao.findAll(statementName);
	}
	
	public List findAllObj(String statementName) {
		return entityDao.findAll(statementName);
	}

	/**
     * 分页查询函数
     * 
     * @param countStatementName 查询总个数sql的id
     * @param statementName 查询分页数据的sql的id
     * @param countParameterMap 查询总个数需要的参数
     * @param queryParameterMap 查询分页数据需要的参数
     * @param page 分页对象
     * @return 对象集的分页结果
     */
	public List pagedQuery(String countStatementName, String statementName, 
	        Map countParameterMap, Map queryParameterMap, Page pager) {
	    
		return entityDao.pagedQuery(countStatementName, statementName, 
				countParameterMap, queryParameterMap, pager);
	}
	
	/**
     * 分页查询函数
     * 
     * @param countStatementName 查询总个数sql的id
     * @param statementName 查询分页数据的sql的id
     * @param paramClass 参数对象值
     * @param page 分页对象
     * @return 对象集的分页结果
     */
    public List pagedQuery(String countStatementName, String statementName, 
            Object paramClass, Page pager) {
        
        return entityDao.pagedQuery(countStatementName, statementName, paramClass, pager);
    }

	/**
     * 删除对象
     * @param statementName sql语句id
     */
	public void remove(String statementName) {
		entityDao.remove(statementName);
	}
	
	/**
     * 删除对象或集合  
     * @param statementName sql语句id
     * @param parameterMap  参数值map
     * @exception    
     * @since  1.0.0
     */
	public void remove(String statementName, Map parameterMap){
		entityDao.remove(statementName, parameterMap);
	}

	   /**
     * 根据ID删除对象
     * 
     * @param id 将删除的对象的ID
     */
	public void removeById(String statementName, PK id) {
		entityDao.removeById(statementName, id);
	}
	
	/**
     * 删除对象或集合  
     * @param statementName sql语句id
     * @param obj  参数值
     * @exception    
     * @since  1.0.0
     */
    public void remove(String statementName, Object obj) {
        entityDao.remove(statementName, obj);
    }

	/**
     * 保存对象
     * @param statementName sql语句id
     * @param obj 需要保存的对象
     */
	public void save(String statementName, Object obj) {
		entityDao.save(statementName, obj);
	}
	
	/**
     * 更新对象
     * @param statementName sql语句id
     * @param obj 需要更新的对象
     */
	public void update(String statementName, Object obj){
		entityDao.update(statementName, obj);
	}
	
	/**
	 * 返回对象类型
	 * @param procedureName
	 * @param map
	 * @return
	 */
	public Object findObject(String statementName, Object o){
		return entityDao.findObject(statementName, o);
	}
	
	public void executeProcedure(String procedureName, Map map){
		entityDao.executeProcedure(procedureName, map);
	}
	

	public EntityIbaDao<T, PK> getEntityDao() {
		return entityDao;
	}
	
	/**
	 * 保存对象并有返回值
	 * @param statementName sql语句id
	 * @param obj 需要保存的对象
	 */
	public Long saveObj(String statementName, Object obj) {
		return entityDao.saveObj(statementName, obj);
	}
	
	/**
	 * 保存对象并有返回值
	 * @param statementName sql语句id
	 * @param obj 需要保存的对象
	 */
	public Integer saveValue(String statementName, Object obj) {
		return entityDao.saveValue(statementName, obj);
	}
}
