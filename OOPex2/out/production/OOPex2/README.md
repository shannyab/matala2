# OOP Ex2
### By Shani and Avigail


## Part A
![UML.png](PartA%2FUML.png)

In this part we'd tested how efficient are threads with reading files.

We create a function to create text files with random number of lines and checked with 3 other function what's the total sum of all the lines:

* **getNumOfLines** - iterate single file each time and sum the total of lines. This was the slowest method.
* **getNumOfLinesThreads** - iterate multiple files via threads that stored in an array - each thread takes care for one file. This method is at least 10 times faster than the first one.
* **getNumOfLinesThreadPool** - same as the second function, but via a thread pool and using Future objects to get the result of each thread.

**Conclusion:** Threads are much more efficient than single thread.

## Part B
![UML.png](PartB%2FUML.png)

In this part we extended the abilies of the threadpoolexecutor, by giving each task a priority. The task with the highest priority is pooled first from the priority queue.

We create 3 Classes:

* **Task** - a task that has a priority and a callable object.