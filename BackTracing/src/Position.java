
public class Position {
	public Position() {
		
	}
	
	public Position(int row, int col) {
		this.row=row;
		this.col=col;
	}
	
	public String toString() {
		return "("+row+"("+col+")";
	}
	
	int row;
	int col;
}
