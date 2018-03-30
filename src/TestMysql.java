import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;  
import java.sql.DriverManager;   
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;  
import java.sql.DriverManager;   
import java.sql.SQLException;
public class TestMysql {

	//登陆本地数据库
	private static final String url = "jdbc:mysql://127.0.0.1:3306/test";   
	private  static final String user = "root";  
	private  static final String password = "032599";
	
	private static ResultSet ret = null;
	private  static final String name = "com.mysql.jdbc.Driver";
    public Connection conn = null;  
    public PreparedStatement pst = null; 
	public  TestMysql() {  
        try {  
            Class.forName(name);//指定连接类型  
            conn = DriverManager.getConnection(url, user, password);//获取连接             
            
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
	
	//查询及其他
	public void changeMySqlDate(String sql) {
		try {
			System.out.println("接收到的参数为："+sql);
			pst=conn.prepareStatement(sql);
			pst.executeUpdate();
//			pst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		}
	}
	
	
	//插入删除操作
	public ResultSet selectSqlDate(String sql) {
		try {
			System.out.println("接收到的插入删除参数为："+sql);
			pst=conn.prepareStatement(sql);
			ret = pst.executeQuery();
			return ret;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
	}
	
	//关闭mysql
	public void closeMySql() {
		try {  
            this.conn.close();   
        } catch (SQLException e) {  
            e.printStackTrace();  
        }
	}
	
	
	//关闭数据表
    @SuppressWarnings("static-access")
	public void closeChart() {  
        try {    
            this.pst.close();
            this.ret.close();
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    } 
}
