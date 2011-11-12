package chap3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FileFrag {
	
	static BufferedReader br=null;
	
	static String readLine() throws IOException{
		return br.readLine();
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException{
		//File f=new File("ff.in");
		//System.setIn(new FileInputStream(f));
		br=new BufferedReader(new InputStreamReader(System.in));
		
		int testCases=Integer.parseInt(readLine().trim()), currCase=0;
		readLine();
		
		while(currCase++<testCases){
			int minL=Integer.MAX_VALUE, maxL=Integer.MIN_VALUE, size=0;
			List<String> input=new ArrayList<String>();
			Map<Integer, List<String>> lenMap=new HashMap<Integer, List<String>>();
			
			String in=readLine();
			while(in!=null && in.length()!=0){
				input.add(in);
				//System.out.println(in.length());
				if(in.length()<minL)
					minL=in.length();
				
				if(in.length()>maxL)
					maxL=in.length();
				
				if(!lenMap.containsKey(in.length()))
					lenMap.put(in.length(), new ArrayList<String>());
				lenMap.get(in.length()).add(in);
				
				in=readLine();
			}
			
			size=minL+maxL;
			
			Set<String> soln=new HashSet<String>();
			for(String shrt : lenMap.get(minL)){
				for(String lng :  lenMap.get(maxL)){
					if(!soln.contains(shrt+lng))
						soln.add(shrt+lng);
					if(!soln.contains(lng+shrt))
						soln.add(lng+shrt);
				}
			}
			
			
			for(int l1 : lenMap.keySet()){
				if(soln.size()==1)
					break;
				
				int l2=size-l1;
				Set<String> tempSoln=new HashSet<String>();
				for(String str1 : lenMap.get(l1)){
					
					for(String str2 : lenMap.get(l2)){
						if(soln.contains(str1+str2) && !tempSoln.contains(str1+str2)){
							tempSoln.add(str1+str2);
						}
						if(soln.contains(str2+str1) && !tempSoln.contains(str2+str1)){
							tempSoln.add(str2+str1);
						}
					}
				}
				soln=tempSoln;
			}
			
			
			for(String str : soln){
				System.out.println(str);
				break;
			}
			
			if(currCase!=testCases)
				System.out.println();
			
		}
		
	}
}
