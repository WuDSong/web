package service;
import dao.Dao;
import po.UserBean;
import java.sql.Connection;
import java.sql.SQLException;

public class Service {
    public Connection connection;

    public Service(Connection connection) {
        this.connection = connection;
        System.out.println("已连接：" + this.connection);
        System.out.println("服务层已就绪");
    }

    public void login(UserBean userBean) throws SQLException, ClassNotFoundException {
        if(new Dao().login(userBean,connection)){
            System.out.println("--登录成功--");
        }else System.out.println("--登录失败：用户名错误/密码错误/未注册");
    }

    public void registration(UserBean userBean) throws SQLException {
        int flag=0;
        try{
            connection.setAutoCommit(false);
            new Dao().registration(userBean,connection);
            connection.commit();//记得要提交
            flag=1;
        } catch (Exception e) {
            connection.rollback();
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

}
