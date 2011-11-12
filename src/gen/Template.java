package gen;

/*
 * 
 * 3n+1 problem
 */
class myStuff implements Runnable {

    @Override
    public void run() {
        String line = Template.ReadLn(128);
        while (line != null) {
            process(line);
            line = Template.ReadLn(128);
        }
    }

    private void process(String line) {

        //String[] data = line.trim().split("\\s+");        
    }

}

// java program model from www.programming-challenges.com
class Template implements Runnable {
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
        Template myWork = new Template(); // Construct the bootloader
        myWork.run(); // execute
    }

    @Override
    public void run() {
        new myStuff().run();
    }

}
