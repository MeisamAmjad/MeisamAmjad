/*
 * Copyright (c) 2017, 2018.
 * 
 */
package data_structures;

import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * <p>This Class makes connection between user and a database to simplify all work for the user./<p> 
 * @author amjadm@miamioh.edu
 *
 */
public class DataBaseManager {
	
	
	public DataBaseManager() 															{ super(); }
	public DataBaseManager(String schema) 												{ super(); setSchema(schema); }
	public DataBaseManager(String schema, String url, String user, String pass) 		{ super(); setAll(schema, url, user, pass); }
	public String getCurrentSchema()													{ return getSchema(); }
	public List<String[]> exeQueryReturnList(String query, int num) 					{ return run(query, num); }
	
	/*	*************************************************
	 *	Below there exist all fields and private methods.
	 *	*************************************************
	 */
	
	/* Holds the default schema value.*/
	private String schema = "genes";
	
	/* Holds the default driver value.*/
	private String _Driver = "com.mysql.jdbc.Driver";
	
	/* Holds the default URL value. */
	private String _connectionURL1 = "jdbc:mysql://localhost:3306/";
	
	/* Holds the default second part of the URL value. */
	private String _URL2 = "?autoReconnect=true&useSSL=false";
	
	/* Holds the default user value.*/
	private String _User = "root";
	
	/* Holds the default pass value. */
	private String _Pass = "Mysql12345678";
	
	/* Sets a schema by the given input schema.*/
	private void setSchema(String schema) { this.schema = schema; }
	
	/* Returns the existent schema. */
	private String getSchema() { return this.schema; }
	
	/* Sets all the given inputs into proper fields. */
	private void setAll(String schema, String url, String user, String pass) { this.schema = schema; this._connectionURL1 = url; this._User = user; this._Pass = pass; }
	
	/* Makes a connection with the database and runs the given query.*/
	private List<String[]> run(String query, int num) {
		String URL = _connectionURL1 + schema + _URL2; 
		List<String[]> output = new ArrayList<>();
		try(Connection connection = DriverManager.getConnection(URL, _User, _Pass)) {
            Class.forName(_Driver);
            ResultSet result = connection.createStatement().executeQuery(query);
            while (result.next()) {
            	switch(num){
            		case 2:
            			String id = result.getString("id");
                    	String name = result.getString("name");
                    	output.add(new String[] {id,name});
            			break;
            		case 4:
            			String ix = result.getString("id");
                    	String gene = result.getString("name");
                    	String gene_type = result.getBoolean("gene_type")? "protein coding": "NON PROTEIN!!!" ;
                    	String ref_seq_status = result.getBoolean("ref_seq_status")? "REVIEWED": "VALIDATED";
                    	output.add(new String[] {ix,gene, gene_type, ref_seq_status});
            			break;
            	}
            }
            return output;
        } catch (SQLException | ClassNotFoundException e) {
            return null;
        }
	}
}
