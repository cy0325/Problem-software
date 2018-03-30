import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

	public class Main 
	{
		static TestMysql ls;
		static ResultSet rs;
		
		public static void main(String[] args) throws SQLException 
		{
			login window=new login();
			window.show();
			for(int i=1;i<=20;i++)
			{
				Random r = new Random();
				int s=r.nextInt(10000)+1;
				ls=new TestMysql();
				rs=ls.selectSqlDate("select content from questionbank where id="+s);
				while(rs.next())
				{
					System.out.println(rs.getString("content"));
				}
			}
					
		}

	}

