package simpledatabase;
import java.util.ArrayList;

public class Projection extends Operator{
	
	ArrayList<Attribute> newAttributeList;
	private String attributePredicate;


	public Projection(Operator child, String attributePredicate){
		
		this.attributePredicate = attributePredicate;
		this.child = child;
		newAttributeList = new ArrayList<Attribute>();
		
	}
	
	
	/**
     * Return the data of the selected attribute as tuple format
     * @return tuple
     */
	@Override
	public Tuple next(){
		Tuple tuple=child.next();
		if (tuple==null) return null;
		newAttributeList = new ArrayList<Attribute>();
		ArrayList<Attribute> list=tuple.getAttributeList();
		// find the selected attribute
		for (int i=0; i<list.size(); i++){
			if (attributePredicate.equals(list.get(i).getAttributeName())){
				newAttributeList.add(list.get(i));
			}
		}
		if (newAttributeList.isEmpty()) return null;
		return new Tuple(newAttributeList);
	}
		

	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return child.getAttributeList();
	}

}