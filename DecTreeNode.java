
public class DecTreeNode {
	int feature;
	public DecTreeNode[] treeChildren = new DecTreeNode[2];
	public int []		 classChildren = new int[2];
	
	public DecTreeNode(int f){
		feature = f;
	}

};
