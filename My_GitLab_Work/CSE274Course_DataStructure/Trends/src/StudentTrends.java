import java.util.ArrayList;

/**
 *
 * @author // Meisam Amjad
 */


public class StudentTrends implements Trends {
	CombinationHashTableWithArrayfeatures<Integer,String,Boolean> data; //Main table for keeping the strings and a number that they are repeated
	CombinationHashTableWithArrayfeatures<Integer,ArrayList<String>,Boolean> fList;//For keeping a list of the words depends on how many times they are repeated
	int nE;// Keeps the number of unique words
	int theMostRepeatedNum;// keeps the maximum number of time that a word is repeated 
	
	public StudentTrends(){
		data= new CombinationHashTableWithArrayfeatures<Integer,String,Boolean>();
		fList= new CombinationHashTableWithArrayfeatures<Integer,ArrayList<String>,Boolean>();
		nE=0;
		theMostRepeatedNum=0;
	}
	
	public void sort(int ith) {// Using some kind of Inserting sort
		ArrayList<String> array=fList.table.get(ith).second;
	    for (int i = 1; i < array.size(); i++) {
	    	String item = array.get(i);
	    	int iH = i;
	    	while (iH > 0 && array.get(iH - 1).compareTo(item) > 0)  array.set(iH, array.get(--iH));// Shifting elements  
	    	array.set(iH,item);
	    }
	    fList.table.get(ith).third=true;//It means this Column is already sorted.So, next time it's not going to get sorted.
	    fList.table.get(ith).second=array;//save the sorted array in it's position
	}
	private void removeItem(String s,int index) {//Remove the 'S' from the List 
		ArrayList<String> lst=fList.table.get(index).second;
		if (lst==null) return;
		for (int i=0;i<lst.size();i++){
			if (lst.get(i).equals(s)){
				lst.remove(i);
				return;
			}
		}
	}
	private Pair<Integer, Integer>  indexCalculator(int n) {//Finds in which Index of the data table, also in which position of the List 
		int n3=n, n2=0, nR=fList.table.get((theMostRepeatedNum-n2)).second.size(), l=nR;
		while (n>=nR){
			n2++;
			l=fList.table.get((theMostRepeatedNum-n2)).second.size();
			nR+=l;
		}
		n3=l-(nR-n);
		return new Pair<Integer, Integer>(n2,n3);//n2: is the index of the table (which row). n3: is the right position inside the List
	}
	
	@Override
	public void increaseCount(String s, int amount) {
		int strKey=data.strHash(s);
		int index=data.findIndex(strKey,s);// produces String hash code 
		if (index>=0){
			Triple<Integer, String,Boolean> t01=data.table.get(index);
			removeItem(s,(t01.first));
			t01.first+=amount;
			data.table.set(index, t01);//saves the item inside the data table
			index=(t01.first);//changes the index for saving inside the fList table
			Triple<Integer, ArrayList<String>,Boolean> fl01=fList.table.get(index);
			if (fl01==null)	fl01=new Triple<Integer, ArrayList<String>,Boolean>(index,new ArrayList<String>(),false);
			fl01.third=false;
			fl01.second.add(s);
			fList.table.set(index,fl01);//saves the item inside the fList table
			if (t01.first>theMostRepeatedNum) theMostRepeatedNum=t01.first;// keeps the max amount of repetition inside theMostRepeatedNum variable
		}
		else{
			index=data.properIndexForAdding(strKey, s);	// produces String hash code
			data.table.set(index,new  Triple<Integer, String,Boolean>(amount,s,false));//save the item inside the data table
			index=(amount);//changes the index for saving inside the fList table
			Triple<Integer, ArrayList<String>,Boolean> fl01=fList.table.get(index);
			if (fl01==null)	fl01=new Triple<Integer, ArrayList<String>,Boolean>(index,new ArrayList<String>(),false);
			fl01.third=false;
			fl01.second.add(s);
			fList.table.set(index,fl01);//saves the item inside the fList table
			nE++;// Adds the number of unique items
			if (amount>theMostRepeatedNum) theMostRepeatedNum=amount;// keeps the max amount of repetition inside theMostRepeatedNum variable
		}
	}

	@Override
	public int getCount(String s) {
		int index=data.findIndex(data.strHash(s),s);// Finds the position of the 's' inside the data table
		if (index>=0) return data.table.get(index).first;
		return 0;
	}
	
	@Override
	public String getNthPopular(int n) {
		Pair<Integer, Integer> ns=indexCalculator(n);// Gets the right Index of the flist table, also the right position inside the List
		int ith=theMostRepeatedNum-ns.first;
		if (fList.table.get(ith).second.size()>1 && !fList.table.get(ith).third) sort(ith);//checks if it needs to be sorted
		return fList.table.get(ith).second.get(ns.second); //Retrieves the data from the fList table
	}

	@Override
	public int numEntries() {
		return nE;
	}
}
