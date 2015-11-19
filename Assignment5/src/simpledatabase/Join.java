package simpledatabase;
import java.util.ArrayList;
import java.util.Arrays;

public class Join extends Operator{

	private ArrayList<Attribute> newAttributeList;
	private String joinPredicate;
	ArrayList<Tuple> tuples1;
	Tuple tuple;
	Tuple tuple2;
	Tuple returner;
	Boolean OK = false;
	
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
		//Add all the tuple in leftChild into tuples1
		tuple = leftChild.next();
		while (tuple!=null){
			tuples1.add(tuple);
			tuple = leftChild.next();
		}
		
		//Find the match colomn for joining
		Tuple rightTuple = rightChild.next();
		int leftMatchedColomn = 0;
		int rightMatchedColomn = 0;
		if(rightTuple == null){ //if no next tuple then end
			return null;
		}
		for (int i = 0; i < tuples1.get(0).getAttributeList().size(); i++){
			for (int j = 0; j < rightTuple.getAttributeList().size(); j++){
				if (tuples1.get(0).getAttributeName(i).equals(rightTuple.getAttributeName(j)))
					leftMatchedColomn = i;
					rightMatchedColomn = j;
					
			}
		}
		
		//Start matching all the tuple together
		while (rightTuple != null){
			newAttributeList = new ArrayList<Attribute>();
			if (rightTuple != null){
				for (int i = 0; i < tuples1.get(0).getAttributeList().size(); i++){
					if (rightTuple.getAttributeValue(rightMatchedColomn).equals(tuples1.get(i).getAttributeValue(leftMatchedColomn))){
						newAttributeList.addAll(tuples1.get(i).getAttributeList()); 
						newAttributeList.addAll(rightTuple.getAttributeList());
						OK = true;
					}
				}
				if(OK){
					returner = new Tuple(newAttributeList);
					return returner;
				}else{
					rightTuple = rightChild.next();
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