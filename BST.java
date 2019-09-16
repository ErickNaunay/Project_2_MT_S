import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import javafx.concurrent.Task;

public class BST extends Task<int[]>
{

  public BST_Node root;
  
  int size;
  int height;

  int nodesR= 0;
  int nodesL = 0;
  
  private long exec_time;
	
  ArrayList<Integer> BST_sorted = new ArrayList<Integer>();

  public BST(){ size=0; root=null; }

  public BST(int[] i)
	{
	  	int[] ic = i.clone();

	  	size=0;
	  	root=null;

		for(int num : ic)
		{
			insert(num);
		}
	}


  //used for testing, please leave as is
  public BST_Node getRoot(){ return root; }

	public boolean insert(Integer s)
	{
		if (root == null)
		{
			BST_Node incomingN = new BST_Node(s);
			root = incomingN;
			incomingN.left = null;
			incomingN.right = null;
			size++;
			height = 0;
			//System.out.println("Root stablished[]!");
			return true;
		}
	
		if (size >= 1)
		{
			if(root.insertNode(s) == true)
			{
				size++;
				//System.out.println("Node inserted!");
				return true;
			}
		}
		return false;
	}
	
	public boolean remove(Integer s)
	{
		if(size == 0 || root == null)
		{
			System.out.println("BST Empty!!, no remove");
			return false;
		}
		else
			if (root.data == s)
			{
				BST_Node head = new BST_Node(1);
				boolean notif;
	
				head.left = root;
				notif = root.removeNode(s, head);
				root = head.left;
				size--;
	            return notif;
			}
	
		//REFERENCE FOR THIS CODE:
		  /***************************************************************************************
		  *    Title: Binary search tree. Removing a node
		  *    Author: Algorithms and Data Structures
		  *    Date: n/a
		  *    Code version: n/a
		  *    Availability: http://www.algolist.net/Data_structures/Binary_search_tree/Removal
		  *
		  *    Title: Binary Search Tree | Set 2 (Delete)
		  *    Author: GeeksforGeeks
		  *    Date: n/a
		  *    Code version: n/a
		  *    Availability: https://www.geeksforgeeks.org/binary-search-tree-set-2-delete/
		  *
		  *    Title: Binary Search Trees - Remove Node Function - C++ - Part 10
		  *    Author: Paul Programming
		  *    Date: Mar 9, 2014
		  *    Code version: n/a
		  *    Availability: https://www.youtube.com/watch?v=ROtgyI64kGw
		  *
		  *    Title: BinarySearchTree class
		  *    Author: Mark Allen Weiss
		  *    Date: n/a
		  *    Code version: n/a
		  *    Availability: https://cs.nyu.edu/courses/spring07/V22.0102-002/BinarySearchTree.java
		  *
		  *    Title: How to delete a node from Binary Search Tree (BST)?
		  *    Author: Nataraja Gootooru
		  *    Date: n/a
		  *    Code version: n/a
		  *    Availability: http://www.java2novice.com/java-interview-programs/delete-node-binary-search-tree-bst/
		  *
		  ***************************************************************************************/
	
	
			else
			{
				if(root.removeNode(s, null) == true)
				{
					size--;
					System.out.println("Node deleted! --> " + s);
					return true;
				}
				else
					System.out.println("Node " + s + " not in BST :/");
			}
	
	
		return false;
	}
	
	public Integer findMin()
	{
		if(size!=0)
		{
			Integer min = root.findMin().data;
			System.out.println("MIN in BST -->  " + min);
			return min;
		}
		else return null;
	}
	
	public Integer findMax()
	{
		if(size!=0)
		{
			Integer max = root.findMax().data;
			System.out.println("MAX in BST -->  " + max);
			return max;
		}
		else return null;
	}
	
	public boolean empty()
	{
		if (size > 0)
		{
			System.out.println("Not empty tree || #curr elements: " + this.size());
			return false;
		}
		else
			return true;
	}
	
	public boolean contains(Integer s)
	{
		if (size <= 0)
		{
			System.out.println("Empty tree");
			return false;
		}
		else
		{
			return root.containsNode(s);
		}
	
	}
	
	public int size()
	{
		return size;
	}
	
	public int height()
	{
		if (root == null)
		{
			height = -1;
			return height;
		}
		else
			height = root.getHeight();
		return height;
	}
	
	public void BST_sort(BST_Node root)
	{
		  //will print your current tree In-Order
		  if(root!=null)
		  {
		    BST_sort(root.getLeft());
		    int num = root.getData();
		    //System.out.print(num + " ");
		    BST_sorted.add(num);
		    BST_sort(root.getRight());
		  }
	}


	@Override
	protected int[] call()
	{	
		Instant begin = Instant.now();
	    BST_sort(root);
	
	    int[] bst_sort = new int[BST_sorted.size()];

		for(int i=0; i<BST_sorted.size(); i++)
		{
			bst_sort[i] = BST_sorted.get(i);
			
			if (i % 1000 == 0)
				System.out.printf("BST SORT: value extracted from the tree [%d]\n", bst_sort[i]);
				
			updateProgress(i, BST_sorted.size());
		}
		
		Instant end = Instant.now();
		setExecutionTime(begin, end);
		
		return bst_sort;

	}
	
	private void setExecutionTime(Instant init, Instant end) {
		
		exec_time = Duration.between(init, end).toMillis();
		
	}
	
	public long getExecutionTime() {
		return exec_time;
	}

}

