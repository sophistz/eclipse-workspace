import acm.graphics.*;

class bNode {
	
	gBall data;
	bNode left;
	bNode right;
	
}


public class bTree {
	
	bNode root;
	
	double ballLocation=50;
	
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
		
		root.data.myball.setLocation(ballLocation,root.data.myball.getY());
		ballLocation+=root.data.bSize;		
		
		if(root.right!=null) {
			traverse_inorder(root.right);
		}
		
	}
	
	public bNode findNode(GOval value) {
		return findNodeRecursive(root,value);
	}
	
	public bNode findNodeRecursive(bNode root,GOval value) {
		if(value==root.data.myball) {
			return root;
		}
		else if(value.getWidth()<root.data.bSize) {
			return findNodeRecursive(root.left,value);
		}
		else {
			return findNodeRecursive(root.right,value);
		}
	}
	
	public void deleteNode(gBall key) {
		
		bNode node=root;
		bNode parentNode=null;
		
		while(node!=null) {
			if(key.bSize<node.data.bSize) {
				parentNode=node;
				node=node.left;
			}else if(key.bSize>node.data.bSize) {
				parentNode=node;
				node=node.right;
			}else {
				break;
			}
		}
		
		if(node.right==null) {
			if(parentNode!=null) {
				if(parentNode.left==node)
					parentNode.left=node.left;
				else
					parentNode.right=node.left;
			}else {
				root=node.left;
			}
		
		}else if(node.left==null) {
			if(parentNode!=null) {
				if(parentNode.left==node)
					parentNode.left=node.right;
				else
					parentNode.right=node.right;
			}else {
				root=node.right;
			}
		
		}else {
			
			bNode replaceNode=node.left;
			bNode replaceParentNode=node;
			
			if(replaceNode.right!=null) {
				replaceParentNode=replaceNode;
				replaceNode=replaceNode.right;
			}
			
			if(replaceParentNode==node)
				replaceParentNode.left=replaceNode.left;
			else
				replaceParentNode.right=replaceNode.left;
			
			node.data=replaceNode.data;
			
		}
		
	}

	public void reorder(bNode root,gBall deletegBall) {
		if(root.left!=null) {
			reorder(root.left,deletegBall);
		}
		
		if(root.data!=deletegBall)bSim.tempTree.addNode(root.data);
		
		if(root.right!=null) {
			reorder(root.right,deletegBall);
		}
	}
	
	public void testRunning(bNode root) {
		if(root.left!=null) {
			testRunning(root.left);
		}
		
		bSim.isRunning=bSim.isRunning&&root.data.isRunning;
		
		if(root.right!=null) {
			testRunning(root.right);
		}
	}
	
}
