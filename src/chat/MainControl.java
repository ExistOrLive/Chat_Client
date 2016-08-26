 package chat;

import gui.login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainControl {

	private Socket socket=null;
	private ChatData data=null;
	
	@SuppressWarnings("unused")
	public MainControl(String ip,int port){
		BufferedReader in=null;
		PrintWriter out=null;
		BufferedReader input=null;
		try {
			socket=new Socket(ip,port);
		     in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		     out=new PrintWriter(socket.getOutputStream());
	         input=new BufferedReader(new InputStreamReader(System.in));
			 data=new ChatData(in,out);
			System.out.println("*******服务器连通！*******");
			
		} catch (UnknownHostException e) {
		    System.out.println("无法连接服务器！请检查网络连接和ip");	
		    try{
		    	if(in!=null)
		    		in.close();
		    	if(out!=null)
		    		out.close();
		    	if(input!=null)
		    		input.close();
		    	if(socket!=null)
		    		socket.close();
		    	}catch(IOException ex){
		    		e.printStackTrace();
		    	}
			e.printStackTrace();
		} catch (IOException e) {
		    try{
		    	if(in!=null)
		    		in.close();
		    	if(out!=null)
		    		out.close();
		    	if(input!=null)
		    		input.close();
		    	if(socket!=null)
		    		socket.close();
		    	}catch(IOException ex){
		    		e.printStackTrace();
		    	}
			e.printStackTrace();
		}
		
	}
	
	public void run() throws IOException{
	    
		
		new login(data);
		
		
	}
	
	public static void main(String[] args) throws IOException {
		// TODO 自动生成的方法存根
       MainControl client=new MainControl("127.0.0.1",7000);
       client.run();
		
	}

}
