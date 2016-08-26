package gui;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import chat.ChatData;
import chat.ListenPRThread_2;
import chat.MainOperation;



@SuppressWarnings("serial")
public class friendlist extends JFrame 
{ 
	
	
	
	private static boolean isHidden;
    private JPanel cp=new JPanel(); 
	private JTree jtree; 
	private DefaultMutableTreeNode root;
	private ChatData data=null;
  
	
	public friendlist(ChatData data){
		this.data=data;
	}
	public void frilist() throws IOException, InterruptedException 
	{ 
		setTitle("ͨ Ѷ ¼"); 
		cp=(JPanel)this.getContentPane(); 
		cp.setLayout(new BorderLayout()); 
		root = new DefaultMutableTreeNode("���ߺ���"); 
		createTree(root); 
		jtree=new JTree(root);  
		jtree.addMouseListener(new chatMouseListener());
		cp.add(jtree,BorderLayout.CENTER);
		
		
		
		/*
		final Timer timer = new Timer(500, new ActionListener() {  
			  public void actionPerformed(ActionEvent event) {  
			  isHidden = !isHidden;  
			  jtree.updateUI();  
			  }  
			  });  
			  timer.start();  
			  jtree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {  
			  public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {  
			  DefaultMutableTreeNode node = (DefaultMutableTreeNode)jtree.getLastSelectedPathComponent();  
			  String name=node.toString();  
			  
			  //��˸����
			  if(node.isLeaf()){  
			     if(name.equals("��־��")){  
			      timer.stop();  
			      jtree.setCellRenderer(new MyRenderer());  
			      }  
			     }  
			  }  
			  });  
		
			  */
			  
			  
			  
	    setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setSize(300,500); 
		setVisible(true); 
		
	} 

	public void createTree (DefaultMutableTreeNode root) throws IOException, InterruptedException 
	{   
		DefaultMutableTreeNode frinode =null; 
        String[] list=MainOperation.requestForList(data, data.out);
		//String[] list={"����","������"};
        if(list!=null){
		for(int i=0 ; i<list.length ; i++){
			if(!list[i].equals(data.name)){
			frinode = new DefaultMutableTreeNode(list[i]);
			root.add(frinode);
			}else{
				frinode = new DefaultMutableTreeNode("��");
				root.add(frinode);	
			}
		}
        }
	} 
	protected void processWindowEvent(WindowEvent e) 
	{ 
		if(e.getID()==WindowEvent.WINDOW_CLOSING) 
		{ 
			dispose();
		} 
	}	 
	public class chatMouseListener extends MouseAdapter{		
		public void mouseClicked(MouseEvent e ){
		
				if(e.getClickCount() >= 2)
				{
					//�ж��ǲ���Ҷ�ӽ��
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) 
							jtree.getLastSelectedPathComponent(); 
					if(node.isLeaf()){
						//��������ҳ��
		          String name=(String)node.getUserObject();
		               data.chatObject=new String(name);
						Thread thread=new ListenPRThread_2(data,data.chatObject);
						thread.setDaemon(true);
						thread.start();
					}
				}
				
			}
		}
	
}