package chat;

import java.io.BufferedReader;
import java.io.PrintWriter;


public class ChatData {


	public BufferedReader in=null;
	public PrintWriter out=null;
	public String name=null;
	public String chatObject=null;
    public SynchronizedArrayDeque<String> grDeque=new SynchronizedArrayDeque<String>(20);
    public SynchronizedArrayDeque<String> prDeque=new SynchronizedArrayDeque<String>(20);
    public SynchronizedArrayDeque<String> Deque=new SynchronizedArrayDeque<String>(10);  
    public ChatData(BufferedReader in,PrintWriter out){
    	
    	this.in=in;
    	this.out=out;
    	
    }
	
}
