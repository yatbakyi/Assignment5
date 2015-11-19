package simpledatabase;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Table extends Operator{
	private BufferedReader br = null;
	private boolean getAttribute=false;
	private Tuple tuple;
	String col;
	String col1;
	String col2;

	
	public Table(String from){
		this.from = from;
		
		//Create buffer reader
		try{
			br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/datafile/"+from+".csv")));
			
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

	
	/**
     * Create a new tuple and return the tuple to its parent.
     * Set the attribute list if you have not prepare the attribute list
     * @return the tuple
     */
	@Override
	public Tuple next(){
		//Delete the lines below and add your code here
		try{
			while (!getAttribute){
			col=br.readLine();
			col1=br.readLine();
			getAttribute=true;}

			if ((col2 = br.readLine())!= null){
			tuple = new Tuple(col, col1, col2);
			tuple.setAttributeName();
			tuple.setAttributeType();
			tuple.setAttributeValue();
			return tuple;
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	

	/**
     * The function is used to get the attribute list of the tuple
     * @return the attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return tuple.getAttributeList();
	}
	
}