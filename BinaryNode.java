import java.util.*;

public class BinaryNode {
	private BinaryNode left, right;
	private int myValue;
	
	public BinaryNode(int x)
	{	myValue = x;	}
	
	public void setValue(int x)
	{
		myValue = x;
	}
	
	// returns 0 for leaf, 2 for 2 degree node, 
	//-1 for 1 degree node with left child, 
	//and 1 for 1 degree node with right child
	public int degree()
	{
		if(left == null && right == null)
			return 0;
		else if(left != null && right != null)
			return 2;
		else if(left != null)
			return -1;
		else 
			return 1;
	}
	
	public int getValue()
	{	
		return myValue;
	}
	
	public BinaryNode left()
	{	return left;	}
	
	public BinaryNode right()
	{	return right;	}
	
	public void setLeft(BinaryNode x)
	{	left = x;	}
	
	public void setRight(BinaryNode x)
	{	right = x;	}
	
	public String toString()
	{
		String temp = myValue + "";
		return temp;
	}
}
