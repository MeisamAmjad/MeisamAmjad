package data_structures;
/** 
 * ***************************************************
 *              Copyright (c) 2018
 * 
 *          FOR EDUCATIONAL PURPOSE ONLY
 *            Group Project CSE 464/564
 * 
 * Written by: Megan moore, Prasidh Arora, Meisam Amjad
 * ***************************************************
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * *************************************************************************************************************************
 * The FileManager Class reads From a (*.cvs) file with prepared format for Matching class and 
 * returns:
 *      1 - List of Parent's roll numbers.
 *      2 - List of Child's ID numbers.
 *      3 - HashMap of Parents.
 *      4 - HashMap of Children.
 * */
public class FileManager<T> {
    // *************************************************************************************************************************
    /** <p> The <b>Default Constructor</b>. Sets all fields to Empty.</p> */
    public FileManager() {}
    /** <p> The <b>Default Constructor</b>. Class reads From a (*.cvs) file.</p>	*/
    public FileManager (String fullPath) throws IOException {readlines(fullPath);}
    /** <p> Returns the given file path for this class. </p> */
    public String getFilePath()                         	{return fileP();}
	/** <p> Get the all lines of a file. </p> */
    public String[] getLines()                          	{return lines();}
    /** <p> Get List of Parent's roll numbers. </p> */
    public List<Integer> getParents()                   	{return getPList();}
    /** <p> Get List of Children's Id numbers. </p> */
    public List<Integer> getChildren()                  	{return getCList();}
    /** <p> Get HashMap of Parents. </p> */
    public HashMap<Integer, Parent<T>> getParentMap()   	{return getPMap();}
    /** <p> Get HashMap of Children. </p> */
    public HashMap<Integer, Child<T>> getChildrenMap()  	{return getCMap();}
    // *************************************************************************************************************************

    /* <p> Contains each line of a file. </p> */
    private String[] Lines;
    /* <p> Holds the path. </p> */
    private String filePath;
    /* <p> List of Parent's roll numbers. </p> */
    private List<Integer> parents   = new ArrayList<Integer>();
    /* <p> List of Children's Id numbers. </p> */
    private List<Integer> children  = new ArrayList<Integer>();
    /* <p> HashMap of Parents. </p> */
    private HashMap<Integer, Parent<T>> parentsMap = new HashMap<Integer, Parent<T>>();
    /* <p> HashMap of Children. </p> */
    private HashMap<Integer, Child<T>> childrenMap = new HashMap<Integer, Child<T>>();
    /* Helpers for sets & gets methods */
    private String[] lines()                        {return this.Lines;}
    private String fileP()                          {return this.filePath;}
    private List<Integer> getPList()                {return this.parents;}
    private List<Integer> getCList()                {return this.children;}
    private HashMap<Integer, Parent<T>> getPMap()   {return this.parentsMap;}
    private HashMap<Integer, Child<T>> getCMap()    {return this.childrenMap;}
	/*
	 * <p> Read from a cvs file and assign the the result into the fiels String[] Lines. </p>
	 * @param fullPath the path of cvs file
	 * @throws IOException if there is no such a file
	 */
	private void readlines(String fullPath) throws IOException{
		ArrayList<String> linesInFile = readAllLines(fullPath);
		this.Lines = new String[linesInFile.size()];    // Initializing the length
        linesInFile.toArray(Lines);                     // change to String[]
        assigneFiled();  // Initilizes the parent & children's List & Map
	}
	/*
	 * <p> It's a helper method for readlines() </br>
	 * opens a connection to the file, read each line and add them to the list </br>
	 * close the connection, and return an array list contains all the lines </br>
	 * If the line is empty, or fills up with tabs or spaces, instead of deleting the line </br>
	 * fills it up with one witheSpace. That way later can check it easier. </p>
	 * @param fullPath the full address of the input file.
	 * @return an ArrayList from all the lines.
	 * @throws IOException if there is no such a file.
	 */
	private ArrayList<String> readAllLines(String fullPath) throws IOException{
		BufferedReader _br              = new BufferedReader(new FileReader(fullPath));
		ArrayList<String> linesInFile   = new ArrayList<>();
		String s;
		while ((s = _br.readLine()) != null)  // Reads line by line
			linesInFile.add(!s.trim().isEmpty()? s.trim(): " ");	
		_br.close();
		return linesInFile;
    }
    
    /*
     * <p> A helper method which reads from all file Lines and assign proper values </br>
     * into the rest of the fields in this class.</p>
     */
    private void assigneFiled() {
        for(String line:getLines()) {
            // Splits a line based on either whitespace or comma
            String[] tokens = line.split(",");
            // Skips because of lack of data
            // Skips if the line starts with nothing or there is no id number
            if (tokens.length < 4 || tokens[0].trim().isEmpty() || !isNumber(tokens[1].trim())) 
                continue;
            switch (tokens[0].trim().toLowerCase().charAt(0)) {  // Checks the first token
                case 'c':   readChild(tokens);
                            break;
                case 'p':   readParent(tokens);
                            break;
            }
        }
    }

    private void readChild(String[] tokens) {
        int id              = toInteger(tokens[1].trim());
        Child<T> c          = new Child<T>();
        List<Integer> lst   = new ArrayList<Integer>();
        this.children.add(id);      // Add to Children List.
        c.setId(id);
        c.setName(tokens[2].trim());
        for (int i = 3; i < tokens.length; lst.add(toInteger(tokens[i++].trim())));
        c.setRankedParent(lst);
        this.childrenMap.put(id, c);    // Add to childrenMap.
    }

    private void readParent(String[] tokens) {
        int rollNumber      = toInteger(tokens[1].trim()); 
        Parent<T> p         = new Parent<T>();
        List<Integer> lst   = new ArrayList<Integer>();
        this.parents.add(rollNumber);       // Add to Parents List.
        p.setRollNumber(rollNumber);
        p.setName(tokens[2].trim());
        p.setAvailChild(toInteger(tokens[3].trim()));
        for (int i = 4; i < tokens.length; lst.add(toInteger(tokens[i++].trim())));
        p.setRankedChildren(lst);
        this.parentsMap.put(rollNumber, p);     // Add to Parent Map.
    }

    /*
     * Checks if the given String is an Integer or not.
     */
	private boolean isNumber(String s) {
		try{
			Integer.valueOf(s);
			return true;
		}catch(NumberFormatException e) {
			return false;
		}
    }
    
    /** Returns an integer from a given String.  */
    private int toInteger(String s) {return Integer.valueOf(s);}
}