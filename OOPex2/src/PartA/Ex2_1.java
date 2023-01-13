package PartA;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class Ex2_1 {
    public static void main(String[] args) {
        String[] test = createTextFiles(1000, 1, 1000);

        long s = System.currentTimeMillis();
        int lines = getNumOfLines(test);
        System.out.println("Function 2 res = " + lines + " time = " + (System.currentTimeMillis() - s) + " ms");

        s = System.currentTimeMillis();
        lines = getNumOfLinesThreads(test);
        System.out.println("Function 3 res = " + lines + " time = " + (System.currentTimeMillis() - s) + " ms");

        s = System.currentTimeMillis();
        lines = getNumOfLinesThreadPool(test);
        System.out.println("Function 4 res = " + lines + " time = " + (System.currentTimeMillis() - s) + " ms");
    }

    /**
     *
     * @param n number of files to create
     * @param seed seed for random number generator
     * @param bound max number of lines in each file
     * @return array of file names
     */
    public static String[] createTextFiles(int n, int seed, int bound) {
        String[] fileNames = new String[n];
        Random rand = new Random(seed);

        for (int i = 0; i < n; i++)
        {
            int genlines = rand.nextInt(bound);

            try
            {
                fileNames[i] = "txtfile" + i + ".txt";
                FileWriter write = new FileWriter(fileNames[i]);

                for (int j = 0; j < genlines; j++)
                    write.write("hello world\n");

                write.close();
            }

            catch (IOException e)
            {
                throw new RuntimeException(e);
            }

        }

        return fileNames;
    }

    /**
     * Returns the number of lines in all files, using a single thread
     * @param fileNames array of files to read from.
     * @return total lines in all files
     */
    public static int getNumOfLines(String[] fileNames) {
        int sum = 0;

        for (String fileName : fileNames)
        {
            File myObj = new File(fileName);
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
                sum++;
            }

            myReader.close();
        }

        return sum;
    }

    /**
     * Returns the number of lines in all files, using multiple threads
     * @param fileNames array of files to read from.
     * @return total lines in all files
     */
    public static int getNumOfLinesThreads(String[] fileNames) {
        int sum = 0;
        ThreadFiles[] threads = new ThreadFiles[fileNames.length];

        for (int i = 0; i < fileNames.length; i++)
        {
            threads[i] = new ThreadFiles(fileNames[i]);
            threads[i].start();
        }

        for (ThreadFiles thread: threads)
        {
            try
            {
                thread.join();
                sum += thread.getGetlines();
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        }

        return sum;
    }

    /**
     * Returns the number of lines in all files, using threadpool.
     * @param fileNames array of files to read from.
     * @return total lines in all files
     */
    public static int getNumOfLinesThreadPool(String[] fileNames) {
        int sum = 0;
        ExecutorService executor = Executors.newFixedThreadPool(fileNames.length/2);
        List<Future<Integer>> list = new ArrayList<Future<Integer>>();

        for (String fileName: fileNames)
        {
            Callable<Integer> worker = new ThreadCall(fileName);
            Future<Integer> submit = executor.submit(worker);
            list.add(submit);
        }

        for (Future<Integer> fut: list)
        {
            try
            {
                sum += fut.get();
            }

            catch (InterruptedException | ExecutionException e)
            {
                throw new RuntimeException(e);
            }
        }

        executor.shutdown();

        return sum;
    }
}
