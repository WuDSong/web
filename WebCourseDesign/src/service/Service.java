package service;

import dao.Dao;
import po.Good;
import po.Order;
import po.UserBean;
import util.DBUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Service {
    public Connection connection;

    public Service(Connection connection) {
        this.connection = connection;
//        System.out.println("已连接：" + this.connection);
//        System.out.println("服务层已就绪");
    }

    public void login(UserBean userBean) {
        try {
            if (new Dao().login(userBean, connection)) {
                System.out.println("--登录成功--");
            } else System.out.println("--登录失败：用户名错误/密码错误/未注册");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void registration(UserBean userBean) {
        int flag = 0;
        try {
            connection.setAutoCommit(false);
            new Dao().registration(userBean, connection);
            connection.commit();//记得要提交
            flag = 1;
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("--注册失败原因：  " + e);
        } finally {
            if (flag == 1) System.out.println("--注册成功！---");
        }
    }

    public void logout(UserBean userBean) {
        int flag = 0;
        try {
            connection.setAutoCommit(false);
            new Dao().logout(userBean.getId(), connection);
            connection.commit();//记得要提交
            flag = 1;
        } catch (SQLException e) {
            System.out.println("--注销失败:" + e);
        } finally {
            if (flag == 1) {
                System.out.println("--注销成功！---");
                userBean.setId(0);
            }
        }
    }

    public List<Good> getGoods() {
        try {
            return new Dao().getGoodItems(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Good getGoodById(int id) {
        try {
            return new Dao().getGoodByID(id, connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean submitOrder(List<Order> orderList) {
        boolean flag = false;
        return flag;
    }

    public boolean submitAllGood(int uid, HashMap<Integer, Integer> cart) {
        boolean flag=false;
        Dao dao = new Dao();
        try {
            connection.setAutoCommit(false);
            Iterator<Map.Entry<Integer, Integer>> iterator = cart.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, Integer> entry = iterator.next();
                Integer key = entry.getKey();
                Integer value = entry.getValue();
                dao.insertOrder(uid, key,value,connection);
            }
            connection.commit();//记得要提交
            flag=true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            return flag;
        }
    }

    public List<Order> getMyOrder(int id) {
        try {
            return new Dao().getOrderByUid(id, connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
