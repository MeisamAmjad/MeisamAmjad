package data_structures;
/** 
 * ***************************************************
 *               Copyright (c) 2018
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
 * *************************************************************************************************************************
 * <p>The Parent class </br>
 * using a quadruple (int rollNum, String name, int availChild, arrayList<Pointer> rankedChildren), where:</br>
 * rollNum: identifier of the Parent which is defined by their role in the Fraternity.</br>
 * name : string holding the name of the Parent.</br>
 * availChild:  representing the maximum number of Children, they are willing to be paired with (0, 1, 2).</br>
 * rankedChildren : an arrayList of Pointers [0 to 4] to Child objects, where 0 is the most preferred Child and 4 is </br>
 * the lowest ranked.</br> </p>
 */
public class Parent<T> implements Comparable<Parent<T>> {
    // *************************************************************************************************************************
    /** <p>The <b>Default Constructor</b>. Sets an empty Parent Object.</p>	*/
    public Parent() {}
    /** <p>The <b>Constructor</b>. Initializes the class with the given parent object.</p> */
    public Parent(Parent<T> p)                          {setParent(p);}
    /** <p> Sets the Roll Number.</p> */
    public void setRollNumber(int rollNumber)           {setRN(rollNumber);}
    /** <p> Sets the name.</p> */
    public void setName(String name)                    {setN(name);}
    /** <p> Sets the available childern.</p> */
    public void setAvailChild(int availChild)           {setAC(availChild);}
    /** <p> Sets list of ranked children.</p> */
    public void setRankedChildren(List<Integer> chList) {setRC(chList);} 
    /** <p> Returns the roll number.</p> */
    public int getRollNumber()                          {return getRN();}
    /** <p> Returns the name.</p> */
    public String getName()                             {return getN();}
    /** <p> Returns the available children.</p> */
    public int getAvailChild()                          {return getAC();}
    /** <p> Returns the list of ranked children.</p> */
    public List<Integer> getRankedChildren()            {return getRC();}
    /** <p> (non-Javadoc) </br> @see java.lang.Comparable#compareTo(java.lang.Object) </br> </p> */
    @Override
    public int compareTo(Parent<T> otherParent)         {return compare(otherParent);}
    /** <p> (non-Javadoc) </br> @see java.lang.Object#toString() </br> </p> */
	@Override
	public String toString()                     	   	{return showDetails();}
    // *************************************************************************************************************************
    
    /* <p>identifier of the Parent which is defined by their role in the Fraternity</p> */
    private int rollNum = 0;
    /* <p>holding the name of the Parent.</p> */
    private String name = new String();
    /* <p>representing the maximum number of Children, they are willing to be paired with (0, 1, 2).</p> */
    private int availChild = 0;
    /* <p>an arrayList of Pointers [0 to 4] to Child objects, where 0 is the most preferred Child and 4 
     is the lowest ranked.</p> */
    private List<Integer> rankedChildren = new ArrayList<Integer>();
    /* <p> Helper methods for setting & getting fields. </p> */
    private void setRN(int rollNumber)          {this.rollNum = rollNumber;}
    private void setN(String name)              {this.name = name;}
    private void setAC(int availChild)          {this.availChild = availChild;}
    private void setRC(List<Integer> chList)    {this.rankedChildren = chList;}
    private int getRN()                         {return this.rollNum;}
    private String getN()                       {return this.name;}
    private int getAC()                         {return availChild;}
    private List<Integer> getRC()               {return rankedChildren;}

    /*
     * <p> Initializes the class with the given parent object.</p> 
     */
    private void setParent(Parent<T> p) {
        setRollNumber(p.getRollNumber());
        setName(p.getName());
        setAvailChild(p.getAvailChild());
        setRankedChildren(p.getRankedChildren());
    }

    /*
     * <p> Helper method for compareTo(). </p>
     */
    private int compare(Parent<T> otherParent) {
        if (getRollNumber() > otherParent.getRollNumber())
            return 1;
        else if (getRollNumber() < otherParent.getRollNumber())
            return 0;
        else
            return -1;
    }
    
    /*
     * <p> A helper method for toString() which shows all field values. </p>
     */
    private String showDetails() {
        StringBuilder builder = new StringBuilder();
        builder.append("Roll Number     : " + getRollNumber() + "\n");
        builder.append("Name            : " + getName() + "\n");
        builder.append("Available Child : " + getAvailChild() + "\n");
        builder.append("Ranked Childern :\n");
        for (Integer child:getRankedChildren())
            builder.append(child + " ");
        builder.append("\n");
        return builder.toString();
    }
}

