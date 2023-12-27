package po;

import po.Good;

import java.util.HashMap;
import java.util.Set;

public class Cart {
    private HashMap<Good, Integer> cart;

    public Cart() {
        cart = new HashMap<>();
    }

    // 添加商品到购物车
    public void add(Good good, int num) {
        if (cart.containsKey(good)) { // 如果购物车中已经存在该商品
            cart.put(good, cart.get(good) + num); // 更新商品数量
        } else { // 如果购物车中不存在该商品
            cart.put(good, num); // 添加新的键值对
        }
    }

    // 从购物车中移除商品
    public void remove(Good good, int num) {
        if (cart.containsKey(good)) { // 如果购物车中存在该商品
            int currentNum = cart.get(good); // 获取当前商品数量
            if (currentNum >= num) { // 如果当前商品数量大于等于要移除的数量
                cart.put(good, currentNum - num); // 更新商品数量
            } else { // 如果当前商品数量小于要移除的数量
                cart.remove(good); // 移除该商品
            }
        }
    }

    // 获取购物车中指定商品的数量
    public int getNum(Good good) {
        if (cart.containsKey(good)) {
            return cart.get(good);
        } else {
            return 0;
        }
    }

    // 清空购物车
    public void clear() {
        cart.clear();
    }

    // 获取购物车中所有商品
    public Set<Good> getAllGoods() {
        return cart.keySet();
    }

    // 获取购物车中所有商品的数量
    public int getTotalNum() {
        int totalNum = 0;
        for (Good good : cart.keySet()) {
            totalNum += cart.get(good);
        }
        return totalNum;
    }
}