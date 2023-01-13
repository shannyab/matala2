package PartA;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.*;

public class ThreadCall implements Callable<Integer> {

    private String fname;

    public ThreadCall(String name) {
        this.fname = name;
    }

    @Override
    public Integer call() throws Exception {
        int getlines = 0;

        File myObj = new File(this.fname);
        Scanner myReader = null;

        myReader = new Scanner(myObj);

        while (myReader.hasNextLine())
        {
            myReader.nextLine();
            getlines++;
        }

        myReader.close();

        return getlines;
    }
}
