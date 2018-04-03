
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

	private JPanel panel = new JPanel()
	{//设置背景  
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
	private JTextField answer;
	private JLabel lun = new JLabel("第 1 轮");;
	private JLabel scorelb = new JLabel("得分");
	private static JLabel question = new JLabel("");
	private JLabel num = new JLabel("1");
	private JLabel time = new JLabel("计时：00:00");
	private JComboBox languae = new JComboBox();
	private JLabel score = new JLabel("60");
	private JButton nextbtn = new JButton("下一题");
	private JButton drawlb = new JButton("成绩图表");
	private final JButton startbtn = new JButton("开始测试");
	long startTime;
	int ans=0,count =1, clun=1;
	String sans;
	int sum=0;
	int tans=0; 
	ArrayList<Integer> scorelist=new ArrayList<>();
	
	Boolean flag=false;
	static String qus=new String("");
	static TestMysql ls;
	static ResultSet rs;
	static ResultSet as;
	static Random r = new Random();
	static int s=r.nextInt(10000)+1;
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
		panel.setBackground(Color.WHITE);
		
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		lun.setForeground(Color.BLUE);
		lun.setHorizontalAlignment(SwingConstants.CENTER);
		
		lun.setFont(new Font("华文行楷", Font.BOLD, 42));
		lun.setBackground(Color.BLUE);
		lun.setBounds(317, 13, 139, 71);
		panel.add(lun);
		
		scorelb.setFont(new Font("宋体", Font.BOLD, 17));
		scorelb.setBounds(94, 345, 72, 24);
		panel.add(scorelb);
	
		num.setHorizontalAlignment(SwingConstants.CENTER);
		num.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		num.setFont(new Font("宋体", Font.BOLD, 17));
		num.setBackground(new Color(0, 255, 255));
		num.setBounds(149, 176, 45, 34);
		panel.add(num);
		question.setForeground(new Color(51, 204, 255));
		
		question.setFont(new Font("华文楷体", Font.BOLD, 32));
		question.setBounds(226, 167, 205, 53);
		panel.add(question);
		
		answer = new JTextField();
		answer.setFont(new Font("华文楷体", Font.BOLD, 32));
		answer.setBounds(479, 167, 169, 53);
		panel.add(answer);
		answer.setColumns(10);
		
		time.setFont(new Font("宋体", Font.BOLD, 17));
		time.setBounds(95, 451, 186, 27);
		panel.add(time);
		
		languae.setEditable(true);
		languae.setBounds(632, 47, 72, 24);
		panel.add(languae);
		
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
				}
				else{
					
				}
			}
		});
		
		
		nextbtn.setFont(new Font("宋体", Font.BOLD, 17));
		nextbtn.setBounds(479, 334, 113, 46);
		panel.add(nextbtn);
		
		score.setFont(new Font("宋体", Font.BOLD, 17));
		score.setHorizontalAlignment(SwingConstants.CENTER);
		score.setBounds(197, 348, 72, 18);
		panel.add(score);

		//成绩图表事件监听
		drawlb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Chart demo = new Chart(scorelist);  
		        demo.setVisible(true);
			}
		});
		drawlb.setFont(new Font("宋体", Font.BOLD, 17));
		drawlb.setBounds(479, 451, 113, 27);
		panel.add(drawlb);
		startbtn.setForeground(new Color(0, 204, 51));
		startbtn.setBackground(new Color(255, 255, 255));
		
		//开始测试事件监听
		startbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {						
				startTime = new Date().getTime();
				this.timer();
				test();
			}
			
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
		startbtn.setFont(new Font("宋体", Font.BOLD, 17));
		startbtn.setBounds(135, 57, 113, 27);
		
		panel.add(startbtn);
	}	
	
	//获取题和答案
	public void test()
	{
		String str = answer.getText();
		if(str.equals("")) str = ""+0;
		ans=Integer.parseInt(str.trim());
		if(tans==ans&&ans!=0)
		{
			flag=true;
		}else flag=false;
		if(flag)
		{
			 sum = sum+5;
			score.setText(String.valueOf(sum));
		}else score.setText(String.valueOf(sum));
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

