
/*
 * I’ve used an array where duplicate points have been omitted and I’ve kept a deep copy of it in my own data
 * structure. Each time I specify a boundary with a specific radius and measure the points in that boundary from
 * the target point. If I don’t find my desired point in that boundary, I duplicate the radius and this time, I 
 * will only measure the new points from the target point (I don’t measure points more that once).  Due to the 
 * earth’s surface, which is not a complete sphere, and since I look at boundaries in circles around the target 
 * point, if I find a point in the last boundary, since that point might not be in fact the nearest point, I will 
 * increase the boundary radius one more time and this time, I will again measure the new points. If I find a point
 * with a shorter distance, that new point will in fact be the correct result. Worst-case running time would be 
 * when the desired point is in the most outer boundary. In this case, all points have to be measured to find the 
 * desired point, which time would be O(N) in this case.
 */
import java.util.ArrayList;



public class StudentStarbucks extends Starbucks{
	private StarbucksLocation[] allLocs;
	private int allLocsLength;
		
	public StudentStarbucks(){
		super();
	}
	/*
	 * This Method checks if there is any duplicate of target inside the source.
	 */
	private boolean contain(ArrayList<StarbucksLocation> source, StarbucksLocation target){
		for (StarbucksLocation el:source)
			if (el.lat==target.lat && el.lng==target.lng) return true;
		return false;
	}
	
	/*
	 * This Method checks the lat,lon point to be between boundary1 to boundary2 from lonT,latT point (target point).
	 */
	private boolean boundaryCheck(double lon,double lat,double lonT,double latT,double boundary1,double boundary2){
		double result= ((lat-latT)*(lat-latT))+((lon-lonT)*(lon-lonT));
		return result>boundary1 && result<=boundary2;
	}
	
	
	public void build(StarbucksLocation[] allLocations) {
		ArrayList<StarbucksLocation> tempLst=new ArrayList<>();
		
		//Get rid of duplicates.
		for(int i=0;i<allLocations.length;i++)
			if (!contain(tempLst,allLocations[i])) 	tempLst.add(allLocations[i]);	
		
		//Make a deep copy of final list 
		allLocsLength=tempLst.size();
		allLocs=new StarbucksLocation[allLocsLength];
		for (int i=0;i<allLocsLength;i++)	allLocs[i]=new StarbucksLocation(tempLst.get(i));
		
	}
	
	/*
	 * A helper method.
	 * Look for lon,lat point inside the specific boundaries.
	 */
	private StarbucksLocation getNearest_helper(double lon,double lat,double boundary1,double boundary2){
		double dist=1000000.0;
		StarbucksLocation result = new StarbucksLocation();
		for(StarbucksLocation el:allLocs){
			if (boundaryCheck(el.lng, el.lat,lon,lat, boundary1,boundary2)){
				double dst =distance(el.lng, el.lat, lon, lat);
				if(dst < dist){
					dist = dst;
					result=el;
					if (dist<0.01) return result; 
				}
			}
		} 
		return result;
	}
	
	/*
	 * Look for lon,lat point inside the specific boundaries.
	 */
	@Override
	public StarbucksLocation getNearest(double lon, double lat) {
		double b1=0,b2=1;
		StarbucksLocation result = new StarbucksLocation();
		StarbucksLocation result2 = new StarbucksLocation();
		boolean firsttime=true;
		while(result.lng==0.0 && result.lat==0.0){
			result=getNearest_helper(lon, lat, b1,b2);
			if (firsttime && result.lat!=0.0){
				firsttime=false;
				result2=result;
				result=new StarbucksLocation();
			}
			b1=b2;
			b2*=2;
		}
		if (result.lat==0.0) return result2;
		else if (distance(result.lng, result.lat, lon, lat)<distance(result2.lng, result2.lat, lon, lat)) return result;
		else return result2; 
	}
}
