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
		send = new JButton("发送");
		tarea = new JTextArea();
		input = new JTextArea();
		jsp = new JScrollPane(tarea);
		jsp2 = new JScrollPane(input);
		gbl = new GridBagLayout();
		gbc = new GridBagConstraints();
		
		//自动换行 
		input.setLineWrap(true);
		
		//显示栏禁止显示
		tarea.setEditable(false);
		tarea.setText("");
		tarea.setLineWrap(true);
		
		send.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
		
				if(!"".equals(input.getText().trim()) && !(input.getText() == null)){
					String msg=input.getText();
					long time=System.currentTimeMillis();
	                Date date = new Date(time);
	                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss E");
	                tarea.setCaretPosition(tarea.getText().length());
	            
	                	
	                    tarea.append("我  " + sdf.format(date) + " :\n" + msg+"\n");
	                    input.setText("");
	               
	                if(data.chatObject!=null){
	                	if(data.chatObject=="group"){
	                		MainOperation.grChatSend(data.out, msg, data.name);
	                	}else
	                		MainOperation.prChatSend(data.out,msg,data.chatObject,data.name);
	                }
	                
	                
	             
	                }
	                else
	                    JOptionPane.showMessageDialog(frame,"不能发送空消息！", "错误提示", JOptionPane.WARNING_MESSAGE);
	                    
	                    
	            }
	        });
		
		
        gbc.gridwidth = GridBagConstraints.REMAINDER;//将GridBagConstraints控制的GUI组件将会成为横向最后一个
        gbc.weightx = 1;//放大或缩小横向占比例
        gbc.weighty = 5;//放大或缩小纵向占比例
        gbc.fill = GridBagConstraints.BOTH;//所有组件都可以在横向、纵向上扩展
        panel.setLayout(gbl);
        gbl.setConstraints(jsp, gbc);//调用GridBagLayout方法构建jsp受控对象和GridBagConstraints对象之间的关系
        panel.add(jsp);
         
        
        gbc.weightx = 5;//放大或缩小横向占比例
        gbc.weighty = 1;//放大或缩小纵向占比例
        gbc.gridwidth = GridBagConstraints.RELATIVE; //默认值
        gbc.fill = GridBagConstraints.BOTH;//所有组件都可以在横向、纵向上扩展
        gbl.setConstraints(jsp2, gbc);
        panel.add(jsp2);
         
        
        gbc.gridwidth = GridBagConstraints.REMAINDER;//将GridBagConstraints控制的GUI组件将会成为横向最后一个
        gbc.weightx = 0;//放大或缩小横向占比例
        gbc.weighty = 0;//放大或缩小纵向占比例
        gbc.fill = GridBagConstraints.BOTH;
        gbl.setConstraints(send, gbc);
        panel.add(send);
         
        
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new WindowListener() {
			
			public void windowOpened(WindowEvent arg0) {
				// TODO 自动生成的方法存根
				
			}
			
			public void windowIconified(WindowEvent arg0) {
				// TODO 自动生成的方法存根
				
			}
			
			public void windowDeiconified(WindowEvent arg0) {
				// TODO 自动生成的方法存根
				
			}
			
			public void windowDeactivated(WindowEvent arg0) {
				// TODO 自动生成的方法存根
				
			}
			
			public void windowClosing(WindowEvent arg0) {
				// TODO 自动生成的方法存根
				     flag.i=1;
				
			}
			
			public void windowClosed(WindowEvent arg0) {
		
			         flag.i=1;
			         Thread.currentThread().stop();
			}
			public void windowActivated(WindowEvent arg0) {
				// TODO 自动生成的方法存根
				
			}
		});
		
		frame.setBounds(350, 300, 600, 400);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);//只关闭自己的窗口
		frame.setVisible(true);
        
		
	}
   
		
}
	


