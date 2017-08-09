/*
 * Copyright (c) 2017, 2018.
 * (( For EDUCATIONAL PURPOSE Only ))
 * 
 */
package main;

/**
 * <p> This class holds each store's details. Also it is an abstract class and ready to be implemented by your solution.</p>
 * @author brinkmwj (edited by amjadm)
 */
public abstract class Stores {
	
	/**
	 * <p>Add all entries in the array to your data structure.</p>
	 * <p>Note that it also should prevent duplication.</p>
	 * @param allLocations An array containing all of the Stores locations.
	 */
	public abstract void build( StoresLocation[] allLocations );

	/**
	 * <p> Return a reference to a closest store location to the given coordinates. </p>
	 * @param lng
	 * @param lat
	 * @return
	 */
	public abstract StoresLocation getNearest( double lng, double lat );
	
	/**
	 *<p> Holds all details about location of the each store.</p> 
	 * @author amjadm
	 *
	 */
	public static class StoresLocation {
		
		/**
		 * <p> Holds the city of a store.</p>
		 */
		public String city = "";
		
		/**
		 * Holds the address of a store.</p>
		 */
		public String address = "";
		
		/**
		 * <p>Holds the longitude ( or X ) of a store</p>
		 */
		public double lng = 0.0; //longitude
		
		/**
		 * <p> Holds the latitude ( or Y ) of a store.</p>
		 */
		public double lat = 0.0; //latitude

		/**
		 * <p>Constructor.</p>
		 */
		public StoresLocation() {
			super();
		}

		public StoresLocation( String icity, String iaddress, double ilng, double ilat ) {
			super();
			this.city = icity;
			this.address = iaddress;
			this.lng = ilng;
			this.lat = ilat;
		}

		public StoresLocation( StoresLocation orig ) {
			this.city = orig.city;
			this.address = orig.address;
			this.lng = orig.lng;
			this.lat = orig.lat;
		}
	}
	
	/**
	 * <p>Given the longitude and latitude of two points, return their distance in km.</p>
	 * @param long1 longitude of first point
	 * @param lat1 latitude of first point
	 * @param long2 longitude of second point
	 * @param lat2 latitude of second point
	 * @return distance in km
	 */
	public static double distance(double long1, double lat1, double long2, double lat2){
		double R = 6371; // radius of earth, in km
		double dLat = (lat2-lat1)*Math.PI/180;
		double dLon = (long2-long1)*Math.PI/180;
		lat1 = lat1*Math.PI/180;
		lat2 = lat2*Math.PI/180;
		double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
			Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(lat1) * Math.cos(lat2); 
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
		double d = R * c;
		return d;
	}
}
