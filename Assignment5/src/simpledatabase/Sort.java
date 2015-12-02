package simpledatabase;
import java.util.ArrayList;
import java.util.Collections;

public class Sort extends Operator{
	
	private ArrayList<Attribute> newAttributeList;
	private String orderPredicate;
	ArrayList<Tuple> tuplesResult;
	ArrayList<Tuple> tuplesRealResult = new ArrayList<Tuple>();
	Tuple tuple;
	int target = 0;
	ArrayList<String> newAttributeList2 = new ArrayList<String>();

	
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
		//add all tuples into tuplesResult
		tuple = child.next();
		while (tuple != null){
			tuplesResult.add(tuple);
			tuple = child.next();
		}
		
		//start ordering
		while(tuplesResult.size() > 0){
			Tuple min = tuplesResult.get(0);
			newAttributeList = min.getAttributeList();
			
			//find which colomn to match with orderPredicate
			for (int i = 0; i < newAttributeList.size(); i++){
				if (newAttributeList.get(i).getAttributeName().equals(orderPredicate)){
					for (int j = 0; j < tuplesResult.size(); j++){
						Tuple temp = tuplesResult.get(j);
						//make the ascending order
						if (min.getAttributeValue(i).toString().compareTo(temp.getAttributeValue(i).toString()) > 0)
							min = temp;
					}
					tuplesResult.remove(min);
					return min;
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
		return child.getAttributeList();
	}

}