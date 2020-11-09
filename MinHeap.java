// Name: Kyle Irvine
// Student number:
public class MinHeap {

    public int size;
	public int [] mH;
	public int position;
	public MinHeap(int size){
		this.size=size;
		mH = new int [size+1];
		position = 0;
	}
	// do not change
        public void createHeap(int [] arrA){
		if(arrA.length>0){
			for(int i=0;i<arrA.length;i++){
				insert(arrA[i]);
			}
		}
                display();
	}
	// do not change
	public void display(){
		for(int i=1;i<position;i++){
			System.out.print(mH[i] + " ");
		}
		System.out.println("");
	}


	public void insert(int x){

    position++;
    mH[position] = x;
    moveUp(position);
    if (position == size) {
      position++;
    }


	}

  public boolean checkToMove(int x) {
    int parentPos = x/2;
    if (mH[parentPos] > mH[x]){
      return true;
    }
    return false;
  }
  public void moveUp(int x) {
    int parentPos = x/2;
    while(x > 0 && mH[x] < mH[parentPos]){
      swapValues(x,parentPos);
      x = parentPos;
      parentPos = parentPos/2;
    }
  }
  public void swapValues(int x, int y){
    int temp = mH[x];
    mH[x] = mH[y];
    mH[y] = temp;
  }
  public void moveDown(int val) {
    int sVal = val;
    int lChild = 2 * sVal;
    int rChild = 2 * sVal + 1;

    if(lChild < size && mH[sVal] > mH[lChild] ){
      sVal = lChild;
    }
    if (rChild < size && mH[sVal] > mH[rChild]){
      sVal = rChild;
    }
    if(sVal != val){
      swapValues(val, sVal);
      moveDown(sVal);
    }
  }
	public void deleteMin(){
    int smallestVal = mH[1];
    mH[1] = mH[size];
    mH[size] = 0;
    moveDown(1);
    size--;
    position--;
    display();

	}



}
