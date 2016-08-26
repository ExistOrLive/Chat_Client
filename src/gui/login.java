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
		setTitle("����ϵͳ��¼");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));

		
		//�������û���������
		JPanel usernamePanel = new JPanel();
		contentPane.add(usernamePanel);
		JLabel usernameLable = new JLabel("�û���");
		usernameLable.setFont(new Font("΢���ź�",Font.PLAIN,15));
		usernamePanel.add(usernameLable);
		
		usernameTextField=new JTextField();
		usernameTextField.setFont(new Font("΢���ź�",Font.PLAIN,15));
		usernamePanel.add(usernameTextField);
		usernameTextField.setColumns(10);
		
		
		//���������롱����
		JPanel passwordPanel = new JPanel();
		contentPane.add(passwordPanel);
		JLabel passwordLabel = new JLabel("��   ��");
		passwordLabel.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		passwordPanel.add(passwordLabel);
		userpasswdField = new JPasswordField();
		userpasswdField.setColumns(10);
		userpasswdField.setFont(new Font("΢���ź�",Font.PLAIN,15));
		passwordPanel.add(userpasswdField);
		
		
		//��������¼����ť
		JButton loginButton = new JButton("��¼");
		loginButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				//new mainChat();
				
				//������ʾ��Ϣ
	           
				String name=usernameTextField.getText();
				String passwd=userpasswdField.getText();
				if(name.equals("")||passwd.equals("")){
					 JOptionPane.showMessageDialog(null, "�û�����������Ϊ��", "����", JOptionPane.ERROR_MESSAGE);
				}else {
				try {
					int result=MainOperation.login(data.in, data.out, name, passwd); 
					switch(result){
					     case 1: data.name=name;new mianchat(data);Thread thread=new ListenThread(data); thread.setDaemon(true); thread.start(); dispose(); System.out.println("��½�ɹ�"); break;
					     case 2:JOptionPane.showMessageDialog(null, "�������", "����", JOptionPane.ERROR_MESSAGE);break;
					     case 3:JOptionPane.showMessageDialog(null, "�˺Ų����ڣ���ע��", "����", JOptionPane.ERROR_MESSAGE);break;
					     case 4:JOptionPane.showMessageDialog(null, "���˺������˵�¼", "����", JOptionPane.ERROR_MESSAGE);break;
					     default:System.out.println("error");
					}
					
				} catch (IOException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
				}
				
			
			}
		});
		
		//������ע�ᡱ��ť
		JButton resiterButton = new JButton("ע��");
	    resiterButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				new register(data);
			
			}
		});
	    
	    
	   JPanel JButtonPanel = new JPanel();
	   JButtonPanel.add(loginButton);
	   JButtonPanel.add(resiterButton);
	   
	   contentPane.add(JButtonPanel);
	   
		pack();
		
		//���ô��ڴ�С
		setBounds(50, 60 ,350 , 200);
		//����
		setLocationRelativeTo(null);
		//���ô�С����
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//���ÿɼ�
		setVisible(true);
		
		
	    
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		setSize(500	, 650);
	}

}
