package simpledatabase;
import java.util.ArrayList;
//import java.util.Comparator;
import java.util.Collections;

public class Sort extends Operator{

	private ArrayList<Attribute> newAttributeList;
	private String orderPredicate;
	ArrayList<Tuple> tuplesResult;

	
	public Sort(Operator child, String orderPredicate){
		this.child = child;
		this.orderPredicate = orderPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuplesResult = new ArrayList<Tuple>();
		
	}
	
	
	/**
     * The function is used to return the sorted tuple
     * @return tuple
     */
	@Override
	public Tuple next(){
		if (tuplesResult.isEmpty()){
			// put all tuples from child to tuplesResult
			while (true){
				Tuple tuple=child.next();
				if (tuple==null) break;
				tuplesResult.add(tuple);
			}
			if (!tuplesResult.isEmpty()){
				// find the position of the orderPredicate
				int pos=0;
				for (int i=0; i<tuplesResult.get(0).getAttributeList().size(); i++){
					if (orderPredicate.equals(tuplesResult.get(0).getAttributeName(i))){
						pos=i;
						break;
					}
				}
				// put the order predicate into an arraylist
				ArrayList order=new ArrayList();
				for (int i=0; i<tuplesResult.size(); i++){
					if (orderPredicate.equals(tuplesResult.get(i).getAttributeName(pos))){
						order.add(tuplesResult.get(i).getAttributeValue(pos));
					}
				}
				Collections.sort(order);
				// sort tuplesResult according to the order in ArrayList order
				for (int i=0; i<tuplesResult.size()-1; i++){
					for (int j=i+1; j<tuplesResult.size(); j++){
						if (order.get(i).equals(tuplesResult.get(j).getAttributeValue(pos))){
							Tuple temp=tuplesResult.get(i);
							tuplesResult.set(i,tuplesResult.get(j));
							tuplesResult.set(j,temp);
						}
					}
				}
			}
		}
		// return the first tuple in tuplesResult
		if (tuplesResult.isEmpty()) return null;
		Tuple result=tuplesResult.get(0);
		if (result==null) return null;
		tuplesResult.remove(0);
		return result;
		
	}
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return child.getAttributeList();
	}

}