package chat;

import java.util.ArrayDeque;

public class SynchronizedArrayDeque<V> {

	private ArrayDeque<V> deque=null;
	
	public SynchronizedArrayDeque(int num){
	     deque=new ArrayDeque<V>(num);	
	}
	
	public synchronized V pollFirst(){
		
		return deque.pollFirst();
	}
	
	public synchronized boolean offerLast(V v){
		
		return deque.offerLast(v);
	}
	
	public int size(){
		return deque.size();
	}
	
	public synchronized void clear(){
		
		deque.clear();
	}
	
	
}
