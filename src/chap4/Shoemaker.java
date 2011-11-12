package chap4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Shoemaker {
	
	static BufferedReader br=null;
	
	public static String readLine() throws IOException{
		return br.readLine();
	}
	
	public static void main(String[] args) throws IOException{
		/*
		File f=new File("shoemaker.in");
		System.setIn(new FileInputStream(f));
		*/
		br=new BufferedReader(new InputStreamReader(System.in));
		
		
		Comparator<Job> jobComparator=new Comparator<Job>() {

			@Override
			public int compare(Job arg0, Job arg1) {
				int ret=arg1.penalty*arg0.time-arg0.penalty*arg1.time;
				if(ret==0){
					ret=arg0.index-arg1.index;
				}
				
				return ret;
			}
		};
		
		int cases=Integer.parseInt(readLine().trim()), currCase=0;
		
		while(currCase++<cases){
			readLine();
			int count=Integer.parseInt(readLine().trim());
			Job[] orders=new Job[count];
			Map<Job, Integer> indMap=new HashMap<Job, Integer>();
			
			for(int i=0;i<count;i++){
				String[] inp=readLine().trim().split(" ");
				orders[i]=new Job(Integer.parseInt(inp[0].trim()), Integer.parseInt(inp[1].trim()), i+1);
			}
			
			Arrays.sort(orders, jobComparator);
			
			StringBuilder out=new StringBuilder();
			for(int i=0;i<orders.length;i++){
				out.append(orders[i].index);
				out.append(' ');
			}
			
			if(currCase!=1)
				System.out.println();
			System.out.println(out.toString().trim());
			
		}
		
	}
}

class Job{
	public int time=-1;
	public int penalty=-1;
	public int index=-1;
	public Job(int time, int penalty, int index){
		this.time=time;
		this.penalty=penalty;
		this.index=index;
	}
}
