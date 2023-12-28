package po;

import java.sql.Timestamp;

public class Order {
    private int uid;
    private int gid;
    private int gnum;
    private Timestamp orderTime;

    public Order() {
    }

    public Order(int uid, int gid, int gnum, Timestamp orderTime) {
        this.uid = uid;
        this.gid = gid;
        this.gnum = gnum;
        this.orderTime = orderTime;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public int getGnum() {
        return gnum;
    }

    public void setGnum(int gnum) {
        this.gnum = gnum;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "uid=" + uid +
                ", gid=" + gid +
                ", gnum=" + gnum +
                ", orderTime=" + orderTime +
                '}';
    }
}
