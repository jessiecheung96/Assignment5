package simpledatabase;
import java.util.ArrayList;

public class Join extends Operator{

	private ArrayList<Attribute> newAttributeList;
	private String joinPredicate;
	ArrayList<Tuple> tuples1;

	
	//Join Constructor, join fill
	public Join(Operator leftChild, Operator rightChild, String joinPredicate){
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.joinPredicate = joinPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuples1 = new ArrayList<Tuple>();
		
	}

	
	/**
     * It is used to return a new tuple which is already joined by the common attribute
     * @return the new joined tuple
     */
	//The record after join with two tables
	@Override
	public Tuple next(){
		if (tuples1.isEmpty()){
			// put all tuples from the leftChild to tuples1
			while (true){
				Tuple tuple=leftChild.next();
				if (tuple==null) break;
				tuples1.add(tuple);
			}
		}
		// join a tuple from rightChild with a tuple in tuples1
		Tuple tuple=rightChild.next();
		if (tuple==null) return null;
		ArrayList<Attribute> list1=tuple.getAttributeList();
		for (int i=0; i<tuples1.size(); i++){
			ArrayList<Attribute> list2=tuples1.get(i).getAttributeList();
			for (int j=0; j<list1.size(); j++){
				for (int k=0; k<list2.size(); k++){
					if (list1.get(j).getAttributeName().equals(list2.get(k).getAttributeName())&&
						list1.get(j).getAttributeValue().equals(list2.get(k).getAttributeValue())){
							newAttributeList = new ArrayList<Attribute>();
							newAttributeList.addAll(list1);
							newAttributeList.addAll(list2);
							return new Tuple(newAttributeList);
						}
				}
			}
		}
		return null;
	}
	
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		if(joinPredicate.isEmpty())
			return child.getAttributeList();
		else
			return(newAttributeList);
	}

}