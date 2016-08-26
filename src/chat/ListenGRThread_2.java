package chat;

import gui.p2pchat;


public class ListenGRThread_2 extends Thread {

	private ChatData data = null;
    private p2pchat chat=null;
    private Flag flag;

	public ListenGRThread_2(ChatData data) {
		flag=new Flag();
	    this.chat=new p2pchat("ÁÄÌìÊÒ",data,flag);
		this.data = data;
	}

	public void run() {
		String line = null;
		while (true) {
			 if(flag.i==1){
	        	 break;
	         }
			
			if ((line = data.grDeque.pollFirst()) != null) {

				String result = MainOperation.parseGroup(line);

				System.out.println(result);
				
			    chat.get(result);

			}

		}

	}

}
