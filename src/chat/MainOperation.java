package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import data.ChatProtocal;

public class MainOperation {
     public static int login(BufferedReader in,PrintWriter out,String name,String passwd) throws IOException{
		
        StringBuffer buffer=new StringBuffer();
        String result=null;
        buffer.append(ChatProtocal.LOGIN);
        buffer.append(name+ChatProtocal.DELIMITER);
        buffer.append(passwd);
        buffer.append(ChatProtocal.LOGIN);
        out.println(buffer.toString());
        out.flush();
        if((result=in.readLine())!=null){
        	if(result.equals(ChatProtocal.SUCCESS_LOGIN))
        		return 1;
        	else if(result.equals(ChatProtocal.WR_PASSWD))
        		return 2;
        	else if(result.equals(ChatProtocal.AC_NOTEX))
        		return 3;
        	else if(result.equals(ChatProtocal.LOGINED))
        		return 4;
        }
        return 0;
      }
     
     
     public static int register(BufferedReader in,PrintWriter out,String name,String passwd,int age,String sex,String num) throws IOException{
    	 StringBuffer buffer=new StringBuffer();
         String result=null;
         buffer.append(ChatProtocal.REGISTER);
         buffer.append(name+ChatProtocal.DELIMITER);
         buffer.append(passwd+ChatProtocal.DELIMITER);
         buffer.append(age+ChatProtocal.DELIMITER);
         buffer.append(sex+ChatProtocal.DELIMITER);
         buffer.append(num);
         buffer.append(ChatProtocal.REGISTER);
         out.println(buffer.toString());
         out.flush();
         if((result=in.readLine())!=null){
         	if(result.equals(ChatProtocal.REGISTER_SUCCESS))
         		return 1;
         	else if(result.equals(ChatProtocal.REGISTER_FAILED))
         		return 2;
         	else if(result.equals(ChatProtocal.AC_EXIST))
         		return 3;
         
         }
         return 0;
    	 
     }
     
     public static void prChatSend(PrintWriter out,String msg,String name,String localname){
    	 
    	 StringBuffer buffer=new StringBuffer();
         buffer.append(ChatProtocal.PR_MSG);
         buffer.append(name+ChatProtocal.DELIMITER);
         buffer.append(localname+ChatProtocal.DELIMITER);
         buffer.append(msg+ChatProtocal.DELIMITER);
         buffer.append(System.currentTimeMillis());
         buffer.append(ChatProtocal.PR_MSG);
         out.println(buffer.toString());
         out.flush();
    	 
     }
     
     public static void grChatSend(PrintWriter out,String msg,String localname){
    	 StringBuffer buffer=new StringBuffer();
         buffer.append(ChatProtocal.GR_MSG);
         buffer.append(localname+ChatProtocal.DELIMITER);
         buffer.append(msg+ChatProtocal.DELIMITER);
         buffer.append(System.currentTimeMillis());
         buffer.append(ChatProtocal.GR_MSG);
         out.println(buffer.toString());
         out.flush();
     }
     
     
     public static String[] requestForList(ChatData data,PrintWriter out) throws IOException, InterruptedException{
 
		String line=null;
    	 out.println(ChatProtocal.REQUESTFORLIST);
		out.flush();
		int i=0;
		while(true){
			line=data.Deque.pollFirst();
			if(line!=null){
				if(line!=ChatProtocal.REQUESTFORLIST){
			    	   String msg=getRealMsg(line);
					   String[] result=msg.split(ChatProtocal.DELIMITER);
				       return result;
					}else
						return null;
				
			}
			
			if(i==100){
				i=0;
				out.println(ChatProtocal.REQUESTFORLIST);
				out.flush();
			}
			
		}
		
     }
     
     
     public static String   parseGroup(String line){
    	 String[] msgs=line.split(ChatProtocal.DELIMITER);
    	 if(msgs.length!=3){
    		 System.out.println("error 群组信息格式解析");
    		 return "error 群组信息格式解析\n";
    		 
    	 }else{
    		 
    		 long time=Long.parseLong(msgs[2]);
    		 SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss E");
    		 String date=format.format(new Date(time));
    		 StringBuffer buffer=new StringBuffer();
    		 buffer.append(msgs[0]+" "+date+ " :\n" + msgs[1]+"\n");
    		 return buffer.toString();
    		 
    	  }
    	 
     }
     public static String   parsePrivate(String line,String name){

    	 String[] msgs=line.split(ChatProtocal.DELIMITER);
    	 if(msgs.length!=4){
    		 System.out.println("error 私人对话信息格式解析");
    		 return "error 私人对话信息格式解析\n";
    		 
    	 }else{
    		 if(msgs[1]!=name){
    		     long time=Long.parseLong(msgs[3]);
    		     SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
    		     String date=format.format(new Date(time));
    		     StringBuffer buffer=new StringBuffer();
    		     buffer.append(msgs[1]+" "+date+ " :\n" + msgs[2]+"\n");
    		     return buffer.toString();
    		 }
    		 else
    			 return null;
    	  }
    	 
     }
     
  public static String getRealMsg(String line) {

 		return line.substring(ChatProtocal.PROTOCAL_LENGTH, line.length()
 				- ChatProtocal.PROTOCAL_LENGTH);
 	}
     

}
