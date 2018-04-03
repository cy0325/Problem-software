
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
	{//���ñ���  
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
	private JLabel lun = new JLabel("�� 1 ��");;
	private JLabel scorelb = new JLabel("�÷�:");
	private static JLabel question = new JLabel("____________");
	private JLabel num = new JLabel("1");
	private JLabel time = new JLabel("��ʱ��00:00");
	private JComboBox languae = new JComboBox();
	private JLabel score = new JLabel("0");
	private JButton nextbtn = new JButton("��һ��");
	private JButton drawlb = new JButton("�ɼ�ͼ��");
	private final JButton startbtn = new JButton("��ʼ����");
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
		
		lun.setFont(new Font("�����п�", Font.BOLD, 42));
		lun.setBackground(Color.BLUE);
		lun.setBounds(339, 80, 139, 71);
		panel.add(lun);
		
		scorelb.setFont(new Font("΢���ź�", Font.BOLD, 21));
		scorelb.setBounds(252, 339, 72, 46);
		panel.add(scorelb);
		num.setToolTipText("");
	
		num.setHorizontalAlignment(SwingConstants.CENTER);
		num.setBorder(new LineBorder(new Color(0, 204, 51), 3, true));
		num.setFont(new Font("����", Font.BOLD, 17));
		num.setBackground(new Color(0, 255, 255));
		num.setBounds(234, 209, 45, 34);
		panel.add(num);
		question.setForeground(new Color(51, 204, 255));
		
		question.setFont(new Font("���Ŀ���", Font.BOLD, 32));
		question.setBounds(298, 205, 276, 53);
		panel.add(question);
		
		answer = new JTextField();
		answer.setFont(new Font("���Ŀ���", Font.BOLD, 32));
		answer.setBounds(588, 205, 128, 53);
		panel.add(answer);
		answer.setColumns(10);
		
		time.setFont(new Font("΢���ź�", Font.BOLD, 21));
		time.setBounds(255, 429, 186, 46);
		panel.add(time);
		
		languae.setEditable(true);
		languae.setBounds(582, 68, 72, 24);
		panel.add(languae);
		nextbtn.setBackground(new Color(204, 204, 255));
		
		//��һ���¼�����
		nextbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isend;
				isend=nextbtn.getText().equals("�������");
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
						lun.setText("�� "+clun+" ��");
						scorelist.add(sum);	
						nextbtn.setText("�ύ");
						int value=JOptionPane.showConfirmDialog(null, "��ǰ�÷֣�"+sum+"���Ƿ������һ�֣�", "��ʾ��Ϣ", JOptionPane.YES_NO_OPTION);
						if (value==JOptionPane.YES_OPTION) {
							System.out.println("��ѡ������");
							nextbtn.setText("��һ��");}
							else if (value==JOptionPane.NO_OPTION) {
							nextbtn.setText("�������");
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
		
		
		nextbtn.setFont(new Font("����", Font.BOLD, 17));
		nextbtn.setBounds(479, 334, 113, 46);
		panel.add(nextbtn);
		score.setForeground(Color.RED);
		
		score.setFont(new Font("����", Font.BOLD, 22));
		score.setHorizontalAlignment(SwingConstants.CENTER);
		score.setBounds(298, 346, 56, 34);
		panel.add(score);
		drawlb.setBackground(new Color(204, 204, 255));

		//�ɼ�ͼ���¼�����
		drawlb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Chart demo = new Chart(scorelist);  
		        demo.setVisible(true);
			}
		});
		drawlb.setFont(new Font("����", Font.BOLD, 17));
		drawlb.setBounds(479, 432, 113, 46);
		panel.add(drawlb);
		startbtn.setForeground(new Color(255, 0, 51));
		startbtn.setBackground(new Color(255, 204, 204));
		
		//��ʼ�����¼�����
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

								time.setText("��ʱ: " + (mm < 10 ? "0" + mm : "" + mm) + ":" + (ss < 10 ? "0" + ss : "" + ss));
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}.start();
			}

		});
		startbtn.setFont(new Font("����", Font.BOLD, 20));
		startbtn.setBounds(164, 58, 128, 41);
		
		panel.add(startbtn);
	}	
	
	//��ȡ��ʹ�
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

