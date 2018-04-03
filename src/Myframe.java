import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Myframe extends JFrame {
	private JTextField answer;
	private JLabel lun = new JLabel("第 1 轮");;
	private JLabel scorelb = new JLabel("得分:");
	private static JLabel question = new JLabel("____________");
	private JLabel num = new JLabel("1");
	private JLabel time = new JLabel("计时：00:00");
	private JComboBox languae = new JComboBox();
	private JLabel score = new JLabel("0");
	private JButton nextbtn = new JButton("下一题");
	private JButton drawlb = new JButton("成绩图表");
	private final JButton startbtn = new JButton("开始测试");
	long startTime;
	int ans=0,tans=0,count =1, clun=1;
	int sum=0;
	Boolean flag=false;
	static String sans=new String("");;
	static String qus=new String("");
	static TestMysql ls;
	static ResultSet rs;
	static ResultSet as;  
	static Random r = new Random();
	static int s=r.nextInt(10000)+1;
	ArrayList<Integer> scorelist=new ArrayList<>();  //存放每一轮的得分
	private JPanel panel = new JPanel()
	{
		//设置背景  
        protected void paintComponent(Graphics g) {  
            Image bg;  
            try {  
                bg = ImageIO.read(new File("src/image/4.jpg"));  
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }; 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window=new login();
					window.show();			
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});	
	}

	/**
	 * Create the frame.
	 */
	public Myframe() {	
		startTime = new Date().getTime();	
		setBounds(100, 100, 821, 643);
		//设置面板
		panel.setBackground(Color.WHITE);	
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		//设置第几轮
		lun.setForeground(Color.BLUE);
		lun.setHorizontalAlignment(SwingConstants.CENTER);
		lun.setFont(new Font("华文行楷", Font.BOLD, 42));
		lun.setBackground(Color.BLUE);
		lun.setBounds(339, 80, 139, 71);
		panel.add(lun);
		//设置题号
		num.setToolTipText("");
		num.setHorizontalAlignment(SwingConstants.CENTER);
		num.setBorder(new LineBorder(new Color(0, 204, 51), 3, true));
		num.setFont(new Font("宋体", Font.BOLD, 17));
		num.setBackground(new Color(0, 255, 255));
		num.setBounds(234, 209, 45, 34);
		panel.add(num);
		//设置运算题
		question.setForeground(new Color(51, 204, 255));
		question.setFont(new Font("华文楷体", Font.BOLD, 32));
		question.setBounds(298, 205, 276, 53);
		panel.add(question);
		//设置答案
		answer = new JTextField();
		answer.setFont(new Font("华文楷体", Font.BOLD, 32));
		answer.setBounds(588, 205, 128, 53);
		panel.add(answer);
		answer.setColumns(10);
		//设置得分
		scorelb.setFont(new Font("微软雅黑", Font.BOLD, 21));
		scorelb.setBounds(252, 339, 72, 46);
		panel.add(scorelb);
		score.setForeground(Color.RED);
		score.setFont(new Font("宋体", Font.BOLD, 22));
		score.setHorizontalAlignment(SwingConstants.CENTER);
		score.setBounds(298, 346, 56, 34);
		panel.add(score);
		//设置计时器
		time.setFont(new Font("微软雅黑", Font.BOLD, 21));
		time.setBounds(255, 429, 186, 46);
		panel.add(time);
		//设置语言
		languae.setEditable(true);
		languae.setBounds(582, 68, 72, 24);
		languae.addItem("中文");
		languae.addItem("英文");
		panel.add(languae);
		//设置"下一题"按钮
		nextbtn.setBackground(new Color(204, 204, 255));
		nextbtn.setFont(new Font("宋体", Font.BOLD, 17));
		nextbtn.setBounds(479, 334, 113, 46);
		panel.add(nextbtn);
		//下一题事件监听
		nextbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isend;
				isend=nextbtn.getText().equals("答题结束");
				if(! isend)
				{
					count = count+1;
					num.setText(""+count);
					if(count==21)
					{
						startTime = new Date().getTime();
						count =1;
						num.setText(""+count);
						clun=clun+1;
						lun.setText("第 "+clun+" 轮");
						scorelist.add(sum);	
						nextbtn.setText("提交");
						int value=JOptionPane.showConfirmDialog(null, "当前得分："+sum+"，是否进入下一轮？", "提示信息", JOptionPane.YES_NO_OPTION);
						if (value==JOptionPane.YES_OPTION) {
							System.out.println("你选择了是");
							nextbtn.setText("下一题");}
							else if (value==JOptionPane.NO_OPTION) {
							nextbtn.setText("答题结束");
							}
						sum=0;
					}
					test();
					answer.setText("");
				}
				else{
					
				}
			}
		});
		
		//设置"成绩图表"按钮
		drawlb.setBackground(new Color(204, 204, 255));
		drawlb.setFont(new Font("宋体", Font.BOLD, 17));
		drawlb.setBounds(479, 432, 113, 46);
		panel.add(drawlb);
		//成绩图表事件监听
		drawlb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Chart demo = new Chart(scorelist);  
		        demo.setVisible(true);
			}
		});
		//设置"开始测试"按钮
		startbtn.setForeground(new Color(255, 0, 51));
		startbtn.setBackground(new Color(255, 204, 204));
		startbtn.setFont(new Font("楷体", Font.BOLD, 20));
		startbtn.setBounds(164, 58, 128, 41);
		panel.add(startbtn);
		
		//开始测试事件监听
		startbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {						
				startTime = new Date().getTime();
				this.timer();
				test();
			}
			//计时器	
			public void timer() {
				new Thread() {
					public void run() {
						while (true) {
							try {
								Thread.sleep(1000);
								long seconds = (new Date().getTime() - startTime) / 1000;
								long mm = seconds / 60;
								long ss = seconds % 60;
	
								time.setText("计时: " + (mm < 10 ? "0" + mm : "" + mm) + ":" + (ss < 10 ? "0" + ss : "" + ss));
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}.start();
			}
		});	
	}	
	
	//获取题和答案
	public void test()
	{
		String str = answer.getText();
		if(str.equals("")) str = ""+0;
		ans=Integer.parseInt(str.trim());
		//判断答案是否正确
		if(tans==ans&&ans!=0)
		{
			flag=true;
		}
		else 
			flag=false;
		//如果答案正确，则加5分
		if(flag)
		{
			 sum = sum+5;
			 score.setText(String.valueOf(sum));
		}
		else 
			score.setText(String.valueOf(sum));
		
		//从数据库题目表中获取问题
		s=r.nextInt(10000)+1;
		ls=new TestMysql();
		rs=ls.selectSqlDate("select content from questionbank where id="+s);
		try {
			while(rs.next())
			{
				System.out.println(rs.getString("content"));
				qus=rs.getString("content");	
				question.setText(qus);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//从数据库题目表中获取正确答案，用于判断答案是否正确
		as=ls.selectSqlDate("select result from questionbank where id="+s);	
		try {
			while(as.next())
			{
				System.out.println(as.getString("result"));
				sans=as.getString("result");
				tans = Integer.parseInt(sans);
				//System.out.println(tans);
			}
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
	}
 }

