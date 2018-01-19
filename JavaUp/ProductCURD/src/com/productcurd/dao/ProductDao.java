package com.productcurd.dao;

import com.productcurd.domain.Product;
import com.productcurd.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    public List<Product> findAll() throws SQLException{
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());

        String sql = "select * from product";

        List<Product> query = queryRunner.query(sql, new BeanListHandler<>(Product.class));

        return query;
    }

    /**
     * `pid` varchar (96),
     `pname` varchar (150),
     `market_price` double ,
     `shop_price` double ,
     `pimage` varchar (600),
     `pdate` date ,
     `pdesc` varchar (765)
     * @param product
     */
    public void addProduct(Product product) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());

        String sql = "insert into product(pid,pname,market_price,shop_price,pdate,pdesc) values(?,?,?,?,?,?);";

        int row = queryRunner.update(sql,product.getPid(),product.getPname(),product.getMarket_price(),
                product.getShop_price(),product.getPdate(),product.getPdesc());
        if (row == 1)
            System.out.println("添加成功");
    }

    public Product getProductById(String pid) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from product where pid = ?;";
        Product query = queryRunner.query(sql, new BeanHandler<>(Product.class), pid);
        return query;
    }

    public void updateProduct(Product p) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());

        String sql = "update product set pname = ?,market_price = ?,shop_price = ?,pdesc = ? where pid = ?;";

        int row = queryRunner.update(sql,p.getPname(),p.getMarket_price(),p.getShop_price(),
                p.getPdesc(),p.getPid());

        if (row == 1)
            System.out.println("更新一个商品成功");
    }

    public void deleteProductById(String pid) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "delete from product where pid = ?;";

        int update = queryRunner.update(sql, pid);
        if (update == 1)
            System.out.println("删除商品成功");
    }

    public List<Product> searchProductByCondition(String name, String kw) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from product where 1=1";//where 1=1的应用，不是什么高级的应用，也不是所谓的智能化的构造，
                                                        // 仅仅只是为了满足多条件查询页面中不确定的各种因素而采用的一种
                                                            // 构造一条正确能运行的动态SQL语句的一种方法。
        ArrayList<String> params = new ArrayList<>();
        if (name != null && name.trim().length()>0){
            sql += (" and pname like ? ");
            params.add("%"+name+"%");
        }
        if (kw != null && kw.trim().length()>0){
            sql += (" and pdesc like ? ");
            params.add("%"+kw+"%");
        }
        System.out.println(sql);
        List<Product> query = queryRunner.query(sql, new BeanListHandler<>(Product.class), params.toArray());//后面的param参数必须是数组
        return query;
//        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
//        String sql="select  * from product where 1=1 ";
//
//        //存放参数
//        ArrayList<String> params=new ArrayList<>();
//
//        //判断参数是否为空 拼接sql
//        if(name!=null && name.trim().length()>0){
//            sql+=(" and pname like ? ");// pname like "%ssss%"
//            params.add("%"+name+"%");
//        }
//
//        if(kw!=null && kw.trim().length()>0){
//            sql+=(" and pdesc like ? ");// pname like "%ssss%"
//            params.add("%"+kw+"%");
//        }
//
//
//
//        return qr.query(sql, new BeanListHandler<>(Product.class), params.toArray());
    }

    public List<Product> getPageProductList(int currPage, int pageSize) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from product limit ?,?;";
        List<Product> list = queryRunner.query(sql,new BeanListHandler<>(Product.class),(currPage - 1)*pageSize,pageSize);
        return list;
    }

    public int getTotalCount() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select count(*) from product;";
        return ((Long)queryRunner.query(sql,new ScalarHandler())).intValue();
    }
}
