package tinyprog;
import jflex.io.*;
import java.io.*;
import java.io.File;

import jflex.exceptions.SilentExit;

/**
 * @author rashe
 */
public class TinyProg {

    /**
     * @param args the command line arguments
     */
	public static void main(String args[]) {
    	//File file =new File("C:\\Users\\d7oom\\eclipse-workspace\\PROJECT\\src\\Tiny.flex");
    	try {
    	    File file = new File("C:\\Users\\d7oom\\eclipse-workspace\\tinyprog\\src\\\\tinyprog\\Tiny.flex");
    	    jflex.Main.generate(new String[] { file.getPath() });
    	} catch (SilentExit e) {
    	    System.err.println("JFlex encountered an error:");
    	    e.printStackTrace();
    	} catch (Exception e) {
    	    System.err.println("An unexpected error occurred:");
    	    e.printStackTrace();
    	}

    }
    
}


