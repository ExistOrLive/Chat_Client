package chat;

import java.io.BufferedReader;
import java.io.IOException;

public class ListenThread extends Thread{
   
	private BufferedReader in=null;
	private ChatData data=null;
	
	public ListenThread(ChatData data){
		this.in=data.in;
		this.data=data;
	}
	
	public void run(){
		String line=null;
		String msg;
		try {
			while((line=in.readLine())!=null){
				if(line.startsWith(ChatProtocal.PR_MSG)&&line.endsWith(ChatProtocal.PR_MSG)){
				   msg=MainOperation.getRealMsg(line);
				   if(data.prDeque.size()>=10)
					   data.prDeque.clear();
				   data.prDeque.offerLast(msg);
				}else if(line.startsWith(ChatProtocal.GR_MSG)&&line.endsWith(ChatProtocal.GR_MSG)){
					msg=MainOperation.getRealMsg(line);
					   if(data.grDeque.size()>=10)
						   data.grDeque.clear();
					   data.grDeque.offerLast(msg);
				}else{
				
					   if(data.Deque.size()>=5)
						   data.Deque.clear();
					   data.Deque.offerLast(line);
					
				}
				
			  }
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		
		
		
	}
}
