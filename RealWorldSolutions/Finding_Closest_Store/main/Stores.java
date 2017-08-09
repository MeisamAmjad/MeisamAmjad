/*
 * Copyright (c) 2017, 2018.
 * (( For EDUCATIONAL PURPOSE Only ))
 * 
 */
package main;

/**
 * <p> This class holds each store's details. Also it is an abstract class and ready to be implemented by your solution.</p>
 *@author amjadm
 */
public abstract class Stores {
	
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
	public abstract StoresLocation getNearest(double lng, double lat);
}
