import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
public class BinarySearchTree {
    private BinaryNode root;
    public BinarySearchTree(){
        root = null;
    }
    public void add(BinaryNode x){
        if(root == null){
            root = x;
            return;
        }
        add(root, x);
    }    
    public BinaryNode getRoot() {
    	return root;
    }
    private void add(BinaryNode parent, BinaryNode x){
    	if(parent == null) return;
        if(x.getValue()<(parent.getValue())) {
        	if(parent.left()==null)
        		parent.setLeft(x);
            else
                add(parent.left(),x);
        }
        else {
            if(parent.right()==null)
                parent.setRight(x);
            else
                add(parent.right(),x);
        }
    }
    public String preOrder(){
        return preOrder(root).trim() + " ";
    }
    private String preOrder(BinaryNode k){
        String temp = "";
        if(k != null){
            // use value
            temp += k.getValue()+ " ";
            // go left
            temp += preOrder(k.left());
            // go right
            temp += preOrder(k.right());
        }
        return temp;
    }
    public String postOrder(){
    	return postOrder(root).trim() + " ";
    }
    private String postOrder(BinaryNode k){
        String temp = "";
        if(k != null){
            // go left
            temp += postOrder(k.left());
            // go right
            temp += postOrder(k.right());
            // use value
            temp += k.getValue()+ " ";
        }
        return temp;
    }
    public String inOrder(){
        return inOrder(root);
    }
    public String inOrder(BinaryNode k){
        String temp = "";
        if(k != null){
            temp += inOrder(k.left());
            temp += k.getValue() + " ";
            temp += inOrder(k.right());
        }
        return temp;
    }
    public ArrayList<String> fullLevelOrder(){
        ArrayList<String> nodes = new ArrayList<String>();
        int count = 1;
        int height = getHeight(root);
        int h = 0;
        Queue<BinaryNode> queue = new LinkedList<>();
        queue.add(root);
        while(count <= 31 && h <= height){
            BinaryNode k = queue.remove();
            if(k == null){
            	nodes.add("null");
                queue.add(null);
                queue.add(null);
            }
            else{
            	nodes.add("" + k.getValue());
                if(k.left() == null){
                    queue.add(null);
                }
                else{
                    queue.add(k.left());
                }
                if(k.right() == null){
                    queue.add(null);
                }
                else{
                    queue.add(k.right());
                }
            }
            count++;
        }
        return nodes;
    }
    public void printFullTree(ArrayList<String> order, int depth) {
        int n = 0;
        while (n < depth) {
            System.out.println(order.get(n));
            n++;
        }
    }
    public String levelOrder(){
        String[] temp = new String[31];
        int i = 0;
        Queue<BinaryNode> queue = new LinkedList<BinaryNode>();
        queue.add(root);
        while(!queue.isEmpty()){
            BinaryNode k = queue.remove();
            temp[i] = Integer.toString(k.getValue());
            if(k.left()!=null)
                queue.add(k.left());
            if(k.right()!=null)
                queue.add(k.right());
            i++;
        }
        return Arrays.toString(temp);
    }
    public String reverseOrder(){
        return reverseOrder(root);
    }
    public String reverseOrder(BinaryNode k){
        String temp = "";
        String[] array = inOrder().split(" ");
        for(int i = array.length - 1; i >= 0; i--){
            temp += array[i] + " ";
        }
        return temp;
    }
    public int getWidth(){
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < getHeight(); i++){
            int width = getWidthAtLevel(i);
            if(width > max){max = width;}
        }
        return max;
    }
    public int getWidthAtLevel(int level){
        if(root == null){
            return -1;
        }
        if(level > getHeight()){
            return -1;
        }
        if(level == 0){
            return 1;
        }
        Queue<BinaryNode> queue = new LinkedList<>();
        if(root.left() != null)
            queue.add(root.left());
        if(root.right() != null)
            queue.add(root.right());
        int currwidth = queue.size();
        for(int i = 0; i < level; i++){
            currwidth = queue.size();
            int temp = currwidth;
            while(temp > 0){
                BinaryNode removed = queue.remove();
                if(removed.left() != null)
                    queue.add(removed.left());
                if(removed.right() != null)
                    queue.add(removed.right());
                temp--;
            }
        }
        return currwidth;
    }
    public boolean isFull(){
        return isFull(root);
    }
    public boolean isFull(BinaryNode k){
        if(k == null){
            return true;
        }
        if(k.left() == null && k.right() == null)
            return true;
        if(k.left() == null || k.right() == null){
            return false;
        }
        return isFull(k.left()) && isFull(k.right());
    }
    public int getNumLeaves() {
    	return getNumLeaves(root);
    }
    public int getNumLeaves(BinaryNode k){
        if(k.left() == null && k.right() == null){
            return 1;
        }
        if(k.left() == null){
            return getNumLeaves(k.right());
        }
        if(k.right() == null){
            return getNumLeaves(k.left());
        }
        return getNumLeaves(k.right()) + getNumLeaves(k.left());
    }
    public int getNumLevels(){
        return getHeight() + 1;
    }
    public int getSmallest() {
    	BinaryNode temp = root;
        while(temp.left() != null)
            temp = temp.left();
        return temp.getValue();
    }
    public int getLargest() {
    	BinaryNode temp = root;
        while(temp.right() != null){
            temp = temp.right();
        }
        return temp.getValue();
    }
    public boolean contains(int k) {
    	if(search(root, k) != null)
            return true;
        return false;
    }
    public int diameter(){
        if(root == null){
            return 0;
        }
        if(root.right() == null && root.left() == null){
            return 1;
        }
        if(root.right() == null){
            return 2 + getHeight(root.left());
        }
        if(root.left() == null){
            return 2 + getHeight(root.right());
        }
        return 3 + getHeight(root.left()) + getHeight(root.right());
    }
    public int getHeight(){
        return getHeight(root);
    }
    private int getHeight(BinaryNode k){
        if(k == null)
            return -1;
        return 1 + Math.max(getHeight(k.left()), getHeight(k.right()));
    }
    public int getNumNodes(){
        return getNumNodes(root);
    }
    public int getNumNodes(BinaryNode k){
        if(k == null){
            return 0;
        }
        return 1 + getNumNodes(k.left()) + getNumNodes(k.right());
    }
    public BinaryNode remove(int target){
    	if(root == null) 
            return null;
    	BinaryNode temp = root;
    	BinaryNode inorderSuccessor;
    	if(temp.getValue()==(target)){
    		if(temp.left() == null && temp.right() == null){
    			root = null;
    			return temp;
    		}
    		else if(temp.left() == null){
    			root = root.right();
    			temp.setRight(null);;
    			return temp;
    		}
    		if(temp.right() == null){
    			root = root.left();
    			temp.setLeft(null);
    			return temp;
    		}
    		inorderSuccessor = successor(root);
    		swap(root,inorderSuccessor);
            if(root.right() == inorderSuccessor){
                root.setRight(inorderSuccessor.right());
                inorderSuccessor.setRight(null);
                return inorderSuccessor;
            }
    		return remove(root.right(),target);
    	}
    	return remove(root,target);
    }

    private BinaryNode remove(BinaryNode startNode, int target){
    	BinaryNode nodeToRemove, inorderSuccessor;
    	BinaryNode parent = search(startNode,target);
    	if(parent == null) return null;
    	//decide if it is a left or right child
    	boolean isLeft = parent.left()!=null &&parent.left().getValue()==(target);
    	nodeToRemove = isLeft ? parent.left() : parent.right();
    	//degree 0
    	if(nodeToRemove.left() == null && nodeToRemove.right() == null){
    		if(isLeft)
    			parent.setLeft(null);
    		else
    			parent.setRight(null);
    	return nodeToRemove;
    	}
    	//degree 1
    	else if(nodeToRemove.left() == null){
    		if(isLeft)
    			parent.setLeft(nodeToRemove.right());
    		else
    			parent.setRight(nodeToRemove.right());
    		nodeToRemove.setRight(null);
    		return nodeToRemove;
    	}
    	else if(nodeToRemove.right() == null){
    		if(isLeft)
    			parent.setLeft(nodeToRemove.left());
    		else
    			parent.setRight(nodeToRemove.left());
    		nodeToRemove.setLeft(null);
    		return nodeToRemove;
    	}
    	//degree 2
    	else{
    		inorderSuccessor = successor(nodeToRemove);
    		swap(inorderSuccessor, nodeToRemove);
    		if(nodeToRemove.right()==inorderSuccessor){
    			nodeToRemove.setRight(inorderSuccessor.right());
    			inorderSuccessor.setRight(null);
    			return inorderSuccessor;
    		}
    		return remove(nodeToRemove.right(), target);
    	}
    }
    public BinaryNode search(BinaryNode parent, int target){
        if(parent == null) return null;
        if(parent.left()!=null && parent.left().getValue()==(target)||parent.right()!=null && parent.right().getValue()==(target))
            return parent;
        else if(target < (parent.getValue()))
            return search(parent.left(),target);
        else
            return search(parent.right(),target);
        }
    private BinaryNode successor(BinaryNode k){
    	BinaryNode temp = k;
    	temp = temp.right();
        if(temp == null){
            temp = search(root, k.getValue());
        }
    	while(temp.left() != null)
    		temp = temp.left();
    	return temp;
    }
    private void swap(BinaryNode x, BinaryNode y){
    	int k = x.getValue();
    	x.setValue(y.getValue());
    	y.setValue(k);
    }
    public String toString() {
    	return inOrder();
    }
}
