package myClass;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
//用来当数据库
public class GoodDB {
    private static Map<String, Good> goods=new HashMap<String,Good>();
    //静态代码块
    static{
        goods.put("1", new Good("1","华为手机", 3999));
        goods.put("2", new Good("2","烤箱", 1200));
        goods.put("3", new Good("3","豆浆机", 450));
        goods.put("4", new Good("4","电脑", 7999));
        goods.put("5", new Good("5","台灯", 15));
    }
    //获得所有商品
    public static Collection<Good> getAll() {
        return goods.values();
    }
    //根据id获得商品
    public static Good getById(String id) {
        return goods.get(id);
    }

}
