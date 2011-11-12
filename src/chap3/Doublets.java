package chap3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Doublets {
	
	static BufferedReader br=null;
	
	static String readline() throws IOException{
		return br.readLine();
	}
	
	public static void main(String[] args) throws IOException{
		
		//File f=new File("doub.in");
		//System.setIn(new FileInputStream(f));
		
		br=new BufferedReader(new InputStreamReader(System.in));
		
		List<String> input=new ArrayList<String>();
		int size=0;
		String inp=readline();
		
		Map<String, Integer> indMap=new HashMap<String, Integer>();
		Map<Integer, List<Integer>> sizeMap=new HashMap<Integer, List<Integer>>();
		
		
		
		while(inp!=null && inp.length()!=0){
			input.add(inp);
			indMap.put(inp, input.size()-1);
			if(!sizeMap.containsKey(inp.length()))
				sizeMap.put(inp.length(), new ArrayList<Integer>());
			
			sizeMap.get(inp.length()).add(input.size()-1);
			inp=readline();
		}
		
		size=input.size();
		
		
		
		
		String test=readline();
		boolean first=true;
		while(test!=null && test.length()!=0){
			
			test=test.trim();
			//System.out.println(test);
			String[] spl=test.split(" ");
			String source=spl[0], dest=spl[1];
			
			
			Map<String, String> path=new HashMap<String, String>();
			
			List<Integer> lst=sizeMap.get(source.length());
			BitSet bs=new BitSet(lst.size());
			
			Queue<Integer> q=new LinkedList<Integer>();
			q.add(indMap.get(dest));
			bs.set(indMap.get(dest));
			

			boolean found=false;
			
			
			if(!(source.length()!=dest.length() || source.compareTo(dest)==0))
			while(q.size()!=0){
				int item=q.remove();
				String itemS=input.get(item);
				for(Integer i : lst){
					if(bs.get(i))
						continue;
					
					String curr=input.get(i);
					
					if(!check(curr, itemS))
						continue;
					
					bs.set(i);
					path.put(curr, itemS);
					
					if(curr.compareTo(source)==0){
						found=true;
					}
					
					q.add(i);
				}
				
				if(found)
					break;
			}
			
			if(first){
				first=false;
			}
			else
				System.out.println();
			
			
			if(found){
				String temp=source;
				do{
					System.out.println(temp);
					temp=path.get(temp);
				}while(temp.compareTo(dest)!=0);
				System.out.println(dest);
			}
			else{
				if(source.compareTo(dest)==0){
					System.out.println(source);
				}
				else
					System.out.println("No solution.");
			}
			
			test=readline();
		}
		
	}
	
	static boolean check(String str1, String str2){
		
		if(str1.length()!=str2.length())
			return false;
		
		int diffCount=0;
		for(int i=0;i<str1.length();i++){
			if(diffCount>1)
				return false;
			if(str1.charAt(i)!=str2.charAt(i))
				diffCount++;
		}
		
		if(diffCount==1)
			return true;
		else
			return false;
	}
}
