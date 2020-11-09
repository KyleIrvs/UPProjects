//Student Number: u17154783
//Name: Kyle Irvine
public class TreapOperations
{
	public static Node findKey(Node root, int key)
	{
		if(root.getKey() < key) {
			return(findKey(root.rightChild_,key));
		} else if(root.getKey() > key) {
			return(findKey(root.leftChild_,key));
		} else if(root.getKey() == key){
			return root;
		}
		return null;
	}


	public static void rotateLeft(Node root)
	{
		Node temp1 = root.rightChild_;
		Node temp2 = temp1.leftChild_;
		temp1.leftChild_ = root;
		root.rightChild_ = temp2;
		root.parent_.leftChild_ = temp1;
		temp1.parent_ = root.parent_;
		root.parent_ = temp1;
		if(temp2 != null) {
		temp2.parent_ = temp1.leftChild_;
	}
	}

	public static void rotateRight(Node root)
	{
		Node temp1 = root.leftChild_;
		Node temp2 = temp1.rightChild_;
		temp1.rightChild_ = root;
		root.leftChild_ = temp2;
		root.parent_.rightChild_ = temp1;
		temp1.parent_ = root.parent_;
		root.parent_ = temp1;
		if(temp2 != null){
		temp2.parent_ = root;
	}
	}

	public static void rotateUp(Node target)
	{
		if(target == target.parent_.leftChild_){
			rotateRight(target);
		} else {
			rotateLeft(target);
		}
	}

	public static void rotateDown(Node root)
	{
		if(root.getPriority() < root.leftChild_.getPriority() && root.getPriority() < root.rightChild_.getPriority()){
		if(root.leftChild_.getPriority() > root.rightChild_.getPriority()){
			rotateRight(root.leftChild_);
		} else if(root.rightChild_.getPriority() > root.leftChild_.getPriority()){
			rotateLeft(root.rightChild_);
		}
		} else if(root.leftChild_ == null){
			rotateLeft(root.rightChild_);
		} else if(root.rightChild_ == null) {
			rotateRight(root.leftChild_);
		}
	}

	public static void addNode(Node root, Node newNode)
	{
		if(newNode.getKey() < root.getKey() && root.leftChild_ == null){
			newNode.parent_ = root;
			root.leftChild_ = newNode;
		} else if (newNode.getKey() > root.getKey() && root.rightChild_ == null){
			newNode.parent_ = root;
			root.rightChild_ = newNode;
		} else if (newNode.getKey() < root.getKey() && root.leftChild_ != null) {
			addNode(root.leftChild_,newNode);
		} else {
			addNode(root.rightChild_,newNode);
		}

	}



	public static void removeNode(Node target)
	{

		if(target.leftChild_ != null && target.rightChild_ != null){
			if (target.leftChild_.getKey() < target.rightChild_.getKey()) {
				rotateLeft(target);
				removeNode(target);
			} else {
				rotateRight(target);
				removeNode(target);
			}
		} else if(target.leftChild_ == null && target.rightChild_ != null){
			Node temp = target.rightChild_;
			temp.parent_ = target.parent_;
			if(target.parent_.leftChild_ == target)
				target.parent_.leftChild_ = temp;
				else
				target.parent_.rightChild_ = temp;

				target = null;

		} else if(target.rightChild_ == null && target.leftChild_ != null){
			Node temp = target.leftChild_;
			temp.parent_ = target.parent_;
			if(target.parent_.leftChild_ == target)
				target.parent_.leftChild_ = temp;
				else
				target.parent_.rightChild_ = temp;

				target = null;
		} else if(target.leftChild_ == null&& target.rightChild_ == null){
			target = null;
		}
		//use parent and rotate if pritoritynis bigger/smaller
		//if target is smaller use right child leftChild_
	}


	// do not change
	public static void inorder(Node r)
         {
            if (r != null)
            {
             inorder(r.leftChild_);
             System.out.print("\nkey: " + r.getKey() + " "+ "priority: "+ r.getPriority() + " Value: "+ r.getValue());
             inorder(r.rightChild_);
         }
        }
}
