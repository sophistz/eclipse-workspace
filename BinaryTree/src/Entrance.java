import acm.util.*;

public class Entrance {

	public static void main(String[] args) {
		
		bTree bt=new bTree();
		int x;
		
		for(int i=1;i<=10;i++) {
			x=RandomGenerator.getInstance().nextInt(1,100);
			bt.addNode(x);
			System.out.print(x+" ");
		}
		
		System.out.println("");
		
		bt.inorder();
		
	}

}
