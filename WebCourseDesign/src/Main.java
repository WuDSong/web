
import po.Cart;
import po.Good;

import service.Service;
import util.DBUtils;

import java.sql.Array;
import java.sql.SQLException;
import java.util.HashMap;


public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        List<Good> goods = new Dao().getGoodItems(DBUtils.getConnection());
//        if(!goods.isEmpty()){
//            for (int i = 0; i < goods.size(); i++) {
//                System.out.println(goods.get(i).toString());
//            }
//        }
        System.out.println(new Service(DBUtils.getConnection()).connection);
        Good g1=new Good();
        Good g2=new Good();
        System.out.println(g1.toString()+g2.toString());
        System.out.println(Good.goodEqual(g1,g2));
        Cart cart=new Cart();
        cart.add(g1,1);
        cart.add(g2,11);
        System.out.println(cart.getNum(g1));
    }
}