import java.io.*;
import java.util.*;
import java.util.Random;


/**
 *
 * @author skonlc brinkmwj and karroje
 * @Date     : 2009-10-04, 2011-03-10, 2015-09-27
 * Sources  : All code is original
 * Purpose  : The purpose of this file is to do some VERY rudimentary timing of your increaseCount
 *            method. For the QUALITY measures I am also going to test getCount and getNthPopulat,
 *            so you might want to design some way to test out the running time of your getCount as well!
 */
public class Controller {

/*
 * The only purpose of main() is to call processFile with increasingingly larger and larger
 * files. A larger file will give a more accurate sense of how fast addToTrends is, but at some
 * point it may take so long to do the getNthPopular, that we aren't willing to wait for it to finish.
 */

	static Random rng;

    public static void main(String[] args) {

    	/* These files are books from project Gutenberg. I have provided the inputs, as well as my outputs
    	 * in the starter files */

    	/* NOTE: You may want to comment some of these out!
    	 * Unless your program is very speedy on all operations some of these will never finish.
    	 */
    	rng = new Random(42);

    	timeTests("28885.txt");
        //processFile("46.txt");
        //processFile("23684.txt");
        //processFile("4300.txt");
        //processFile("1342.txt");
        //processFile("3090.txt");
        //processFile("6130.txt");

    }

    public static ArrayList<String> readFile(String fileName) {
    	ArrayList<String> wordList = new ArrayList<>();
    	String line;
       	try {
    		FileReader fileReader = new FileReader(fileName);
    		try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
    			while((line = bufferedReader.readLine()) != null)
    			{
    				line=line.replaceAll("[^A-Za-z0-9 ]", ""); //Remove all non-alphanumeric/whitespace characters
    				for(String word : line.split(" "))
    					if (!word.isEmpty())
    						wordList.add(word);
    			}
    		}
    	}
    	catch(FileNotFoundException ex) {
    		System.err.println( "Unable to open file '" +  fileName + "'");
    		System.exit(1);
    	}
    	catch(IOException ex) {
    		System.err.println("Error reading file '" + fileName + "'");
    		System.exit(1);
    	}
       	return wordList;
    }


    /**
     * Find the total time to insert the words into a list.
     * @param wordList
     * @return average time per insert
     */
    public static void timeTests(String file) {
    	ArrayList<String> wordList = readFile(file);

    	// Average time for insert
    	long startTime = System.nanoTime();
    	Trends tr = new StudentTrends();
    	for (String w: wordList)
    		tr.increaseCount(w, 1);
    	long endTime = System.nanoTime();
    	double test1 = (double)((endTime -startTime)/1000000.0)/(double)wordList.size();
    	System.out.println("Test 1: " + test1 + " milliseconds / insert");


    	// Average time for getCount
    	startTime = System.nanoTime();
    	for (String w: wordList)
    		tr.getCount(w);
    	endTime = System.nanoTime();
    	double test2 = (double)((endTime - startTime)/1000000.0)/(double)wordList.size();
    	System.out.println("Test 2: " + test2 + " milliseconds / getCount");

    	// Average time for getNthPopular();
    	int n = tr.numEntries();
    	startTime = System.nanoTime();
    	for (int i = 0; i < n; i++)
    		tr.getNthPopular(i);
    	endTime = System.nanoTime();
    	double test3 = (double)((endTime - startTime)/1000000.0)/(double)n;
    	System.out.println("Test 3: " + test3 + " milliseconds / getNthPopular");

    	// Average time per operation when mixing operations
    	tr = new StudentTrends();
    	startTime = System.nanoTime();
    	n = 0;
    	for (int i=0; i < wordList.size(); i++) {
    		tr.increaseCount(wordList.get(i),1);
    		if (rng.nextDouble() < 0.2)
    			n = tr.numEntries();
    		if (rng.nextDouble() < 0.2) {
    			tr.getNthPopular(rng.nextInt(Math.max(n,1)));
    		}
    	}
    	endTime = System.nanoTime();
    	double test4 = (endTime - startTime) / 1000000000.0;
    	System.out.println("Test 4: " + test4 + " seconds (total)");
       /*	for(int i=0;i<tr.numEntries();i++){
   
    		System.out.println("["+i+"]= "+tr.getNthPopular(i));
    		System.out.println("not: "+tr.getCount("not"));
    	}
    	
    	System.out.println(tr.getNthPopular());
    	System.out.println(tr.getCount("OF"));
    	System.out.println(tr.getCount("Of"));*/
    }
}
