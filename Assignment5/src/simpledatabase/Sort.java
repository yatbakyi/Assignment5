package simpledatabase;
import java.util.ArrayList;
import java.util.Collections;

public class Sort extends Operator{
	
	private ArrayList<Attribute> newAttributeList;
	private String orderPredicate;
	ArrayList<Tuple> tuplesResult;
	ArrayList<Tuple> tuplesRealResult;
	Tuple tuple;
	int target = 0;
	ArrayList<String> newAttributeList2;

	
	public Sort(Operator child, String orderPredicate){
		this.child = child;
		this.orderPredicate = orderPredicate;
		newAttributeList = new ArrayList<Attribute>();
		newAttributeList2 = new ArrayList<String>();
		tuplesResult = new ArrayList<Tuple>();
		tuplesRealResult = new ArrayList<Tuple>();
		
	}
	
	
	/**
     * The function is used to return the sorted tuple
     * @return tuple
     */
	@Override
	/**public Tuple next(){
		
		//Find sort by which field
		tuple = child.next();
		for (int i = 0; i < tuple.getAttributeList().size(); i++){
			if (tuple.getAttributeName(i).equals(orderPredicate))
				target = i;
		}
		
		//Add all the tuple into newAttributeList 
		//Add all the "name" into a string arraylist for sorting
		while (tuple != null){
			newAttributeList.addAll(tuple.getAttributeList());
			newAttributeList2.add(tuple.getAttributeValue(target).toString());
			tuple = child.next();
			returner = new Tuple(newAttributeList);
		}
		
		//Sort
		Collections.sort(newAttributeList2);
		
		for (int i = 0; i < newAttributeList2.size(); i++){
			for (int j = 0; j < returner.getAttributeList().size(); j++){
				if (returner.getAttributeValue(j).equals(newAttributeList2.get(i))){
					
				}
			}
		}
		
		
		
		
		for (int z = 0; z < returner.getAttributeList().size(); z++){
			System.out.println(returner.getAttributeValue(z));
		}
		System.out.println(newAttributeList2);
		return null;
		
	}**/
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