
class bNode {
	
	int data;
	bNode left;
	bNode right;
	
}


public class bTree {
	
	bNode root;
	
	public void addNode(int data) {
		root=rNode(root,data);
	}
	
	public bNode rNode(bNode root,int data) {
		
		if(root==null) {
			bNode node=new bNode();
			node.data=data;
			node.left=null;
			node.right=null;
			root=node;
		}
		else if(data<root.data) {
			root.left=rNode(root.left,data);
		}
		else {
			root.right=rNode(root.right,data);
		}
		
		return root;
		
	}
	
	public void inorder() {
		traverse_inorder(root);
	}
	
	public void traverse_inorder(bNode root) {
		
		if(root.left!=null) {
			traverse_inorder(root.left);
		}
		
		System.out.print(root.data+" ");
		
		if(root.right!=null) {
			traverse_inorder(root.right);
		}
		
	}
	
}
