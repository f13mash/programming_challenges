package chap3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CryptKicker2 {
	
	static BufferedReader br=null;
	static String comparisonString="the quick brown fox jumps over the lazy dog";
	
	static String readLine() throws IOException{
		return br.readLine();
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException{
		
		//File f=new File("CK2.in");
		//System.setIn(new FileInputStream(f));
		
		br=new BufferedReader(new InputStreamReader(System.in));
		int testCases=Integer.parseInt(br.readLine().trim()), testCount=0;
		readLine();
		
		while(testCount++<testCases){
			char[][] charMapping=new char[26][2];
			boolean mappingDone=false;
			List<String> input=new ArrayList<String>();
			String line=readLine();
			
			while(line!=null && line.length()!=0){
				input.add(line);
				if(!mappingDone){
					charMapping=new char[26][2];
					if(comparisonString.length()==line.length()){
						int count=0;
						for(int i=0;i< line.length();i++){
							
							char c1=comparisonString.charAt(i), c2=line.charAt(i);
							if(Character.isWhitespace(c1) && Character.isLetter(c2) || Character.isWhitespace(c2) && Character.isLetter(c1)){
								break;
							}
							if(Character.isWhitespace(c1) && Character.isWhitespace(c2)){
								continue;
							}
							
							if((charMapping[c2-'a'][0]==c1 && charMapping[c1-'a'][1]==c2))
								continue;
							
							if((charMapping[c2-'a'][0]==0 && charMapping[c1-'a'][1]==0)){
								count++;
								charMapping[c2-'a'][0]=c1;
								charMapping[c1-'a'][1]=c2;
							}
							else{
								break;
							}
						}
						if(count==26)
							mappingDone=true;
					}
				}
				line=readLine();
			}
			
			if(mappingDone){
				for(int j=0;j<input.size();j++){
					String ln=input.get(j);
					char[] arr=ln.toCharArray();
					for(int i=0;i<arr.length;i++){
						if(Character.isLetter(arr[i]))
							arr[i]=charMapping[arr[i]-'a'][0];
					}
					System.out.println(new String(arr));
				}
				
				
			}
			else{
				System.out.println("No solution.");
			}
			if(testCount!=testCases){
				System.out.println();
			}
		}
	}
	

}
