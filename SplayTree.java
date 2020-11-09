// Name: Kyle Irvine
// Student number: u17154783

public class SplayTree<T extends Comparable<T>> {

	protected Node<T> root = null;

	/**
	 * Prints out all the elements in the tree
	 * @param verbose
	 *			If false, the method simply prints out the element of each node in the tree
	 *			If true, then the output provides additional detail about each of the nodes.
	 */
	public void printTree(Boolean verbose) {
		String result;
		result = preorder(root, verbose);
		System.out.println(result);
	}

	protected String preorder(Node<T> node, Boolean verbose) {
		if (node != null) {
			String result = "";
			if (verbose) {
				result += node.toString()+"\n";
			} else {
				result += node.elem.toString() + " ";
			}
			result += preorder(node.left, verbose);
			result += preorder(node.right, verbose);
			return result;
		}
		return "";
	}

	////// You may not change any code above this line //////

	////// Implement the functions below this line //////

	/**
	* Checks whether a given element is already in the tree.
	* @param elem
	* 		 	The element being searched for in the tree
	* @return
	*			Returns true if the element is already in the tree
	*			Returns false if elem is not in the tree
	*
	*/
	public boolean contains(T elem) {
		// Your code here
		if(root != null) {
		return(findEl(root,elem));
	} else{
		return false;
	}

	}

	public boolean findEl(Node<T> Nroot, T el) {
		if (el.compareTo(Nroot.elem) == 0) {
			return true;
		} else if ((el.compareTo(Nroot.elem) < 0) && (Nroot.left != null)) {
			return (findEl(Nroot.left, el));
		} else if((el.compareTo(Nroot.elem) < 0) && (Nroot.left == null)){
			return false;
		} else if((el.compareTo(Nroot.elem) > 0 ) && (Nroot.right != null)){
			return(findEl(Nroot.right, el));
		} else if((el.compareTo(Nroot.elem) > 0) && (Nroot.right== null)){
			return false;
		}
		return false;
	}
	/**
	* Inserts the given element into the tree, but only if it is not already in the tree.
	* @param elem
	* 		 	The element to be inserted into the tree
	* @return
	*			Returns true if the element was successfully inserted into the tree.
	*			Returns false if elem is already in the tree and no insertion took place.
	*
	*/
	public boolean insert(T elem) {
		// Your code here

		if(contains(elem) == true){

			return false;
		} else {
			if(root == null){
			 Node<T> temp = new Node<T>(elem);
			 root = temp;
			} else {
				tryInsert(root, elem);
				return true;
			}
		}
		return false;
	}
	private void tryInsert(Node<T> Groot, T elem){

		if ((elem.compareTo(Groot.elem) < 0) && (Groot.left != null)) {
			tryInsert(Groot.left, elem);
		} else if((elem.compareTo(Groot.elem) < 0) && (Groot.left == null)){
			Groot.left = new Node<T>(elem);
		} else if((elem.compareTo(Groot.elem) > 0) && (Groot.right != null)){
			tryInsert(Groot.right, elem);
		} else if((elem.compareTo(Groot.elem) > 0) && (Groot.right== null)){
			Groot.right = new Node<T>(elem);
		}
	}

private Node<T> findNode (Node<T> temp, T el){
	if (el.compareTo(temp.elem) == 0) {
		return temp;
	} else if ((el.compareTo(temp.elem) < 0) && (temp.left != null)) {
		return(findNode(temp.left, el));
	}  else if((el.compareTo(temp.elem) > 0 ) && (temp.right != null)){
		return(findNode(temp.right, el));
	}
	return temp;
}

private Node<T> findParent(Node<T> temp, T el){
	if (el.compareTo(temp.left.elem) == 0) {
		return temp;
	} else if(el.compareTo(temp.right.elem) == 0){
		return temp;
	} else if ((el.compareTo(temp.elem) < 0)) {
		return(findParent(temp.left, el));
	}  else if((el.compareTo(temp.elem) > 0 )){
		return(findParent(temp.right, el));
	}
	return temp;
}
	/**
	 * Accesses the node containing elem.
	 * If no such node exists, the tree should remain unchanged.
	 * If the element is found in the tree, the tree should be splayed so that the accessed node becomes the new root.
	 * @param elem
	 *			The element being accessed
	 */
	public void access(T elem) {
		// Your code here
		boolean foundNode = contains(elem);
		if ( foundNode != false){
			Node<T> temp = findNode(root, elem);
			if (elem.compareTo(root.elem) == 0){
				return;
			} else if(temp == root.left || temp == root.right){
				Node<T> parent = findParent(root,elem);
				if (parent.elem.compareTo(temp.elem) < 0){
					Node<T> leftTemp = temp.left;
					temp.left = parent;
					parent.right = leftTemp;
					root = temp;
				} else {
					Node<T> rightTemp = temp.right;
					temp.right = parent;
					parent.left = rightTemp;
					root = temp;
				}
			} else {
				Node<T> parent = findParent(root, elem);
				Node<T> GParent = findParent(root, parent.elem);
				if (parent.elem.compareTo(temp.elem) < 0 && GParent.elem.compareTo(parent.elem) < 0){
					Node<T> cLeft = temp.left;
					temp.left = parent;
					parent.right = cLeft;

					GParent.right = temp;

				} else if(parent.elem.compareTo(temp.elem) > 0 && GParent.elem.compareTo(parent.elem) > 0){
					Node<T> cRight = temp.right;
					temp.right = parent;
					parent.left = cRight;

					GParent.left = temp;

				}else if(parent.elem.compareTo(temp.elem) > 0 && GParent.elem.compareTo(temp.elem) < 0){
					Node<T> cRight = temp.right;
					temp.right = parent;
					parent.left = cRight;


					GParent.right = temp;

				}else if(parent.elem.compareTo(temp.elem) < 0 && GParent.elem.compareTo(temp.elem) > 0){
					Node<T> cLeft = temp.left;
					temp.left = parent;
					parent.right = cLeft;

					GParent.left = temp;

				}
				access(elem);
			}


		}
	}
}
