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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p> This Class read the data from a cvs file and save them as two list:</br>
 * 1- An arraylist of parent objects.</br>
 * 2- An arraylist of children objects.</br>
 * To produces fraternalPairing list.</p>
 */
public class Matching<T> {
    // *************************************************************************************************************************
    /** <p>The <b>Default Constructor</b>. Sets everything into Empty.</p> */
    public Matching(){}
    /** <p>The <b>Constructor</b>. Sets everything from the given FileManager object.</p> */
    public Matching(FileManager<T> f)                         	{assignAll(f);}
    /** <p>The <b>Copy Constructor</b>. Making a deep copy of the given Matching object.</p> */
    public Matching(Matching<T> M)								{init(M);}
    /** <p> List of all Parents. (Key = roll number, value = Parent object) </p> */
    public HashMap<Integer, Parent<T>> getParentsMap()          {return getPM();}
    /** <p> List of all Children. (Key = ID number, value = Child object) </p> */
    public HashMap<Integer, Child<T>> getChildrenMap()          {return getCM();}
    /** <p> Returns the list of Parent's roll numbers. </p> */
    public List<Integer> getParents()                           {return getParentList();}
    /** <p> Returns the List of Children's ID. </p> */
    public List<Integer> getChildren()                          {return getChildrenList();}
    /** <p> Holds the list of matched pairs after fraternalPairing method gets run once.</p> */
    public HashMap<Integer, Integer> getPairs()					{return getMatched();}
    /** <p> Returns a list of pairs. null if there is no matched.</p> */
    public HashMap<Integer, Integer> fraternalPairing()   		{return match();}
    /** <p> Display the matched pairs on the display after the fraternalPairing gets run once. </p> */
    public void printPairs()									{displayPairs();}
    // *************************************************************************************************************************

    /* <p> List of all Parents. Key == roll number, value == a Parent object </p> */
    private HashMap<Integer, Parent<T>> parentsMap = new HashMap<Integer, Parent<T>>();
    /* <p> List of all Children. (Key = ID number, value = Child object) </p> */
    private HashMap<Integer, Child<T>> childrenMap = new HashMap<Integer, Child<T>>(); 
    /* <p> List of all Parents by their roll number. </p> */
    private List<Integer> parents = new ArrayList<Integer>();
    /* <p> List of Children by their id number. </p> */
    private List<Integer> children = new ArrayList<Integer>();
    /* <p> Holds the list of matched pairs after fraternalPairing method gets run once.</p> */
    private HashMap<Integer, Integer> pairs = new HashMap<Integer, Integer>();
    /* <p> Helper methods for setting & getting field values. </p> */
    private HashMap<Integer, Parent<T>> getPM()     {return this.parentsMap;}
    private HashMap<Integer, Child<T>> getCM()      {return this.childrenMap;}
    private List<Integer> getParentList()           {return this.parents;}
    private List<Integer> getChildrenList()         {return this.children;}
    private HashMap<Integer, Integer> getMatched()	{return this.pairs;}

    /*
     * <p> Make a deep copy of the given M and initializes all fields. </p>
     */
    private void init(Matching<T> M) {
    	for (int c:M.getChildren())
    		this.children.add(c);
    	
    	for(int p:M.getParentList())
    		this.parents.add(p);
    	
    	for(int key:M.getChildrenMap().keySet())
    		this.childrenMap.put(key, M.getChildrenMap().get(key));
    	
    	for(int key:M.getParentsMap().keySet())
    		this.parentsMap.put(key, M.getParentsMap().get(key));
    }
    
    /*
     * <p> A Helper method that assign parent and Children Map also </br>
     * parents and children lists from the given FileManager object. </p> 
     */
    private void assignAll(FileManager<T> f) {
        this.parentsMap     = f.getParentMap();
        this.childrenMap    = f.getChildrenMap();
        this.parents        = f.getParents();
        this.children       = f.getChildren(); 
    }

