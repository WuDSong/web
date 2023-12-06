package po;

public class UserBean {
    private int id;
    private String uname;
    private String password;

    public UserBean(String uname, String password) {
        this.uname = uname;
        this.password = password;
    }

    public UserBean(int id, String uname, String password) {
        this.id = id;
        this.uname = uname;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void show(){
        System.out.println("id:"+id+"  姓名："+uname+"  密码："+password);
    }
}
