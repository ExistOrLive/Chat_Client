package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import chat.ChatData;
import chat.Flag;
import chat.MainOperation;

public class p2pchat {
	private JFrame frame;
	private  JPanel panel;
	private  JButton send;
	private  JTextArea tarea;
	private  JScrollPane jsp;
	private  JScrollPane jsp2;
	private  JTextArea input;
	private  GridBagLayout gbl;
	private  GridBagConstraints gbc;
	

	
	public void get(String line){
		
		tarea.append(line);
		
		
	}
	
	
	
	
	public p2pchat(String username,ChatData data,Flag flag){
        frame = new JFrame(username);
		panel = new JPanel();
		new JLabel();
		send = new JButton("����");
		tarea = new JTextArea();
		input = new JTextArea();
		jsp = new JScrollPane(tarea);
		jsp2 = new JScrollPane(input);
		gbl = new GridBagLayout();
		gbc = new GridBagConstraints();
		
		//�Զ����� 
		input.setLineWrap(true);
		
		//��ʾ����ֹ��ʾ
		tarea.setEditable(false);
		tarea.setText("");
		tarea.setLineWrap(true);
		
		send.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
		
				if(!"".equals(input.getText().trim()) && !(input.getText() == null)){
					String msg=input.getText();
					long time=System.currentTimeMillis();
	                Date date = new Date(time);
	                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss E");
	                tarea.setCaretPosition(tarea.getText().length());
	            
	                	
	                    tarea.append("��  " + sdf.format(date) + " :\n" + msg+"\n");
	                    input.setText("");
	               
	                if(data.chatObject!=null){
	                	if(data.chatObject=="group"){
	                		MainOperation.grChatSend(data.out, msg, data.name);
	                	}else
	                		MainOperation.prChatSend(data.out,msg,data.chatObject,data.name);
	                }
	                
	                
	             
	                }
	                else
	                    JOptionPane.showMessageDialog(frame,"���ܷ��Ϳ���Ϣ��", "������ʾ", JOptionPane.WARNING_MESSAGE);
	                    
	                    
	            }
	        });
		
		
        gbc.gridwidth = GridBagConstraints.REMAINDER;//��GridBagConstraints���Ƶ�GUI��������Ϊ�������һ��
        gbc.weightx = 1;//�Ŵ����С����ռ����
        gbc.weighty = 5;//�Ŵ����С����ռ����
        gbc.fill = GridBagConstraints.BOTH;//��������������ں�����������չ
        panel.setLayout(gbl);
        gbl.setConstraints(jsp, gbc);//����GridBagLayout��������jsp�ܿض����GridBagConstraints����֮��Ĺ�ϵ
        panel.add(jsp);
         
        
        gbc.weightx = 5;//�Ŵ����С����ռ����
        gbc.weighty = 1;//�Ŵ����С����ռ����
        gbc.gridwidth = GridBagConstraints.RELATIVE; //Ĭ��ֵ
        gbc.fill = GridBagConstraints.BOTH;//��������������ں�����������չ
        gbl.setConstraints(jsp2, gbc);
        panel.add(jsp2);
         
        
        gbc.gridwidth = GridBagConstraints.REMAINDER;//��GridBagConstraints���Ƶ�GUI��������Ϊ�������һ��
        gbc.weightx = 0;//�Ŵ����С����ռ����
        gbc.weighty = 0;//�Ŵ����С����ռ����
        gbc.fill = GridBagConstraints.BOTH;
        gbl.setConstraints(send, gbc);
        panel.add(send);
         
        
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new WindowListener() {
			
			public void windowOpened(WindowEvent arg0) {
				// TODO �Զ����ɵķ������
				
			}
			
			public void windowIconified(WindowEvent arg0) {
				// TODO �Զ����ɵķ������
				
			}
			
			public void windowDeiconified(WindowEvent arg0) {
				// TODO �Զ����ɵķ������
				
			}
			
			public void windowDeactivated(WindowEvent arg0) {
				// TODO �Զ����ɵķ������
				
			}
			
			public void windowClosing(WindowEvent arg0) {
				// TODO �Զ����ɵķ������
				     flag.i=1;
				
			}
			
			public void windowClosed(WindowEvent arg0) {
		
			         flag.i=1;
			         Thread.currentThread().stop();
			}
			public void windowActivated(WindowEvent arg0) {
				// TODO �Զ����ɵķ������
				
			}
		});
		
		frame.setBounds(350, 300, 600, 400);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);//ֻ�ر��Լ��Ĵ���
		frame.setVisible(true);
        
		
	}
   
		
}
	


