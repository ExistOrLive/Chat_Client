package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.dyno.visual.swing.layouts.GroupLayout;

import chat.ChatData;
import chat.ListenThread;
import chat.MainOperation;




//VS4E -- DO NOT REMOVE THIS LINE!
public class login extends JFrame {
    
	private ChatData data=null;
	private static final long serialVersionUID = 1L;
    private JPanel contentPane ;
	private JTextField usernameTextField;
	private JPasswordField userpasswdField;
	

	public login(ChatData data) {
		this.data=data;
		initComponents();
		setTitle("嗨聊系统登录");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));

		
		//创建“用户名”容器
		JPanel usernamePanel = new JPanel();
		contentPane.add(usernamePanel);
		JLabel usernameLable = new JLabel("用户名");
		usernameLable.setFont(new Font("微软雅黑",Font.PLAIN,15));
		usernamePanel.add(usernameLable);
		
		usernameTextField=new JTextField();
		usernameTextField.setFont(new Font("微软雅黑",Font.PLAIN,15));
		usernamePanel.add(usernameTextField);
		usernameTextField.setColumns(10);
		
		
		//创建“密码”容器
		JPanel passwordPanel = new JPanel();
		contentPane.add(passwordPanel);
		JLabel passwordLabel = new JLabel("密   码");
		passwordLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		passwordPanel.add(passwordLabel);
		userpasswdField = new JPasswordField();
		userpasswdField.setColumns(10);
		userpasswdField.setFont(new Font("微软雅黑",Font.PLAIN,15));
		passwordPanel.add(userpasswdField);
		
		
		//创建“登录”按钮
		JButton loginButton = new JButton("登录");
		loginButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				//new mainChat();
				
				//错误提示信息
	           
				String name=usernameTextField.getText();
				String passwd=userpasswdField.getText();
				if(name.equals("")||passwd.equals("")){
					 JOptionPane.showMessageDialog(null, "用户名或者密码为空", "错误", JOptionPane.ERROR_MESSAGE);
				}else {
				try {
					int result=MainOperation.login(data.in, data.out, name, passwd); 
					switch(result){
					     case 1: data.name=name;new mianchat(data);Thread thread=new ListenThread(data); thread.setDaemon(true); thread.start(); dispose(); System.out.println("登陆成功"); break;
					     case 2:JOptionPane.showMessageDialog(null, "密码错误", "错误", JOptionPane.ERROR_MESSAGE);break;
					     case 3:JOptionPane.showMessageDialog(null, "账号不存在，请注册", "错误", JOptionPane.ERROR_MESSAGE);break;
					     case 4:JOptionPane.showMessageDialog(null, "该账号已有人登录", "错误", JOptionPane.ERROR_MESSAGE);break;
					     default:System.out.println("error");
					}
					
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				}
				
			
			}
		});
		
		//创建“注册”按钮
		JButton resiterButton = new JButton("注册");
	    resiterButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				new register(data);
			
			}
		});
	    
	    
	   JPanel JButtonPanel = new JPanel();
	   JButtonPanel.add(loginButton);
	   JButtonPanel.add(resiterButton);
	   
	   contentPane.add(JButtonPanel);
	   
		pack();
		
		//设置窗口大小
		setBounds(50, 60 ,350 , 200);
		//居中
		setLocationRelativeTo(null);
		//设置大小不变
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置可见
		setVisible(true);
		
		
	    
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		setSize(500	, 650);
	}

}
