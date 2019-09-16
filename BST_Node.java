public class BST_Node
{

  Integer data;
  BST_Node left;
  BST_Node right;

  BST_Node(Integer data)
  {
	  this.data=data;
  }

  // --- used for testing  ----------------------------------------------
  //
  // leave these 3 methods in, as is

  public Integer getData(){ return data; }
  public BST_Node getLeft(){ return left; }
  public BST_Node getRight(){ return right; }


  public boolean containsNode(Integer s)
  {
	  if ( this.data == s )
	  {
		  System.out.println("Found! " + this.data + " || " + s);
		  return true;
	  }

	  else
	  {
		  if ( this.data.compareTo(s) <= -1 )
		  {

			    if (this.right == null)
			    	return false;
			    else
			    	return this.right.containsNode(s);
		  }
		  else
		  {
			    if (this.left == null)
			    	return false;
			    else
			    	return this.left.containsNode(s);
		  }

	  }

  }

  public boolean insertNode(Integer s)
  {

//	  if(this.data == s)
//	  {
//		  System.out.println("Item already on BST!"); //Prevents duplicated insertions, off for this exercise
//		  return false;
//	  }
//	  else
//	  {
		  //System.out.println(this.data.compareTo(s));
		  if (this.data.compareTo(s) <= -1)
		  {
			  if (this.right == null)
			  {
			       this.right = new BST_Node(s);
			       return true;
			  };
			  return this.right.insertNode(s);
		  }
		  else
		  {
			  if (this.left == null)
			  {
				  this.left = new BST_Node(s);
				  return true;
			  }
			  else return this.left.insertNode(s);
		  }
//	  }



  }

  public boolean removeNode(Integer s, BST_Node mainnode)
  {
	  if (this.data.compareTo(s) >= 1)
	  {

          if (left != null)

                return left.removeNode(s, this);

          else
        	  return false;

	  }
	  else
		  if (this.data.compareTo(s) <= -1)
		  {

			  if (right != null)
				  return right.removeNode(s, this);

          else
        	  return false;

		  }
		  else
		  {

			  if (left != null && right != null)
			  {

                this.data = right.findMin().data;
                right.removeNode(this.data, this);

			  }
			  else
				  if (mainnode.left == this)
				  {
					  mainnode.left = (left != null) ? left : right;

				  }
				  else
					  if (mainnode.right == this)
					  {
						  mainnode.right = (left != null) ? left : right;
					  }

          return true;

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



  }

  public BST_Node findMin()
  {
	  if(this.left == null)
	  {
		  return this;
	  }

	  else
		  return this.left.findMin();

  }

  public BST_Node findMax()
  {
	  if(this.right == null)
	  {
		  return this;
	  }

	  else
		  return this.right.findMax();
  }

  public int getHeight()
  {

          BST_Node leftsubTree = this.left;
          BST_Node rightsubTree = this.right;

          int ldepth = -1;
          int rdepth = -1;


          if (leftsubTree != null)
          {
        	  ldepth = leftsubTree.getHeight();
          }

          if (rightsubTree != null)
          {
        	  rdepth = rightsubTree.getHeight();
          }

          if (ldepth > rdepth)
              return (ldepth + 1);
           else
              return (rdepth + 1);

  }

//REFERENCE FOR THIS CODE:
  /***************************************************************************************
  *    Title: Write a Program to Find the Maximum Depth or Height of a Tree
  *    Author: GeeksforGeeks
  *    Date: n/a
  *    Code version: n/a
  *    Availability: https://www.geeksforgeeks.org/write-a-c-program-to-find-the-maximum-depth-or-height-of-a-tree/
  *
  */


  public String toString()
  {
    return "Data: "+this.data+", Left: "+((this.left!=null)?left.data:"null")
            +",Right: "+((this.right!=null)?right.data:"null");
  }
}
