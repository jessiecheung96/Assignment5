package simpledatabase;
import java.util.ArrayList;

public class Selection extends Operator{
	
	ArrayList<Attribute> attributeList;
	String whereTablePredicate;
	String whereAttributePredicate;
	String whereValuePredicate;

	
	public Selection(Operator child, String whereTablePredicate, String whereAttributePredicate, String whereValuePredicate) {
		this.child = child;
		this.whereTablePredicate = whereTablePredicate;
		this.whereAttributePredicate = whereAttributePredicate;
		this.whereValuePredicate = whereValuePredicate;
		attributeList = new ArrayList<Attribute>();

	}
	
	
	/**
     * Get the tuple which match to the where condition
     * @return the tuple
     */
	@Override
	public Tuple next(){
		Tuple tuple=child.next();
		if (!whereTablePredicate.equals(child.from)) return tuple;
		while (true){
			if (tuple==null) return null;
			attributeList=tuple.getAttributeList();
			for (int i=0; i<attributeList.size(); i++){
				if (whereAttributePredicate.equals(attributeList.get(i).getAttributeName()))
					if (whereValuePredicate.equals(attributeList.get(i).getAttributeValue())){
						return tuple;
				}
			}
			tuple=child.next();
		}
	}
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return the attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		
		return(child.getAttributeList());
	}

	
}