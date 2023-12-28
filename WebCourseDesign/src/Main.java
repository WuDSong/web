
import dao.Dao;
import po.Cart;
import po.Good;

import po.Order;
import service.Service;
import util.DBUtils;

import java.sql.Array;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;


public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        List<Good> goods = new Dao().getGoodItems(DBUtils.getConnection());
//        if(!goods.isEmpty()){
//            for (int i = 0; i < goods.size(); i++) {
//                System.out.println(goods.get(i).toString());
//            }
//        }
        //测试
        System.out.println(new Service(DBUtils.getConnection()).connection);
        Good g1=new Good();
        Good g2=new Good();
        System.out.println(g1.toString()+g2.toString());
        System.out.println(Good.goodEqual(g1,g2));
        Cart cart=new Cart();
        cart.add(g1,1);
        cart.add(g2,11);
        System.out.println(cart.getNum(g1));

        System.out.println("===============================");
        //测试是否获取
        List<Order> orders=new Dao().getOrderByUid(51,DBUtils.getConnection());
        for (int i = 0; i < orders.size(); i++) {
            System.out.println(orders.get(i).toString());
        }

        //数据库测试下单40 1 5测试
        //new Dao().insertOrder(40,1,5,DBUtils.getConnection());

        //服务层测试
        //原来 1 490 ;2 800
//        HashMap<Integer,Integer> testMap=new HashMap<>();
//        testMap.put(1,10);
//        testMap.put(2,10);
//        //预测 1 480 ;2 790
//        if(new Service(DBUtils.getConnection()).submitAllGood(40,testMap)){
//            System.out.println("插入成功");
//        }
    }
}