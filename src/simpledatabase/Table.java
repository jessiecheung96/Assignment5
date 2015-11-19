package simpledatabase;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Table extends Operator{
	private BufferedReader br = null;
	private boolean getAttribute=false;
	private Tuple tuple;
	private String attributeLine;
	private String dataTypeLine;

	
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
		if (getAttribute){
			try{
				// read the next line
				String tupleLine=br.readLine();
				if (tupleLine!=null){
					tuple=new Tuple(attributeLine, dataTypeLine, tupleLine);
					tuple.setAttributeName();
					tuple.setAttributeType();
					tuple.setAttributeValue();
					return tuple;
				}else return null;
			} catch (IOException ioe){
				return null;
			}
		}else{
			try{
				// read the first three lines
				attributeLine=br.readLine();
				dataTypeLine=br.readLine();
				String tupleLine=br.readLine();
				if (attributeLine!=null && dataTypeLine!=null & tupleLine!=null){
					// create a new tuple and set the attribute list
					tuple=new Tuple(attributeLine, dataTypeLine, tupleLine);
					tuple.setAttributeName();
					tuple.setAttributeType();
					tuple.setAttributeValue();
					getAttribute=true;
					return tuple;
				}else return null;
			} catch (IOException ioe){
				return null;
			}
		}
	}
	

	/**
     * The function is used to get the attribute list of the tuple
     * @return the attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return tuple.getAttributeList();
	}
	
}