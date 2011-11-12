package chap1;

import java.util.Arrays;

/*
 * MineSweeper
 * PC/UVa IDs: 110102/10189
 */
class MineSweeperDoer implements Runnable {

    @Override
    public void run() {
        String line = Minesweeper.ReadLn(128);
        int count=1;
        while (line != null || line.length()!=0) {
            
            String[] data=line.trim().split("\\s");
            int rows=Integer.parseInt(data[0]);
            int columns=Integer.parseInt(data[1]);
            
            if(rows==0 || columns==0){
            	return;
            }
            
            char[][] input=new char[rows][];
            for(int i=0;i<rows;i++){
            	line=Minesweeper.ReadLn(128);
            	input[i]=line.trim().toCharArray();
            }
            
            int[][] output=process(input);
            if(count!=1)
            	System.out.println();
            System.out.println("Field #"+count+++":");
            for(int i=0;i<output.length;i++){
            	
            	for(int j=0;j<output[i].length;j++)
            		if(output[i][j]==-1)
            			System.out.print('*');
            		else
            			System.out.print(output[i][j]);
            	
            	System.out.println();
            }
            line=Minesweeper.ReadLn(128);
            
            
        }
    }

    private int[][] process(char[][] input) {
    	
    	int[][] output=new int[input.length][input[0].length];
    	
    	
    	for(int i=0;i<input.length;i++){
    		for(int j=0;j<input[i].length;j++){
    			if(input[i][j]=='*'){
    				
    				for(int dx=-1;dx<=1;dx++){
    					for(int dy=-1;dy<=1;dy++){
    						if((i+dx)<0||(i+dx)>=input.length||(j+dy)<0||(j+dy)>=input[i].length)
    							continue;
    						if(input[i+dx][j+dy]=='*'){
    							output[i+dx][j+dy]=-1;
    							continue;
    						}
    						output[i+dx][j+dy]++;
    					}
    				}
    				
    			}
    		}
    	}
    	
    	return output;
    	
    }
}

// java program model from www.programming-challenges.com
class Minesweeper implements Runnable {
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
        Minesweeper myWork = new Minesweeper(); // Construct the bootloader
        myWork.run(); // execute
    }

    @Override
    public void run() {
        new MineSweeperDoer().run();
    }

}
