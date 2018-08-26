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
import java.util.List;

/**
 * <p>The Child class </br>
 * using a triple (int id, String name, arrayList<Pointer> rankedParents), where:</br>
 * id: identifier of the Child which is defined as a constant integer where  1 ≤ id ≤ |C|.</br>
 * name : string holding the name of the Child.</br>
 * twin : Boolean value which determines whether the Child is willing to be a twin. </br>
 * rankedParents : an arrayList of Pointers [0 to 4] to Parent objects, where 0 is most </br>
 * the preferred Parent and 4 is the lowest ranked.</br> </p>
 */
public class Child<T> implements Comparable<Child<T>> {
    // *************************************************************************************************************************
    /** <p>The <b>Default Constructor</b>. Sets an empty Child Object.</p> */
    public Child() {}
    /** <p>The <b>Constructor</b>. Initializes the class with the given Child object.</p> */
    public Child(Child<T> c)                            {setChild(c);}
    /** <p> Sets the ID Number.</p> */
    public void setId(int iD)                           {setI(iD);}
    /** <p> Sets the name.</p> */
    public void setName(String name)                    {setN(name);}
    /** <p> Sets list of ranked parent.</p> */
    public void setRankedParent(List<Integer> pList)    {setRP(pList);} 
    /** <p> Returns the ID Number.</p> */
    public int getID()                                  {return getI();}
    /** <p> Returns the name.</p> */
    public String getName()                             {return getN();}
    /** <p> Returns the list of ranked parent.</p> */
    public List<Integer> getRankedParent()              {return getRP();}
    /** <p> (non-Javadoc) </br> @see java.lang.Comparable#compareTo(java.lang.Object) </br> </p> */
    @Override
    public int compareTo(Child<T> otherChild)           {return compare(otherChild);}
    /** <p> (non-Javadoc) </br> @see java.lang.Object#toString() </br> </p> */
	@Override
	public String toString()                            {return showDetails();}
    // ******************************************************************************************************************
    
    /* <p> identifier of the Child which is defined as a constant integer where  1 ≤ id ≤ |C|.</p> */ 
    private int iD = 0;
    /* <p>string holding the name of the Child.</p> */
    private String name = new String();
    /* <p>an an arrayList of Pointers [0 to 4] to Parent objects, where 0 is most the preferred Parent </br>
     * and 4 is the lowest ranked. </p> */
    private List<Integer> rankedParents = new ArrayList<Integer>();
    /* <p> Helper methods for setting field values. </p> */
    private void setI(int iD)               {this.iD = iD;}
    private void setN(String name)          {this.name = name;}
    private void setRP(List<Integer> pList) {this.rankedParents = pList;}
    /* <p> Helper methods for getting field values. </p> */
    private int getI()                      {return this.iD;}
    private String getN()                   {return this.name;}
    private List<Integer> getRP()           {return this.rankedParents;}

    /*
     * <p> Initializes the class with the given parent object.</p> 
     */
    private void setChild(Child<T> C) {
        setId(C.getID());
        setName(C.getName());
        setRankedParent(C.getRankedParent());
    }

    /*
     * <p> Helper method for compareTo(). </p>
     */
    private int compare(Child<T> otherChild) {
        if (getID() > otherChild.getID())
            return 1;
        else if (getID() < otherChild.getID())
            return 0;
        else
            return -1;
    }

    /*
     * <p> A helper method for toString() which shows all field values. </p>
     */
    private String showDetails() {
        StringBuilder builder = new StringBuilder();
        builder.append("ID number      : " + getID() + "\n");
        builder.append("Name           : " + getName() + "\n");
        builder.append("Ranked Parents :\n");
        for (Integer Parent:getRankedParent())
            builder.append(Parent + " ");
        builder.append("\n");
        return builder.toString();
    }
}
