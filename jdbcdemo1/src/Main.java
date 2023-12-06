import dao.Dao;
import po.UserBean;
import service.Service;
import util.DBUtils;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println("===========**开始**===========");
        Service service=new Service(DBUtils.getConnection());//初始化服务
        UserBean user=new UserBean("tes4tdfghgjkhjghfgdfsa1","465464");//用户准备
        System.out.println(user.getId());//
        service.registration(user);
        user.show();
        service.login(user);
        if(user.getId()>0)//代表已经登录
            service.logout(user);
        user.show();
    }
}