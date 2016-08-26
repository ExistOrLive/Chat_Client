package chat;

import gui.p2pchat;




public class ListenPRThread_2 extends Thread {

	private ChatData data = null;
    private p2pchat chat=null;
    private Flag flag;

	public ListenPRThread_2(ChatData data,String name) {
		flag=new Flag();
		this.data = data;
		chat=new p2pchat(name,data,flag);
	}

	public void run() {
		String line = null;
		
		while (true) {
			 if(flag.i==1){
	        	 break;
	         }
		         if ((line = data.prDeque.pollFirst()) != null) {
						String result = MainOperation.parsePrivate(line, data.chatObject);

						System.out.println(result);
						
					    chat.get(result);

					}
		}

	}
	
}
