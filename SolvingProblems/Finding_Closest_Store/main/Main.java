/*
 * Copyright (c) 2017, 2018.
 * (( For EDUCATIONAL PURPOSE Only ))
 * 
 */
package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;
import main.Stores.*;

/**
 * <p>Runs your solution and tests its speed and accuracy.</p> 
 * @author brinkmwj (edited by amjadm)
 * @version 2009-10-15
 *
 */
public class Main {
	
	/**
	 * <p> This is a helper method that reads in the Stores.txt file. </p>
	 */
	@SuppressWarnings("resource")
	public static StoresLocation[] readEntryList() {
		StoresLocation[] ret = null;
		try {
			BufferedReader br = new BufferedReader( new FileReader( "data/Stores.txt" ) );
			String line = br.readLine(); //First line, first element holds the number of the locations
			String[] components = line.split(",");
			int count = Integer.parseInt( components[ 0 ] ); // First element of the first line
			ret = new StoresLocation[ count ];

			line = br.readLine(); // Discarded column header
			for( int i = 0; i < count; i ++ ) {
				line = br.readLine();
				components = line.split( "," );
				ret[ i ] = new StoresLocation( components[ 0 ], components[ 3 ], 
						Double.parseDouble( components[ 2 ] ), Double.parseDouble( components[ 1 ] ) );
			}
		} catch ( Exception e ) {
			e.printStackTrace( System.out );
		}
		return ret;
	}

	/**
	 * <p> This is a helper Method. Uses the brute force method to measure the distance between the given coordinate to all locations, and returns the closest one.</p>
	 * @param lng
	 * @param lat
	 * @param slocs
	 * @return
	 */
	public static StoresLocation bruteGetNearest( double lng, double lat, StoresLocation[] slocs) {
		double dist = Stores.distance( slocs[0].lng, slocs[0].lat, lng, lat );
		int index = 0;
		for( int i = 1; i < slocs.length; i ++ ) {
			double cdist = Stores.distance( slocs[i].lng, slocs[i].lat, lng, lat );
			if( cdist < dist ) {
				index = i;
				dist = cdist;
			}
		}
		return new StoresLocation( slocs[ index ] );
	}

	/**
	 * <p>Finally main method that runs and test your solution in terms of accuracy and speed.</p>
	 * @param args
	 */
	public static void main( String[] args ) {
		Stores sS = new YourSolutionStores();
		StoresLocation[] slocs = readEntryList();
		Random rng = new Random(5);
		//randomly scramble the entryList
		for( int i = 0; i < slocs.length; i ++ ) {
			int j = i + (int) ( rng.nextDouble() * ( slocs.length - i ) );
			StoresLocation t = slocs[ i ];
			slocs[ i ] = slocs[ j ];
			slocs[ j ] = t;
		}
		//Copy the list, so I have a clean copy for accuracy testing
		StoresLocation[] slocsCopy = new StoresLocation[ slocs.length ];
		for( int i = 0; i < slocs.length; i ++ )
			slocsCopy[ i ] = new StoresLocation( slocs[ i ] );
		/*
		 * Unless your build() method is very slow this time will not be very accurate.
		 */
		long startb = System.nanoTime();
		sS.build( slocs );
		long endb = System.nanoTime();
		System.out.println( "Building the data structure took: " + ( endb-startb ) / 1000000.0 + " milliseconds (time not very accurate)" );
		
		long start = 0;
		long end = 0;
		int numTrials = 1000;
		do {
			start = System.nanoTime();
			for( int i = 0; i < numTrials; i ++ ) {
				double x = -125.0 + 73.0 * rng.nextDouble();
				double y = 24.0 + 25.0 * rng.nextDouble();
				sS.getNearest( x, y );
			}
			end = System.nanoTime();
			numTrials *= 10;
		} while ( ( end - start ) / 1000000.0 < 5000 && numTrials < 1000000 );
		System.out.println( "Time: " + ( ( ( end - start ) / 1000000.0 ) / numTrials ) + " ms per search, " + numTrials + " trials" );
		/* 
		 * TEST FOR ACCURACY
		 * Use brute-force for comparison
		 */
		double studentTotal = 0.0;
		double optTotal = 0.0;
		numTrials = 1000;
		for( int i = 0; i < numTrials; i ++ ) {
			double x = -125.0 + 73.0 * rng.nextDouble();
			double y = 24.0 + 25.0 * rng.nextDouble();
			StoresLocation youSolution = sS.getNearest( x, y );
			StoresLocation opt = bruteGetNearest( x, y, slocsCopy );
			studentTotal += Stores.distance( youSolution.lng, youSolution.lat, x, y );
			optTotal += Stores.distance( opt.lng, opt.lat, x, y );
		}
		double error = studentTotal / optTotal;
		System.out.println( "Error percentage is: " + 100.0 * ( error - 1.0 ) );
	}


}
