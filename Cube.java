public class Cube{

	private Color up; //index 0 
	private Color front; //index 1
	private Color right; //index 2
	private Color back; //index 3
	private Color left; //index 4
	private Color down; //index 5
	private Color[] initColors = new Color[6]; //holds the initial colors of the faces.
	private Cube[] orientations = new Cube[25]; //hold all the values for the orientations;
	private int counter = 0; //used in method next to determine the next state


	public Cube(Color[] faces) throws IllegalStateException{

		if (faces.length != 6){

			throw new IllegalStateException("Number of faces is required to be 6!");


		}

		for (int i = 0; i < faces.length; i++){
			if (faces[i] == null){

				throw new IllegalStateException("Face cannot be null");
			}
		}

		up = faces[0];
		front = faces[1];
		right = faces[2];
		back = faces[3];
		left = faces[4];
		down = faces[5];

		for(int i = 0; i < faces.length; i++){
			initColors[i] = faces[i];
		}
	}

	public Cube (Cube other){

		this.up = other.getUp();
		this.front = other.getFront();
		this.right = other.getRight();
		this.back = other.getBack();
		this.left = other.getLeft();
		this.down = other.getDown();

		initColors[0] = this.up;
		initColors[1] = this.front;
		initColors[2] = this.right;
		initColors[3] = this.back;
		initColors[4] = this.left;
		initColors[5] = this.down;


	}

	public Cube copy(){

		Cube copy = new Cube(new Color[] {up, front, right, back, left, down});
		return copy;

	}

	public Color getUp(){ //getter for up face
		return up;
	}

	public Color getFront(){ //getter for front face
		return front;
	}
	
	public Color getRight(){ //getter for right face
		return right;
	}
	
	public Color getBack(){ //getter for back face
		return back;
	}
	
	public Color getLeft(){ //getter for left face
		return left;	
	}
	
	public Color getDown(){ //getter for down face
		return down;
	}

	public String toString(){
		return "[" + this.up + ", " + this.front + ", " + this.right + ", "+ this.back + ", "+ this.left + ", "+ this.down + "]";
	}


	private void rotate(){

		Color tempFront = this.front;
		Color tempRight = this.right;
		Color tempBack = this.back;
		Color tempLeft = this.left;

		this.front = tempLeft;
		this.right = tempFront;
		this.back = tempRight;
		this.left = tempBack;

	}

	private void rightRoll(){
		
		Color tempUp = this.up;
		Color tempDown = this.down;
		Color tempRight = this.right;
		Color tempLeft = this.left;

		this.right = tempUp;
		this.down = tempRight;
		this.up = tempLeft;
		this.left = tempDown;
	}

	private void leftRoll(){

		Color tempUp = this.up;
		Color tempDown = this.down;
		Color tempRight = this.right;
		Color tempLeft = this.left;

		this.right = tempDown;
		this.down = tempLeft;
		this.up = tempRight;
		this.left = tempUp;
	}

	private void identity(){

		this.up = initColors[0];
		this.front = initColors[1];
		this.right = initColors[2];
		this.back = initColors[3];
		this.left = initColors[4];
		this.down = initColors[5];

	}

	public void reset(){
		identity();
		counter = 0;
		for(int i = 0; i < orientations.length; i++){
			orientations[i] = null;
		}
	}
	//after reset:
	//Rotate, Rotate, Rotate, RightRoll, Rotate, Rotate, Rotate, RightRoll, Rotate, Rotate, Rotate, LeftRoll, Rotate,
	//Rotate, Rotate, LeftRoll, Rotate, Rotate, Rotate, RightRoll, Rotate, Rotate, Rotate
	
	//how to keep track of what orientation you are at in the sequence?

	/* Following a call to the method reset, each call to the method next changes the orientation of the cube according to the following list of operations:
			====> Identity, Rotate, Rotate, Rotate, RightRoll, Rotate, Rotate, Rotate, RightRoll, Rotate, Rotate, Rotate, LeftRoll, Rotate,
				Rotate, Rotate, LeftRoll, Rotate, Rotate, Rotate, RightRoll, Rotate, Rotate, Rotate.
 */
	public void next() throws IllegalStateException{

		//should be in a try-catch block?
		
		counter++;

		if (counter>24){

			throw new IllegalStateException("No more possible orientations");


		}

		if (counter == 1){

			this.identity();
			Cube tempCube = this.copy();
			orientations[counter] = tempCube;
			for(int i = 1; i < counter; i++){
				if(tempCube.isEquals(orientations[i])){
					throw new IllegalStateException("New orientation matches previous");
				}
			}
			

		}

		//cases for rightRoll

		else if (counter == 5 || counter == 9 || counter == 21){

			this.rightRoll();
			Cube tempCube = this.copy();
			orientations[counter] = tempCube;
			for(int i = 1; i < counter; i++){
				if(tempCube.isEquals(orientations[i])){
					throw new IllegalStateException("New orientation matches previous");
				}
			}

		}

		//cases for leftRoll

		else if (counter == 13 || counter == 17){

			this.leftRoll();
			Cube tempCube = this.copy();
			orientations[counter] = tempCube;
			for(int i = 1; i < counter; i++){
				if(tempCube.isEquals(orientations[i])){
					throw new IllegalStateException("New orientation matches previous");
				}
			}

	
		}

		//cases for rotate

		else if (counter<=24) {

			this.rotate();
			Cube tempCube = this.copy();
			orientations[counter] = tempCube;
			for(int i = 1; i < counter; i++){
				if(tempCube.isEquals(orientations[i])){
					throw new IllegalStateException("New orientation matches previous");
				}
			}
		}



	}


	public boolean hasNext(){

		if(counter<24 && counter>=0){

			return true;
		}

		return false;

	}

	private boolean isEquals(Cube other){
		if(this.up == other.getUp() && this.front == other.getFront() && this.right == other.getRight() && this.back == other.getBack() && this.left == other.getLeft() && this.down == other.getDown()){
			return true;
		}else{
			return false;
		}
	}

}