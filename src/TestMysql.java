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
	

	//��½�������ݿ�
	private static final String url = "jdbc:mysql://123.206.7.198:3307/test";   
	private  static final String user = "qinxh";  
	private  static final String password = "031578";
	
	private static ResultSet ret = null;
	private  static final String name = "com.mysql.jdbc.Driver";
    public Connection conn = null;  
    public PreparedStatement pst = null; 
	public  TestMysql() {  
        try {  
            Class.forName(name);//ָ����������  
            conn = DriverManager.getConnection(url, user, password);//��ȡ����             
            
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
	
	//��ѯ������
	public void changeMySqlDate(String sql) {
		try {
			System.out.println("���յ��Ĳ���Ϊ��"+sql);
			pst=conn.prepareStatement(sql);
			pst.executeUpdate();
//			pst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		}
	}
	
	
	//����ɾ������
	public ResultSet selectSqlDate(String sql) {
		try {
			System.out.println("���յ��Ĳ���ɾ������Ϊ��"+sql);
			pst=conn.prepareStatement(sql);
			ret = pst.executeQuery();
			return ret;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
	}
	
	//�ر�mysql
	public void closeMySql() {
		try {  
            this.conn.close();   
        } catch (SQLException e) {  
            e.printStackTrace();  
        }
	}
	
	
	//�ر����ݱ�
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
