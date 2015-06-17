package com.netbull.shop.common.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.netbull.shop.common.vo.Page;



/**
 * 负责为单个Entity对象提供CRUD操作的Ibatis DAO基类. <p/> 子类只要在类定义时指定所管理Entity的Class,
 * 即拥有对单个Entity对象的CRUD操作.
 * 
 */

@SuppressWarnings({"unchecked","rawtypes"})
public class IbatisEntityDao<T, PK extends Serializable> extends SqlMapClientDaoSupport implements
        EntityIbaDao<T, PK> {

    /**
     * DAO所管理的Entity类型.
     */
    protected Class<T> entityClass;

    /**
     * 取得entityClass
     */
    protected Class<T> getEntityClass() {
        return entityClass;
    }

    public IbatisEntityDao() {

    }

    /**
     * 在构造函数中将泛型T.class赋给entityClass
     */
    public IbatisEntityDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public boolean exists(String statementName, PK id) {

        T entity = (T) super.getSqlMapClientTemplate().queryForObject(statementName, id);
        if (entity == null) {
            return false;
        } else {
            return true;
        }

    }

    /**
     * 根据参数条件查询
     * 
     * @param statementName 查询sql语句id
     * @param map 参数值map
     */
    public List find(String statementName, Map map) {

        return getSqlMapClientTemplate().queryForList(statementName, map);
    }

    /**
     * 根据参数条件查询
     * 
     * @param statementName 查询sql语句id
     * @param obj 参数值
     */
    public List find(String statementName, Object value) {

        return getSqlMapClientTemplate().queryForList(statementName, value);
    }

    public List<T> findBy(String statementName, Map map) {

        return (List<T>) getSqlMapClientTemplate().queryForList(statementName, map);
    }

    public List<T> findBy(String statementName, Object value) {

        return getSqlMapClientTemplate().queryForList(statementName, value);
    }

    public T findById(String statementName, PK id) {
        return (T) getSqlMapClientTemplate().queryForObject(statementName, id);
    }

    /**
     * 根据参数获取对象
     * 
     * @param statementName sql语句id
     * @param obj 参数对象
     * @return 获取对象
     */
    public T findObj(String statementName, T obj) {

        return (T) getSqlMapClientTemplate().queryForObject(statementName, obj);
    }

    /**
     * 根据参数获取对象
     * 
     * @param statementName sql语句id
     * @param parameterMap 参数map
     * @return 获取对象
     */
    public T findObj(String statementName, Map parameterMap) {

        return (T) getSqlMapClientTemplate().queryForObject(statementName, parameterMap);
    }

    public List<T> findAll(String statementName) {

        return getSqlMapClientTemplate().queryForList(statementName, null);
    }
    
	public List findAllObj(String statementName) {
    	return getSqlMapClientTemplate().queryForList(statementName, null);
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
    public List pagedQuery(String countStatementName, String statementName, Map countParameterMap,
            Map queryParameterMap, Page pager) {
        List pageList = null;
        try {
            // Count查询
            List countlist = getSqlMapClientTemplate().queryForList(countStatementName,
                    countParameterMap);
            int totalCount = (Integer) countlist.get(0);

            if (totalCount >= 1) {
                pager.setTotal(totalCount);
                pager.resetPage();
                // 实际查询返回分页对象
                pageList = getSqlMapClientTemplate().queryForList(statementName, queryParameterMap);
            }
        } catch (Exception e) {
            //打印错误日志
        }

        return pageList;
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
    public List pagedQuery(String countStatementName, String statementName, Object paramClass,
            Page pager) {
        List pageList = null;
        try {
            // Count查询
            List countlist = getSqlMapClientTemplate().queryForList(countStatementName, paramClass);

            if (countlist == null || countlist.size() == 0) {
                return pageList;
            }

            int totalCount = (Integer) countlist.get(0);

            pager.setTotal(totalCount);
            pager.resetPage();
            if (totalCount >= 1) {
                // 实际查询返回分页对象
                pageList = getSqlMapClientTemplate().queryForList(statementName, paramClass);
            }
        } catch (Exception e) {
            //打印错误日志
            e.printStackTrace();
        }

        return pageList;
    }

    public int remove(String statementName) {
       return getSqlMapClientTemplate().delete(statementName, null);
    }

    public int remove(String statementName, Map parameterMap) {
        return getSqlMapClientTemplate().delete(statementName, parameterMap);
    }

    public int removeById(String statementName, PK id) {
        return super.getSqlMapClientTemplate().delete(statementName, id);
    }

    public void save(String statementName, Object obj) {
        getSqlMapClientTemplate().insert(statementName, obj);
    }

    public int update(String statementName, Object obj) {
        return getSqlMapClientTemplate().update(statementName, obj);
    }

    protected boolean hasText(String str) {
        if (str == null || str.trim().equals("")) {
            return false;
        }
        return true;
    }

    /**
     * 调用存储过程,通过jdbc的方式调用
     * 
     * @param procedureName 过程名称
     * @param values 过程参数
     */
    public void executeProcedure(String procedureName, Map map) {
        if ("".equals(procedureName)) {
            return;
        }
        try {
            getSqlMapClientTemplate().insert(procedureName, map);

        } catch (Exception e) {
            throw new RuntimeException("执行存储过程异常: " + procedureName + "异常：" + e);
        }
    }

    /**
     * 删除对象或集合
     * 
     * @param statementName sql语句id
     * @param obj 参数值
     * @exception
     * @since 1.0.0
     */
    public int remove(String statementName, Object obj) {

        return getSqlMapClientTemplate().delete(statementName, obj);
    }

	/**
	 * 返回对象类型
	 * @param procedureName
	 * @param map
	 * @return
	 */
	public Object findObject(String statementName, Object o){
		return getSqlMapClientTemplate().queryForObject(statementName,o);
	}

	/**
	 * 保存对象并有返回值
	 * @param statementName sql语句id
	 * @param obj 需要保存的对象
	 */
	public Long saveObj(String statementName, Object obj) {
		Object returnObj = getSqlMapClientTemplate().insert(statementName, obj);
		return  returnObj!=null?(Long)returnObj:null;
	}

	/**
	 * 保存对象并有返回值
	 * @param statementName sql语句id
	 * @param obj 需要保存的对象
	 */
	public Integer saveValue(String statementName, Object obj) {
		Object returnObj = getSqlMapClientTemplate().insert(statementName, obj);
		return  returnObj!=null?(Integer)returnObj:null;
	}
    
}
