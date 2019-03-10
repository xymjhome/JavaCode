package com.store.dao.impl;

import com.store.dao.OrderDao;
import com.store.domain.Order;
import com.store.domain.OrderItem;
import com.store.domain.Product;
import com.store.utils.DataSourceUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class OrderDaoImpl implements OrderDao {
    @Override
    public void add(Order order) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        String sql="insert into orders values(?,?,?,?,?,?,?,?)";
        //事务操作同一个连接
        queryRunner.update(DataSourceUtils.getConnection(),sql,order.getOid(),order.getOrdertime(),
                order.getTotal(),order.getState(),order.getAddress(),order.getName(),
                order.getTelephone(),order.getUser().getUid());
    }


    /**
     * 添加一条订单项
     */
    @Override
    public void addItem(OrderItem oi) throws SQLException {
        QueryRunner qr = new QueryRunner();
        /**
         * `itemid` varchar(32) NOT NULL,
         `count` int(11) DEFAULT NULL,
         `subtotal` double DEFAULT NULL,
         `pid` varchar(32) DEFAULT NULL,
         `oid` varchar(32) DEFAULT NULL,
         */
        String sql="insert into orderitem values(?,?,?,?,?)";
        qr.update(DataSourceUtils.getConnection(),sql, oi.getItemid(),oi.getCount(),oi.getSubtotal(),oi.getProduct().getPid(),oi.getOrder().getOid());
    }

    @Override
    public List<Order> findAllByPage(int currPage, int pageSize, String uid) throws Exception {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from orders where uid = ? limit ?,?";
        List<Order> query = qr.query(sql, new BeanListHandler<>(Order.class), uid, (currPage - 1) * pageSize, pageSize);

        sql = "select * from orderitem oi,product pd where oi.pid = pd.pid and oi.oid = ?";
        for (Order o : query) {
            List<Map<String, Object>> query1 = qr.query(sql, new MapListHandler(), o.getOid());
            for (Map<String, Object> map : query1) {
                Product product = new Product();
                BeanUtils.populate(product,map);
                OrderItem orderItem = new OrderItem();
                BeanUtils.populate(orderItem,map);
                orderItem.setProduct(product);
                orderItem.setOrder(o);
                o.getItems().add(orderItem);
            }
        }
        return query;
    }

    @Override
    public int getTotalCount(String uid) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select count(*) from orders where uid = ?";

        return ((Long)qr.query(sql,new ScalarHandler(),uid)).intValue();
    }

    @Override
    public Order getById(String oid) throws Exception {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from orders where oid = ?";
        Order order = qr.query(sql, new BeanHandler<>(Order.class),oid);

        sql = "select * from orderitem oi,product pd where oi.pid = pd.pid and oi.oid = ?";
        List<Map<String, Object>> query = qr.query(sql, new MapListHandler(), oid);
        for (Map<String, Object> map : query) {
            Product pt = new Product();
            BeanUtils.populate(pt,map);
            OrderItem orderItem = new OrderItem();
            BeanUtils.populate(orderItem,map);
            orderItem.setProduct(pt);
            orderItem.setOrder(order);
            order.getItems().add(orderItem);
        }
        return order;
    }

    @Override
    public void update(Order order) throws Exception {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql="update orders set state=?,address=?,name=?,telephone=? where oid=?";
        qr.update(sql, order.getState(),order.getAddress(),order.getName(),order.getTelephone(),order.getOid());
    }

    @Override
    public List<Order> findAllByState(String state) throws Exception {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select * from orders where 1 = 1 ";
        if (state != null && state.trim().length() > 0) {
            sql += "and state = ? order by ordertime desc";
            return qr.query(sql,new BeanListHandler<Order>(Order.class),state);
        }
        sql += " order by ordertime desc";
        return qr.query(sql,new BeanListHandler<Order>(Order.class));
    }
}
