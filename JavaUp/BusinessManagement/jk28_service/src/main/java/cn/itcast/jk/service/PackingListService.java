package cn.itcast.jk.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import cn.itcast.jk.domain.PackingList;
import cn.itcast.jk.utils.Page;


/**
 * @Description:	PackingListService接口
 * @CreateDate:		2016-8-15 10:45:58
 */

public interface PackingListService {

	public List<PackingList> find(String hql, Class<PackingList> entityClass, Object[] params);
	public PackingList get(Class<PackingList> entityClass, Serializable id);
	public Page<PackingList> findPage(String hql, Page<PackingList> page, Class<PackingList> entityClass, Object[] params);
	
	
	public void saveOrUpdate(PackingList entity);
	public void saveOrUpdateAll(Collection<PackingList> entitys);
	
	public void deleteById(Class<PackingList> entityClass, Serializable id);
	public void delete(Class<PackingList> entityClass, Serializable[] ids);
}
