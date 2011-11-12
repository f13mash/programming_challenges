package chap1;

import java.util.HashMap;
import java.util.Map;



/*
 * 
 * 3n+1 problem
 * PC/UVa IDs: 110101/100
 */
class myStuff implements Runnable {

	public static Map<Integer, Integer> cache =new HashMap<Integer, Integer>();
	
    @Override
    public void run() {
        String line = Problem1.ReadLn(128);
        while (line != null) {
            process(line);
            line = Problem1.ReadLn(128);
        }
    }

    private void process(String line) {

    	
    	int max=Integer.MIN_VALUE;
        String[] data = line.trim().split("\\s+");
        
        if(data.length==2){
        	int start=Integer.parseInt(data[0]);
        	int end=Integer.parseInt(data[1]);
        	System.out.println(start+" "+end+" "+findMax(Math.min(start, end), Math.max(start, end)));
        }
        
        
        
    	
    	
    	
    }
    
    public static int findMax(int start, int end){
    	int max=Integer.MIN_VALUE;
    	for(int i=start;i<=end;i++){
    		int ret=0;
    		if((ret=collatzSolver(i))>max)
    			max=ret;
    	}
    	return max;
    }
    

	public static int collatzSolver(int  numb){
    	if(numb==1)
    		return 1;
    	if(cache.containsKey(numb))
    		return cache.get(numb);
    	
    	int ret=(numb%2 ==0)?(collatzSolver(numb/2)+1):(collatzSolver(3*numb+1)+1);
    	cache.put(numb, ret);
    	return ret;
    }

}

// java program model from www.programming-challenges.com
class Problem1 implements Runnable {
    static String ReadLn(int maxLength) { // utility function to read from
        // stdin, Provided by Programming-challenges, edit for style only
        byte line[] = new byte[maxLength];
        int length = 0;
        int input = -1;
        try {
            while (length < maxLength) { // Read untill maxlength
                input = System.in.read();
                if ((input < 0) || (input == '\n'))
                    break; // or untill end of line ninput
                line[length++] += input;
            }

            if ((input < 0) && (length == 0))
                return null; // eof
            return new String(line, 0, length);
        } catch (java.io.IOException e) {
            return null;
        }
    }

    public static void main(String args[]) { // entry point from OS
        Problem1 myWork = new Problem1(); // Construct the bootloader
        myWork.run(); // execute
    }

    @Override
    public void run() {
        new myStuff().run();
    }

}
