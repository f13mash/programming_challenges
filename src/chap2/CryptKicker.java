package chap2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * CryptKicker - 
 * PC/UVa IDs: 110204/843
 */

public class CryptKicker {
	
	//extra
	static Map<Integer, List<String>> encryptedValues=null,dict=null;
	static BufferedReader br=null;
	///extra end
	
	static final int MAX_SIZE=512;
	static int lastPoint=0,lastReadAmount=0;
	static byte[] data=new byte[MAX_SIZE];
	static char[][] cm=null;
	
	static String readLine() throws IOException{
		String line="";
		while(true){
			for(;lastPoint<lastReadAmount;lastPoint++){
				if(data[lastPoint]=='\n' || data[lastPoint]=='\f' || data[lastPoint]=='\r'){
					//return the newline here . . .
					lastPoint++;
					return line;
				}
				line+=(char)data[lastPoint];
			}
			
			//will work in situation where input args terminate without any clue
			if(lastPoint>=lastReadAmount){
				if(System.in.available()>0){
					lastReadAmount=System.in.read(data);
					lastPoint=0;
				}
				else{
					return line.length()==0?null:line;
				}
			}
		}
		//return null;
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException{
		//set input stream
		/*File f=new File("CryptKicker.in");
		System.setIn(new FileInputStream(f));*/
		
		String line=readLine();
		dict=new HashMap<Integer, List<String>>();
		int n=Integer.parseInt(line.trim());
		
		for(int i=0;i<n;i++){
			line=readLine().trim();
			if(line.length()==0){
				i--;
				continue;
			}
			
			if(!dict.containsKey(line.length()))
				dict.put(line.length(), new ArrayList<String>());
			dict.get(line.length()).add(line);
		}
		
		line=readLine();
		while(line!=null){
			if(line.length()==0){
				System.out.println(line);
				line=readLine();
				continue;
			}
			String[] encWords=line.split("\\s");
			encryptedValues=new HashMap<Integer, List<String>>();
			for(int i=0;i<encWords.length;i++){
				String word=encWords[i].trim();
				if(!encryptedValues.containsKey(word.length()))
					encryptedValues.put(word.length(), new ArrayList<String>());
				if(!encryptedValues.get(word.length()).contains(word))
					encryptedValues.get(word.length()).add(word);
			}
			char[][] charMap=new char[2][26];
			Arrays.fill(charMap[0], (char)0);
			Arrays.fill(charMap[1], (char)0);
			
			Integer[] keys=new Integer[encryptedValues.size()];
			encryptedValues.keySet().toArray(keys);
			Arrays.sort(keys);
			boolean ret=recurseThrough(charMap, keys, keys.length-1);
			if(!ret)
				Arrays.fill(charMap[1], '*');
			else
				charMap=cm;
			String output="";
			
			for(int i=0;i<line.length();i++){
				char printChar=line.charAt(i);
				if(printChar>='a' && printChar<='z'){
					printChar=charMap[1][printChar-'a'];
				}
				output+=printChar;
			}
			System.out.println(output);
			line=readLine();
		}
		
	}
	
	
	static boolean recurseThrough(char[][] charMap, Integer[] keys, int currIndex){
		if(currIndex<0){
			cm=charMap;
			return true;
		}
		
		int size=keys[currIndex];
		
		
		
		//else generate a combination of no. of elements=size of encrypted elements from set of words from dictionary
		int comb=encryptedValues.get(size).size(), n=dict.get(size).size();
		//going through n C comb permutations
		boolean[] used=new boolean[n];
		

		//number of encrypted values of same length are more than available in dictionary then return false
		if(comb>n){
			return false;
		}
		
		return internalRecurse(charMap, size, used, 0, keys, currIndex);
		
	}
	
	static boolean internalRecurse(char[][] charMap, int key, boolean[] used, int pos, Integer[] keys, int currIndex){
		int sz=dict.get(key).size();
		int cz=encryptedValues.get(key).size();
		if(pos>=cz){
			//time to check for the next set of words
			return recurseThrough(charMap, keys, currIndex-1);
		}
		for(int i=0;i<sz;i++){
			boolean doCall=true;
			if(used[i])
				continue;
			
			used[i]=true;
			//if good then call with pos+1
			//don't spoil the original copy of charMap
			char[][] test=new char[2][];
			test[0]=Arrays.copyOf(charMap[0], charMap[0].length);
			test[1]=Arrays.copyOf(charMap[1], charMap[1].length);
			
			String encWord=encryptedValues.get(key).get(pos), dictWord=dict.get(key).get(i);
			
			
			for(int j=0;j<encWord.length();j++){
				int encInd=encWord.charAt(j)-'a';
				int dictInd=dictWord.charAt(j)-'a';
				//0fordict 1forenc
				
				if((test[0][dictInd]!=0 && test[0][dictInd]!=encWord.charAt(j)) || (test[1][encInd]!=0 && test[1][encInd]!=dictWord.charAt(j))){
					doCall=false;
					break;
				}
				
				test[0][dictInd]=encWord.charAt(j);
				test[1][encInd]=dictWord.charAt(j);
			}
			
			if(doCall)
				if(internalRecurse(test, key, used, pos+1, keys, currIndex)){
					return true;
				}
				
			
			used[i]=false;
		}
		return false;
	}
	
	
	
}
