

class bNode {
	
	gBall data;
	bNode left;
	bNode right;
	
}


public class bTree {
	
	bNode root;
	
	double ballLocation=0;
	
	public void addNode(gBall data) {
		root=rNode(root,data);
	}
	
	public bNode rNode(bNode root,gBall data) {
		
		if(root==null) {
			bNode node=new bNode();
			node.data=data;
			node.left=null;
			node.right=null;
			root=node;
		}
		else if(data.bSize<root.data.bSize) {
			root.left=rNode(root.left,data);
		}
		else {
			root.right=rNode(root.right,data);
		}
		
		return root;
		
	}
	
	public void moveSort() {
		traverse_inorder(root);
	}
	
	public void traverse_inorder(bNode root) {
		
		if(root.left!=null) {
			traverse_inorder(root.left);
		}
		
		//System.out.print(root.data.bSize+" ");
		root.data.myball.setLocation(ballLocation,root.data.myball.getY());
		ballLocation+=root.data.bSize;		
		
		if(root.right!=null) {
			traverse_inorder(root.right);
		}
		
	}
	
}
