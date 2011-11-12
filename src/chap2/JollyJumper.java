package chap2;

import java.io.*;
import java.util.*;

/*
 * JollyJumper- 
 * PC/UVa IDs: 110201/10038
 */
public class JollyJumper {
	
	public static void main(String[] args){
		Scanner scn=new Scanner(System.in);
		
		while(scn.hasNextInt()){
			int n=scn.nextInt();
			int count=n;
			int cur=scn.nextInt(), prev, diff;
			BitSet bs=new BitSet(n);
			boolean good=true;
			n--;
			
			while(n--!=0){
				prev=cur;
				cur=scn.nextInt();
				diff=Math.abs(prev-cur);
				if(diff==0 || diff>=count || bs.get(diff))
					good=false;
				else
					bs.set(diff);
			}
			
			System.out.println(good ? "Jolly" : "Not jolly");
		}
		
	}
}
