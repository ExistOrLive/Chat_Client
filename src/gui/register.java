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
import chat.MainOperation;

//VS4E -- DO NOT REMOVE THIS LINE!
public class register extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	private JTextField usernameTextField;
	private JPasswordField userpassPasswordField1;
	private JPasswordField userpassPasswordField2;
	private JTextField emailField;
	private JLabel tipLable = new JLabel();
	private ChatData data = null;

	public register(ChatData data) {
		this.data = data;
		initComponents();
		setTitle("用户注册");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 创建主容器
		contentPanel = new JPanel();
		setContentPane(contentPanel);
		contentPanel
				.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));

		// 创建“用户名”容器
		JPanel usernamePanel = new JPanel();
		contentPanel.add(usernamePanel);
		JLabel usernameLable = new JLabel("用  户  名");
		usernameLable.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		usernamePanel.add(usernameLable);
		usernameTextField = new JTextField();
		// usernameTextField.setToolTipText("最多15个字符");

		usernameTextField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		usernameTextField.setColumns(10);
		usernamePanel.add(usernameTextField);

		// 创建“输入密码”容器
		JPanel passwordPanel1 = new JPanel();
		contentPanel.add(passwordPanel1);
		JLabel passwordJLabel1 = new JLabel("输入密码");
		passwordJLabel1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		userpassPasswordField1 = new JPasswordField();
		userpassPasswordField1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		userpassPasswordField1.setColumns(10);
		passwordPanel1.add(passwordJLabel1);
		passwordPanel1.add(userpassPasswordField1);

		// 创建“再输一次”容器
		JPanel passwordPanel2 = new JPanel();
		contentPanel.add(passwordPanel2);
		JLabel passwordLabel2 = new JLabel("再输一次");
		passwordLabel2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		userpassPasswordField2 = new JPasswordField();
		userpassPasswordField2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		userpassPasswordField2.setColumns(10);
		passwordPanel2.add(passwordLabel2);
		passwordPanel2.add(userpassPasswordField2);

		// 创建“联系邮箱”容器
		JPanel emailPanel = new JPanel();
		contentPanel.add(emailPanel);
		JLabel emailLabel = new JLabel("联系号码");
		emailLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		emailField = new JTextField();
		emailField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		emailField.setColumns(10);
		emailPanel.add(emailLabel);
		emailPanel.add(emailField);

		// 创建按键容器
		JPanel buttonPanel = new JPanel();

		JButton confirmButton = new JButton("确认");
		confirmButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				String name = usernameTextField.getText();
				String passwd1 = userpassPasswordField1.getText();
						
				String passwd2 = userpassPasswordField2.getText();
						
				System.out.println(passwd1);
				System.out.println(passwd2);
				String telnum = emailField.getText();
				if (name.equals("") || passwd1.equals("") || passwd2 .equals("")
						|| telnum.equals("")) {
					JOptionPane.showMessageDialog(null, "信息不能为空", "错误",
							JOptionPane.ERROR_MESSAGE);
				} else if (!passwd1.equals(passwd2)) {

					JOptionPane.showMessageDialog(null, "密码输入不一致", "错误",
							JOptionPane.ERROR_MESSAGE);

				} else {
					try {
						int result=MainOperation.register(data.in, data.out, name,
								passwd1, 20, "男", telnum);
						switch(result){
						     case 1:  JOptionPane.showMessageDialog(null, "注册成功", "提示信息",
										JOptionPane.DEFAULT_OPTION); 
				                      break;   
						     case 2: JOptionPane.showMessageDialog(null, "注册失败", "错误",
										JOptionPane.ERROR_MESSAGE); 
						             break;
						     case 3: JOptionPane.showMessageDialog(null, "该用户名已存在", "错误",
										JOptionPane.ERROR_MESSAGE); 
				                          break;
				             default :System.out.println("error");
						    	 
						}
					} catch (IOException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
				}
			}
		});

		JButton cancelButton = new JButton("取消");
		cancelButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				dispose();
			}
		});
		buttonPanel.add(confirmButton);
		buttonPanel.add(cancelButton);

		contentPanel.add(buttonPanel);
		pack();
		setBounds(30, 50, 300, 350);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setVisible(true);
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		setSize(320, 240);
	}

}
