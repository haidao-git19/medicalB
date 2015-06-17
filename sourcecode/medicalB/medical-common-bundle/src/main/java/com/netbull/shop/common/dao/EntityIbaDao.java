package com.netbull.shop.common.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.netbull.shop.common.vo.Page;


/**
 * Dao泛型基类
 * 继承此类，可以实现单表的增删改查
 * 
 */
@SuppressWarnings("rawtypes")
public interface EntityIbaDao<T, PK extends Serializable> {
	/**
	 * 获取全部对象，相对于一个表中所有的记录
	 * 
	 * @return 对象列表
	 */
	public List<T> findAll(String statementName);
	
	/**
	 * 获取全部对象，相对于一个表中所有的记录（无泛型）
	 * 
	 * @return 对象列表
	 */
	public List findAllObj(String statementName);

	/**
	 * 根据ID获取对象
	 * @param statementName sql语句id
	 * @param id 对象主键
	 * @return 获取对象
	 */
	public T findById(String statementName, PK id);
	
	/**
     * 根据参数获取对象
     * @param statementName sql语句id
     * @param obj 参数对象
     * @return 获取对象
     */
    public T findObj(String statementName, T obj);

    /**
     * 根据参数获取对象
     * @param statementName sql语句id
     * @param parameterMap 参数map
     * @return 获取对象
     */
    public T findObj(String statementName, Map parameterMap);

	/**
	 * 根据ID判断对象是否存在
	 * @param statementName sql语句id
	 * @param id 对象主键
	 * @return true:对象存在 false:对象不存在
	 */
	public boolean exists(String statementName,PK id);

	/**
	 * 保存对象
	 * @param statementName sql语句id
	 * @param obj 需要保存的对象
	 */
	public void save(String statementName, Object obj);
	
	/**
	 * 更新对象
     * @param statementName sql语句id
     * @param obj 需要更新的对象
	 */
	public int update(String statementName, Object obj);

	/**
	 * 删除对象
	 * @param statementName sql语句id
	 */
	public int remove(String statementName);
	
	/**
	 * 删除对象或集合  
	 * @param statementName sql语句id
	 * @param parameterMap  参数值map
	 * @exception    
	 * @since  1.0.0
	 */
	public int remove(String statementName, Map parameterMap);
	
	/**
     * 删除对象或集合  
     * @param statementName sql语句id
     * @param obj  参数值
     * @exception    
     * @since  1.0.0
     */
    public int remove(String statementName, Object obj);

	/**
	 * 根据ID删除对象
	 * 
	 * @param id  将删除的对象的ID
	 */
	public int removeById(String statementName, PK id);

	/**
	 * 根据参数条件查询
	 * 
	 * @param statementName 查询sql语句id
	 * @param map 参数值map
	 */
	public List find(String statementName, Map map);
	
	/**
     * 根据参数条件查询
     * 
     * @param statementName 查询sql语句id
     * @param obj 参数值
     */
    public List find(String statementName, Object obj);
	
	public List<T> findBy(String statementName, Map map);

	/**
	 * 根据属性名和属性值查询对象.
	 * 
	 * @return 符合条件的对象列表
	 */
	public List<T> findBy(String statementName, Object value);

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
			Map countParameterMap, Map queryParameterMap, Page pager);
	
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
            Object paramClass, Page pager);

	/**
	 * 调用存储过程,通过jdbc的方式调用
	 * 
	 * @param procedureName
	 *            过程名称
	 * @param values
	 *            过程参数
	 */
	public void executeProcedure(String procedureName, Map map);

	/**
	 * 返回对象类型
	 * @param procedureName
	 * @param map
	 * @return
	 */
	public Object findObject(String statementName, Object o);
	
	/**
	 * 保存对象并有返回值
	 * @param statementName sql语句id
	 * @param obj 需要保存的对象
	 */
	public Long saveObj(String statementName, Object obj);
	
	/**
	 * 保存对象并有返回值
	 * @param statementName sql语句id
	 * @param obj 需要保存的对象
	 */
	public Integer saveValue(String statementName, Object obj);

}
