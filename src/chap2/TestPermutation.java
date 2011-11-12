package chap2;

import java.util.PriorityQueue;

public class TestPermutation {
	public static void main(String[] args){
		PriorityQueue<Integer> p=new PriorityQueue<Integer>();
		p.add(5);
		p.add(4);
		p.add(10);
		p.add(-1);
		p.add(2);
		
		while(p.size()!=0)
			System.out.println(p.remove());
	}
}
