import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.SQLException;  
import java.sql.Statement;  
  
public class MyDBConnection{  
  
    private String DBDriver;  
    private String DBURL;  
    private String DBUser;  
    private String DBPass;  
    private Connection conn=null;  
    private Statement stmt=null; 
    
    public MyDBConnection(){  
        DBDriver="com.mysql.jdbc.Driver";  
        DBURL="jdbc:mysql://123.206.7.198:3307/test";  
        DBUser="qinxh";//�û���  
        DBPass="031578";//���ݿ�����  
        try{  
            Class.forName(DBDriver);//������������  
            //System.out.println("���ݿ�����������سɹ�");  
        }catch(Exception e){  
            e.printStackTrace();  
        }  
        try{  
            conn=DriverManager.getConnection(DBURL,DBUser,DBPass);//ȡ�����Ӷ���  
            stmt=conn.createStatement();//ȡ��SQL������  
            System.out.print("�������ݿ�ɹ�");  
        }catch(Exception e){  
            e.printStackTrace();  
        }  
    } 
    
    public Connection getMyConnection(){  
        return conn;  
    }  
    
    public Statement getMyStatement(){  
        return stmt;  
    }  
    
    public void closeMyConnection(){//�ر����ݿ�����  
        try{  
            stmt.close();  
            conn.close();  
        }catch(SQLException e){  
            e.printStackTrace();  
        }  
    }  
    
    public String toString(){  
        return "���ݿ���������"+DBDriver+"�����ӵ�ַ"+DBURL+"���û���"+DBUser+"������"+DBPass;  
    }  
}  