package service;

import dao.Dao;
import po.Good;
import po.UserBean;
import util.DBUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Service {
    public Connection connection;

    public Service(Connection connection) {
        this.connection = connection;
        System.out.println("已连接：" + this.connection);
        System.out.println("服务层已就绪");
    }

    public void login(UserBean userBean)  {
        try {
            if(new Dao().login(userBean,connection)){
                System.out.println("--登录成功--");
            }else System.out.println("--登录失败：用户名错误/密码错误/未注册");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void registration(UserBean userBean) {
        int flag=0;
        try{
            connection.setAutoCommit(false);
            new Dao().registration(userBean,connection);
            connection.commit();//记得要提交
            flag=1;
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("--注册失败原因：  "+e);
        } finally {
            if(flag==1) System.out.println("--注册成功！---");
        }
    }

    public void logout(UserBean userBean){
        int flag=0;
        try {
            connection.setAutoCommit(false);
            new Dao().logout(userBean.getId(),connection);
            connection.commit();//记得要提交
            flag=1;
        } catch (SQLException e) {
            System.out.println("--注销失败:"+e);
        }finally {
            if(flag==1) {
                System.out.println("--注销成功！---");
                userBean.setId(0);
            }
        }
    }

    public List<Good> getGoods()  {
        try {
            return new Dao().getGoodItems(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Good getGoodById(int id)  {
        try {
            return new Dao().getGoodByID(id,connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
