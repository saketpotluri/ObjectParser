package Trees;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by odegaard on 11/21/15.
 */
public class Handle {
    private static Scanner sc;
    public static final String OUTPUT_FILE_NAME = "out.obj";

    public String getFileName() {
        System.out.println("Please enter a file name. Convert the .object file into a .txt. Make it so the first " +
                "line contains only vertices");
        String line = sc.next();
        try {
            while (!line.matches("(.*).txt")) {
                System.out.println("Please enter a valid format with a .txt file name");
                line = sc.nextLine();
            }
        } catch(Exception e) {
            e.printStackTrace();
            line = getFileName(); //call getFileName again
        }
        return line;
    }


    public int getLines() {
        System.out.println("Please enter how many lines to skip (enter 1 if not skipping lines. ");
        int val = sc.nextInt();
        if(val <= 0) {
            System.out.println("Please enter non negative");
            val = getLines();
        }
        return val;
    }

    public static void main(String[] args) throws IOException {
        sc = new Scanner(System.in);
        Handle h = new Handle();
        Process p = new Process();
        String fileName = h.getFileName();
        int skipLines = h.getLines();
        p.fillVertexes(fileName, skipLines);
        //get input for red, green, blue
        p.sortVertices("", 1.0, 0.0, 0.0);
        ArrayList<Vertex> list = p.selectNBestVertexes(100);
        p.writeVertices(list, OUTPUT_FILE_NAME);

    }
}
