package Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Zach
 */
public class Logger {

    private static boolean tried = false;

    /**
     * Clears The Log File
     */
    public static void clear() {
        File theFile = new File ("data\\appData\\logs.txt");
        theFile.delete();
        try {
            theFile.createNewFile();
        } catch (IOException ex) {
            System.out.println("Failed To Create New Log File");
        }
    }
    
    /**
     * Logs Problems That Happened When The Program Is Running. Used To Know
     * When And Where Problems Happened.
     *
     * @param location The Location Where The Problem Happened
     * @param problem The Problem That Happened
     */
    public static void log(String location, String problem){
        Date d = new Date();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String date = df.format(d);
        File check = new File("data\\appData\\logs.txt");
        List<String> txt = new ArrayList<String>();
        if (check.exists()) {
            try {
                Scanner read = new Scanner(check);
                while (read.hasNext()) {
                    txt.add(read.nextLine());
                }
            } catch (FileNotFoundException ex) {
                System.out.println("COULD NOT FIND log.txt");
            }
            try {
                FileWriter fw = new FileWriter("data\\appData\\logs.txt");
                PrintWriter out = new PrintWriter(fw, true);
                out.println("[" + date + "] An Error Has Occured At " + location + ": " + problem);
                for (int i = 0; i < txt.size(); i++) {
                    out.println(txt.get(i));
                }
                txt = null;
            } catch (IOException ex) {
                System.out.println("FAILED");
            }

        } else {
            if (!tried) {
                System.out.println("LOGGER HAS FAILED");
                System.out.println("FILE HAS NOT TRIED TO BE CREATED.");
                System.out.println("ATTEMPTING TO CREATE LOG FILE");
                try {
                    check.createNewFile();
                    System.out.println("LOG FILE CREATED SUCCESSFULY");
                    log(location, problem);
                } catch (IOException ex) {
                    System.out.println("FAILED TO CREATE LOG FILE");
                    System.out.println("SWITCHING TO TERMINAL OUTPUT");
                    tried = true;
                }
            }
            if (tried) {
                System.out.println("[" + date + "] An Error Has Occured At " + location + ":" + problem);
            }

        }
    }

}
