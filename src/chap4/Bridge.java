package chap4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;


public class Bridge {

	/**
	 * @param args
	 */
	
	static BufferedReader br=null;
	
	static String readLine() throws IOException{
		return br.readLine();
	}
	public static void main(String[] args) throws IOException {
		/*
		File f=new File("bridge.in");
		System.setIn(new FileInputStream(f));
		*/
		br=new BufferedReader(new InputStreamReader(System.in));
		
		int testCases=Integer.parseInt(readLine().trim()), currTest=0;
		
		
		while(currTest++<testCases){
			
			readLine();
			
			int count=Integer.parseInt(readLine().trim());
			List<Integer> arr=new ArrayList<Integer>();
			for(int i=0;i<count;i++){
				arr.add(Integer.parseInt(readLine().trim()));
			}
			
			Collections.sort(arr);
			
			long sum=0;
			StringBuffer sb=new StringBuffer();
			TreeSet<Integer> other=new TreeSet<Integer>();
			
			while(arr.size()!=0){
				int val1, val2, ret;
				switch(arr.size()){
				case 1:
					ret=arr.remove(0);
					sum+=ret;
					sb.append(ret+"\n");
					other.add(ret);
					break;
				case 2:
					val1=arr.remove(0);
					val2=arr.remove(0);
					sb.append(val1+" "+val2+"\n");
					sum+=val2;
					other.add(val1);
					other.add(val2);
					break;
				case 3:
					val1=arr.get(0);
					val2=arr.remove(1);
					sb.append(val1+" "+val2+"\n");
					sum+=val2;
					other.add(val2);
					sum+=val1;
					sb.append(val1+"\n");
					break;
				default:
					if((arr.get(1)*2)<(arr.get(0)+arr.get(arr.size()-2))){
						sum+=(arr.get(0)+arr.get(1)*2+arr.get(arr.size()-1));
						sb.append(arr.get(0)+" "+arr.get(1)+"\n");
						sb.append(arr.get(0)+"\n");
						sb.append(arr.get(arr.size()-2)+" "+arr.get(arr.size()-1)+"\n");
						sb.append(arr.get(1)+"\n");
						arr.remove(arr.size()-1);
						arr.remove(arr.size()-1);
					}
					else{
						sum+=(arr.get(0)*2+arr.get(arr.size()-2)+arr.get(arr.size()-1));
						sb.append(arr.get(0)+" "+arr.get(arr.size()-1)+"\n");
						sb.append(arr.get(0)+"\n");
						sb.append(arr.get(0)+" "+arr.get(arr.size()-2)+"\n");
						sb.append(arr.get(0)+"\n");
						arr.remove(arr.size()-1);
						arr.remove(arr.size()-1);
					}
					break;
				}
			}
			
			

			if(currTest!=1)
				System.out.println();
			System.out.println(sum);
			System.out.print(sb);
			
		}
		
		
	}

}
