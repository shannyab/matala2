package PartA;

import java.io.*;
import java.util.*;

public class ThreadFiles extends Thread{

    private String fname;
    private int getlines = 0;

    public ThreadFiles(String name) {
        this.fname = name;
    }

    public int getGetlines() {
        return this.getlines;
    }

    @Override
    public void run() {
        File myObj = new File(this.fname);
        Scanner myReader = null;

        try
        {
            myReader = new Scanner(myObj);
        }

        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }

        while (myReader.hasNextLine())
        {
            myReader.nextLine();
            this.getlines++;
        }

        myReader.close();
    }
}
