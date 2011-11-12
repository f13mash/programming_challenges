package chap4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class CDVII {

	static BufferedReader br=null;
	
	static String readLine() throws IOException{
		return br.readLine();
	}
	
	public static void main(String[] args) throws IOException{
		/*
		File f=new File("cdvii.in");
		System.setIn(new FileInputStream(f));
		*/
		br=new BufferedReader(new InputStreamReader(System.in));
		
		int cases=Integer.parseInt(readLine().trim()), currCase=0;

		readLine();
		
		while(currCase++!=cases){
			String[] tollCosts=readLine().trim().split(" ");
			int[] tolls=new int[tollCosts.length];
			
			Map<String, LicenseID> idMap=new HashMap<String, LicenseID>();

			for(int i=0;i<tollCosts.length;i++){
				tolls[i]=Integer.parseInt(tollCosts[i].trim());
			}
			
			
			String inp=readLine();
			while(inp!=null && inp.length()!=0){
				String[] params=inp.trim().split(" ");
				if(!idMap.containsKey(params[0].trim())){
					idMap.put(params[0].trim(), new LicenseID(params[0].trim(), tolls));
				}
				
				idMap.get(params[0].trim()).updateLog(params[1], params[2], params[3]);
				inp=readLine();
			}
			
			TreeSet<String> keys=new TreeSet<String>(idMap.keySet());
			
			if(currCase!=1)
				System.out.println();
			for(String key : keys){
				idMap.get(key).evaluateCost();
			}
			
		}
	}
}

class LicenseID{
	public String id=null;
	List<LogDetail> logs=null;
	int[] tolls=null;
	
	public LicenseID(String licenseID, int[] tolls){
		this.id=licenseID;
		logs=new ArrayList<LogDetail>();
		this.tolls=tolls;
	}
	
	public void updateLog(String date, String status, String distance){
		logs.add(new LogDetail(date, status, distance));
	}
	
	public void evaluateCost(){
		
		Comparator<LogDetail> logComparator=new Comparator<LogDetail>() {

			@Override
			public int compare(LogDetail arg0, LogDetail arg1) {
				int ret=0;
				ret=arg0.month-arg1.month;
				if(ret==0)
					ret=arg0.day-arg1.day;
				if(ret==0)
					ret=arg0.hour-arg1.hour;
				if(ret==0) 
					ret=arg0.minutes-arg1.minutes;
				
				return ret;
			}
		};
		
		
		Collections.sort(logs, logComparator);
		
		int tripCount=0, sum=0;
		
		int i=0;
		
		while(i<logs.size()){
			switch (logs.get(i).stat) {
			case enter:
				if((i+1)<logs.size() && logs.get(i+1).stat==logs.get(i).stat.exit){
					tripCount++;
					int pricing=tolls[logs.get(i).hour];
					int distance=Math.abs(logs.get(i+1).location-logs.get(i).location);
					sum+=distance*pricing;
					i+=2;
				}
				else
					i++;
				break;
				
			case exit:
				i++;
				break;
			}
			
		}
		
		if(tripCount>0){
			sum=(200+100*tripCount+sum);
			System.out.println(id+" $"+sum/100+"."+(sum%100)/10+sum%10);
		}
		
		
	}
	
}

class LogDetail{
	
	
	public int month=-1;
	public int day=-1;
	public int hour=-1;
	public int minutes=-1;
	
	public static enum Status {
		enter,
		exit
	}
	public int location;
	public Status stat;
	
	public LogDetail(String date, String status, String distance){
		
		
		date=date.trim();
		String[] field=date.split(":");
		month=Integer.parseInt(field[0]);
		day=Integer.parseInt(field[1]);
		hour=Integer.parseInt(field[2]);
		minutes=Integer.parseInt(field[3]);
		
		if(status.trim().compareTo("enter")==0)
			stat=Status.enter;
		else
			stat=Status.exit;
		
		location=Integer.parseInt(distance);
		
	}
}
