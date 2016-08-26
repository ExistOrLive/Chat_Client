package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.dyno.visual.swing.layouts.GroupLayout;

import chat.ChatData;
import chat.ListenGRThread_2;
//VS4E -- DO NOT REMOVE THIS LINE!
public class mianchat extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	private ChatData data=null;
	
	

	
	
	public mianchat(ChatData data) {
		this.data=data;
		initComponents();
		setTitle("������");
		contentPanel = new JPanel();
		setContentPane(contentPanel);
		contentPanel.setLayout(new BoxLayout(contentPanel , BoxLayout.PAGE_AXIS));
		JPanel peasonalPanel = new JPanel();
		JButton peasonalButton = new JButton("��������");
		peasonalButton.setFont(new Font("΢���ź�",Font.PLAIN,18));
		peasonalButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				try {
					new friendlist(data).frilist();
				} catch (IOException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
			
			}
		});
		peasonalPanel.add(peasonalButton);
		
		
		JPanel groupPanel = new JPanel();
		JButton groupButton = new JButton("��������");
		groupButton.setFont(new Font("΢���ź�",Font.PLAIN,18));
		groupPanel.add(groupButton );
		groupButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				data.chatObject="group";
				
			   Thread thread=new ListenGRThread_2(data);
			   thread.setDaemon(true);
			   thread.start();
			
			}
		});		
		
		contentPanel.add(peasonalPanel);
		contentPanel.add(groupPanel);
		
		setBounds(40 , 50 , 300, 200);
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

