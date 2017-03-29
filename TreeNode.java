
public class TreeNode {
	String 		feature;
	TreeNode 	parent;
	double 		true_pTrue;
	double 		true_pFalse;
	
	public TreeNode(String f_name, TreeNode par, double pt, double pf){
		feature 	= f_name;
		parent 		= par;
		true_pTrue 	= pt;
		true_pFalse = pf;
	}
	
	public String toString(){
		return feature;
	}
}
