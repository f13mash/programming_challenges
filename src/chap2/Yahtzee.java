package chap2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Yahtzee {
	
	static int[][] diceValues=null;
	static int[][] diceMapping=null;
	static List<PriorityQueue<Integer>> pqs=null;
	
	static int maxSoFar=0, scoreBest[]=null, fillBest[]=null;
	
	
	static final int MAX_SIZE=512;
	static int lastPoint=0,lastReadAmount=0;
	static byte[] data=new byte[MAX_SIZE];
	static BufferedReader br=null;
	
	/*
	static String readLine() throws IOException{
		String line="";
		while(true){
			for(;lastPoint<lastReadAmount;lastPoint++){
				if(data[lastPoint]=='\n' || data[lastPoint]=='\f' || data[lastPoint]=='\r'){
					//return the newline here . . .
					lastPoint++;
					lastPoint++;
					return line.trim();
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
					return line.length()==0?null:line.trim();
				}
			}
		}
		//return null;
	}
	*/
	
	
	public static void main(String args[]) throws IOException{
		//File f=new File("Yahtzee.in");
		//System.setIn(new FileInputStream(f));
		br=new BufferedReader(new InputStreamReader(System.in));
		
		String line=readLine();
		boolean firstTime=true;
		while(line!=null && line.length()!=0){
			
			diceValues=new int[13][5];
			pqs=new ArrayList<PriorityQueue<Integer>>();
			Scanner scn=null;
			maxSoFar=0;
			scoreBest=null;
			fillBest=null;
			
			for(int i=0;i<diceValues.length;i++){
				scn=new Scanner(line);
				for(int j=0;j<diceValues[i].length;j++){
					diceValues[i][j]=scn.nextInt();
				}
				Arrays.sort(diceValues[i]);
				if(i!=(diceValues.length-1))
					line=readLine();
			}
			
			for(int i=0;i<13;i++)
				Arrays.sort(diceValues[i]);
			
			diceMapping=new int[13][13];
			for(int i=0;i<13;i++){
				for(int j=0;j<13;j++){
					diceMapping[i][j]=calculate(diceValues[i], j);
				}
			}
			
			reConstructPQS();
			evalGreedyMax();
			reConstructPQS();
			
			int[] fill=new int[13];
			boolean[] used=new boolean[13];
			int[] score=new int[14];
			
			recurseThrough(fill, used, score,12);
			int bonus=maxSoFar-scoreBest[0];
			String out="";
			for(int i=0;i<13;i++){
				out+=(scoreBest[i]-scoreBest[i+1])+" ";
			}			
			out+=bonus+" "+maxSoFar;
			
			
			System.out.println(out);
			
			line=readLine();
		}
		
	}
	
	private static String readLine() throws IOException {
		// TODO Auto-generated method stub
		return br.readLine();
	}

	static void reConstructPQS(){
		pqs=new ArrayList<PriorityQueue<Integer>>();
		for(int i=0;i<13;i++)
			pqs.add(new PriorityQueue<Integer>());
		for(int i=0;i<13;i++)
			addBackToPQ(i);
	}
	
	static void evalGreedyMax(){
		int score=0;
		for(int j=0;j<pqs.size();j++){
			int s=-pqs.get(j).peek();
			int id=(scorerID(j, s));
			if(id==-1)
				System.err.println("id should not be negative . .. . :(");
			removeFromPQ(id);
			score+=s;
		}
		maxSoFar=score-1;
	}
	
	static int scorerID(int j, int score){
		for( int i=0;i<13;i++){
			if(diceMapping[i][j]==score)
				return i;
		}
		
		return -1;
	}
	
	static void recurseThrough(int[] fill, boolean[] used, int[] score, int pos){
		
		if(pos==-1){
			
			int totalScore=score[0];
			
			int bonus=(score[0]-score[6])>=63?35:0;
			if((totalScore+bonus)>maxSoFar){
				maxSoFar=totalScore+bonus;
				scoreBest=Arrays.copyOf(score, score.length);
				fillBest=Arrays.copyOf(fill, fill.length);
			}
			
			return;
		}
		
		if(!isMaxAchievable(score, pos+1))
			return;
		
		for(int i=0;i<used.length;i++){
			if(!used[i]){
				used[i]=true;
				fill[pos]=i;
				score[pos]=score[pos+1]+diceMapping[i][pos];
				removeFromPQ(i);
				recurseThrough(fill, used, score, pos-1);
				score[pos]=0;
				addBackToPQ(i);
				used[i]=false;
			}
		}
	}
	
	static void addBackToPQ(int dcID) {
		for(int j=0;j<pqs.size();j++){
			pqs.get(j).add(-diceMapping[dcID][j]);
		}
	}

	static void removeFromPQ(int dcID){
		for(int j=0;j<pqs.size();j++){
			pqs.get(j).remove(-diceMapping[dcID][j]);
		}
	}
	
	static boolean isMaxAchievable(int[] score, int pos){
		
		for(int i=pos-1;i>=0;i--){
			score[i]=(-pqs.get(i).peek()+score[i+1]);
		}
		int bonus=(score[0]-score[6])>=63?35:0;
		return (score[0]+bonus)>maxSoFar;
	}
	
	static int calculate(int[] dices, int op){
		
		if(op<6){
			int count=0;
			for(int i=0;i<dices.length;i++)
				if(dices[i]==(op+1))
					count++;
			
			return count*(op+1);
		}
		
		switch(op){
		case  6:
			return getSum(dices);
		case 7:
			return similarCount(dices, 3)?getSum(dices):0;
		case 8:
			return similarCount(dices, 4)?getSum(dices):0;
		case 9:
			return similarCount(dices, 5)?50:0;
		case 10:
			return isStraight(dices, 4)?25:0;
		case 11:
			return isStraight(dices, 5)?35:0;
		case 12:
			return isFullHouse(dices)?40:0;
		}
		
		return 0;
	}
	
	static int getSum(int[] dices){
		int sum=0;
		for(int i=0;i<dices.length;i++){
			sum+=dices[i];
		}
		return sum;
	}
	
	static boolean similarCount(int[] dices, int count){
		
		for(int i=0;i<(dices.length-count+1);i++){
			if(dices[i]==dices[i+count-1])
				return true;
		}
		
		return false;
	}
	
	static boolean isStraight(int[] dices, int straightSize){
		
		int j=straightSize-1, prev=dices[0];
		
		for(int i=1;i<(dices.length);i++){
			if(dices[i]==prev+1){
				j--;
				prev=prev+1;
			}
			else{
				prev=dices[i];
				j=straightSize-1;
			}
			if(j==0)
				return true;
		}
		
		return false;
	}
	
	static boolean isFullHouse(int[] dices){
		if(dices[0]==dices[2] && dices[3]==dices[4] && dices[0]!=dices[4])
			return true;
		if(dices[0]==dices[1] && dices[2]==dices[4] && dices[0]!=dices[4])
			return true;
		return false;
	}
	
}