    /*
     * A helper method for producing matched list.
     * @return matched list in hashMap (key = childrenId, value = parentRollNumber)
     */
    protected HashMap<Integer, Integer> match() { 

        if (getParents().isEmpty() || getChildren().isEmpty())
            return null;
        
        HashMap<Integer, Integer> parentMatched = new HashMap<Integer, Integer>();
        
    	// Initializing ParentMatched list with null
        for (Integer pRn:getParentsMap().keySet())
    		parentMatched.put(pRn, null);
    	
        // Initializing matched list with null
    	for (Integer cId:children)
    		getPairs().put(cId, null);
    	
    	HashMap<Integer, Integer> result = calcMatches(getChildren(), 
        												getParentsMap(), 
        												getChildrenMap(), 
        												getPairs(), 
        												parentMatched);
        return result;
    }

    
    /*
     * A helper method by which the matching happens.
     */
    private HashMap<Integer, Integer> calcMatches(List<Integer> children,
            									HashMap<Integer, Parent<T>> parentMap,
            									HashMap<Integer, Child<T>> childrenMap,
            									HashMap<Integer, Integer> matched,
            									HashMap<Integer, Integer> parentMatched) {
    	int notMatched  	= children.size(),
    			cIndex		= -1,
    			prevRepeat 	= notMatched;
   
    	while (notMatched > 0) {
    		cIndex = getNextCIndex(children, matched, cIndex);
    		Child<T> currChild  = childrenMap.get(children.get(cIndex));
    		int i;
            for (i = 0; i < currChild.getRankedParent().size() && 
            				matched.get(children.get(cIndex)) == null; i++) {
                // Get parent roll number from current children's ranked parent list
            	int pRn = currChild.getRankedParent().get(i);
            	
                // Adding a match or change the previous match with more preference match.
            	if (parentMap.get(pRn).getAvailChild() > 0)
                	notMatched = addMatched(matched, currChild, pRn, parentMatched, parentMap, notMatched);
                else {
                	int currCId = parentMatched.get(pRn);
                    if (morePreference(currCId, children.get(cIndex), pRn, currChild.getRankedParent().size()))
                    	notMatched = addMorePreference(pRn, currChild, children, cIndex, 
                    									matched, parentMatched, currCId, notMatched);
                }
            }
            // When some children and parent can not be matched properly and they left without
            // any matches, this if condition matches them all.
            if (i == currChild.getRankedParent().size() && prevRepeat == notMatched) {
            	for(int pRn:parentMatched.keySet()) {
            		if (parentMatched.get(pRn) == null || parentMap.get(pRn).getAvailChild() > 0) {
            			notMatched = addMatched(matched, currChild, pRn, parentMatched, parentMap, notMatched);
            			break;
            		}
            	}
            }
            prevRepeat = notMatched;
        }
    	return matched;
    }
    
    /*
     * <p> Returns the index of next avalaible children in children List. </p> 
     */
    private int getNextCIndex(List<Integer> children, 
    							HashMap<Integer, Integer> matched, 
                                Integer cIndex) {
    	cIndex = ((cIndex + 1) < children.size())
								? cIndex + 1
								: (cIndex + 1) % children.size();
    
    	while(matched.get(children.get(cIndex)) != null)
            cIndex = ((cIndex + 1) < children.size())
                                 	? cIndex + 1
                                    : (cIndex + 1) % children.size();
        return cIndex;
    }
    
    /*
     * A helper method that Adds the new match into matched and parentMatched maps.
     */
    private int addMatched(HashMap<Integer, Integer> matched, 
    						Child<T> currChild, 
    						int pRn, 
    						HashMap<Integer, Integer> parentMatched, 
    						HashMap<Integer, Parent<T>> parentMap,
    						int notMatched) {
    	matched.replace(currChild.getID(), pRn);
    	parentMatched.replace(pRn, currChild.getID());
    	parentMap.get(pRn).setAvailChild(parentMap.get(pRn).getAvailChild() - 1);
        --notMatched;
        return notMatched;
    }
    
    /*
     * A helper method that checks if there is more preferable match than older one.
     */
    private boolean morePreference(int currCId, int newCId, int pRn, int size)
    {
        for (int i = 0; i < size; i++) {
            if (parentsMap.get(pRn).getRankedChildren().get(i) == newCId)
                return true;
            if (parentsMap.get(pRn).getRankedChildren().get(i) == currCId)
                return false;
        }
        return false;
    }
    
    /*
     * A helper method that replace more preference match with older match.
     */
    private int addMorePreference(int pRn, 
    								Child<T> currChild, 
    								List<Integer> children, 
    								int cIndex, 
    								HashMap<Integer, Integer> matched, 
    								HashMap<Integer, Integer> parentMatched, 
    								int currCId,
    								int notMatched) {
    	parentMatched.replace(pRn, currChild.getID());
        if (matched.get(children.get(cIndex)) == null)
        	--notMatched;
        matched.replace(children.get(cIndex), pRn);
        if (matched.get(currCId) != null)
        	++notMatched;
        matched.replace(currCId, null);
        return notMatched;
    }
    
    /*
     * <p> A helper method for displaying the result. </p> 
     */
    private void displayPairs() {
    	if (getPairs().isEmpty()) {
    		System.out.println("There is no Result!!\nPlease run fraternalPairing() method first. ");
    	}
    	for (int cId:getChildren())
            System.out.println(cId + ", " + getPairs().get(cId));
    }
}
