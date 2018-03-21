public class Cube{

	private Color up; //index 0 
	private Color front; //index 1
	private Color right; //index 2
	private Color back; //index 3
	private Color left; //index 4
	private Color down; //index 5
	private Color[] initColors = new Color[6]; //holds the initial colors of the faces.
	private Cube[] orientations = new Cube[24]; //hold all the values for the orientations;


	public Cube(Color[] faces){
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

	public void rotate(){

		Color tempFront = this.front;
		Color tempRight = this.right;
		Color tempBack = this.back;
		Color tempLeft = this.left;

		this.front = tempLeft;
		this.right = tempFront;
		this.back = tempRight;
		this.left = tempBack;

	}

	public void rightRoll(){
		
		Color tempUp = this.up;
		Color tempDown = this.down;
		Color tempRight = this.right;
		Color tempLeft = this.left;

		this.right = tempUp;
		this.down = tempRight;
		this.up = tempLeft;
		this.left = tempDown;
	}

	public void leftRoll(){

		Color tempUp = this.up;
		Color tempDown = this.down;
		Color tempRight = this.right;
		Color tempLeft = this.left;

		this.right = tempDown;
		this.down = tempLeft;
		this.up = tempRight;
		this.left = tempUp;
	}

	public void identity(){

		this.up = initColors[0];
		this.front = initColors[1];
		this.right = initColors[2];
		this.back = initColors[3];
		this.left = initColors[4];
		this.down = initColors[5];

	}

	public void reset(){
		identity();
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
	public void next(){
		/* if(){
			;
		} */
	}

}