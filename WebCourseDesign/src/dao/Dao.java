package dao;

import po.Good;
import po.UserBean;
import util.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Dao {
    //通过用户名密码找最近的id
    private int getUserId(UserBean userBean,Connection connection) throws SQLException {
        String sql1 = "SELECT id from userinfo where uname =? and password=?;";
        PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
        preparedStatement1.setString(1, userBean.getUname());
        preparedStatement1.setString(2, userBean.getPassword());
        ResultSet resultSet=preparedStatement1.executeQuery();//用preparedStatement，这条语句括号中是不能写sql
        int id = -1;
        while (resultSet.next()) {
            id =resultSet.getInt("id");
            userBean.setId(id);
        }
        DBUtils.closeAll(null, preparedStatement1, resultSet);
        return id;
    }
    //注册
    public void registration(UserBean userBean, Connection connection) throws SQLException {
        System.out.println("开始注册--ing");
        String sql = "INSERT INTO userinfo (uname,`password`) VALUES (?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, userBean.getUname());
        preparedStatement.setString(2, userBean.getPassword());
        int row = preparedStatement.executeUpdate();//一定写这东西，不写的话读不进数据库
        if (row != 0) {
            userBean.setId(getUserId(userBean,connection));
        }
        DBUtils.closeAll(null, preparedStatement, null);
    }

    //登录
    public boolean login(UserBean userBean, Connection connection) throws SQLException, ClassNotFoundException {
        if (connection != null)
            System.out.println("准备登录--ing");
        userBean.show();
        String sql = "SELECT *from userinfo where uname =? and password=?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, userBean.getUname());
        preparedStatement.setString(2, userBean.getPassword());
        ResultSet resultSet = preparedStatement.executeQuery();
        boolean flag = false;
        if (resultSet.next()) {
            flag = true;
        }
        userBean.setId(getUserId(userBean,connection));
        DBUtils.closeAll(null, preparedStatement, resultSet);
        return flag;
    }
    //注销,必须登录后使用
    public void logout(int uid,Connection connection) throws SQLException {
        String sql = "DELETE from userinfo where id =?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, uid);
        int resultSet = preparedStatement.executeUpdate();
        DBUtils.closeAll(null, preparedStatement, null);
    }

    public List<Good> getGoodItems(Connection connection) throws SQLException {
        List<Good> goods = new ArrayList<>();
        if (connection != null)
            System.out.println("加载货物--ing");
        String sql = "SELECT * FROM items;";
        Statement statement = connection.createStatement();
        // 查询方法
        ResultSet resultSet = statement.executeQuery(sql);
        // 处理结果
        while (resultSet.next()) {
            Good good = new Good();
            good.setId(resultSet.getInt("id"));
            good.setName(resultSet.getString("name"));
            good.setCity(resultSet.getString("city"));
            good.setPrice(resultSet.getFloat("price"));
            good.setNumber(resultSet.getInt("number"));
            good.setPicture(resultSet.getString("picture"));
            goods.add(good);
        }
        DBUtils.closeAll(null,statement,resultSet);
        return goods;
    }

    //TODO:通过id取货物信息
    public Good getGoodByID(int id,Connection connection) throws SQLException {
        Good good = new Good();
        String sql = "select * from items where id=?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            good.setId(resultSet.getInt("id"));
            good.setName(resultSet.getString("name"));
            good.setCity(resultSet.getString("city"));
            good.setPrice(resultSet.getFloat("price"));
            good.setNumber(resultSet.getInt("number"));
            good.setPicture(resultSet.getString("picture"));
        }
        DBUtils.closeAll(null,preparedStatement,resultSet);
        return good;
    }
}
