package com.store.service.impl;

import com.store.dao.CategoryDao;
import com.store.dao.impl.CategoryDaoImpl;
import com.store.domain.Category;
import com.store.service.CategoryService;
import com.store.utils.BeanFactory;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    @Override
    public List<Category> findAll() throws SQLException {
        //1.创建缓存管理器
        CacheManager cacheManager = CacheManager.create(CategoryServiceImpl.class.getClassLoader().getResourceAsStream("ehcache.xml"));
        //2.获取指定的缓存
        Cache categoryCache = cacheManager.getCache("categoryCache");
        //3.通过缓存获取数据  将cache看成一个map即可
        Element cList = categoryCache.get("cList");
        //4.判断数据
        List<Category> categoryList = null;
        if (cList == null) {
            CategoryDao categoryDao = new CategoryDaoImpl();
            categoryList = categoryDao.findAllCategory();

            //将list放入缓存
            categoryCache.put(new Element("cList",categoryList));//缓存往磁盘上写实体数据，实体需要序列化
                                                                //严重: Disk Write of cList failed:
                                                                 //java.io.NotSerializableException: com.store.domain.Category

            System.out.println("缓存中无数据，从数据库读取");
        } else {
            categoryList = (List<Category>) cList.getObjectValue();
            System.out.println("从缓存中读取数据");
        }

        return categoryList;
    }

    @Override
    public void add(Category category) throws Exception {
        CategoryDao categoryDao = (CategoryDao)BeanFactory.getBean("CategoryDao");
        categoryDao.add(category);
        CacheManager cacheManager = CacheManager.create(CategoryServiceImpl.class.getClassLoader().getResourceAsStream("ehcache.xml"));
        Cache categoryCache = cacheManager.getCache("categoryCache");
        categoryCache.remove("cList");

    }

    @Override
    public Category getById(String cid) throws Exception {
        CategoryDao categoryDao = (CategoryDao)BeanFactory.getBean("CategoryDao");

        return  categoryDao.getById(cid);
    }

    @Override
    public void update(Category category) throws Exception {
        CategoryDao categoryDao = (CategoryDao)BeanFactory.getBean("CategoryDao");
        categoryDao.update(category);
        CacheManager cacheManager = CacheManager.create(CategoryServiceImpl.class.getClassLoader().getResourceAsStream("ehcache.xml"));
        Cache categoryCache = cacheManager.getCache("categoryCache");
        categoryCache.remove("cList");
    }

    @Override
    public void delete(String cid) throws Exception {
        CategoryDao categoryDao = (CategoryDao)BeanFactory.getBean("CategoryDao");
        categoryDao.delete(cid);
        CacheManager cacheManager = CacheManager.create(CategoryServiceImpl.class.getClassLoader().getResourceAsStream("ehcache.xml"));
        Cache categoryCache = cacheManager.getCache("categoryCache");
        categoryCache.remove("cList");
    }

    public static void main(String[] args) {
        InputStream resourceAsStream = CategoryServiceImpl.class.getClassLoader().getResourceAsStream("ehcache.xml");
        System.out.println(resourceAsStream);
    }
}
