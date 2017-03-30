
public class DepTree {
	
	//dependency tree 
	/*   		 f0
	 *			/  \
	 * 		   f1	f2
	 * 		  /  \	  \
	 * 		 f3    f4   f5
	 * 	   / | \		|
	 *    f7 f6 f8		f9
	 */
	
	private double[] c1prob_ptrue  = {0.68, 0.49, 0.86, 0.51, 0.98, 0.46, 0.19, 0.84, 0.22, 0.11};
	private double[] c1prob_pfalse = {0.68, 0.32, 0.44, 0.84, 0.33, 0.70, 0.91, 0.74, 0.73, 0.04};
	
	private double[] c2prob_ptrue  = {0.31, 0.13, 0.40, 0.26, 0.25, 0.61, 0.17, 0.05, 0.30, 0.20};
	private double[] c2prob_pfalse = {0.31, 0.42, 0.68, 0.30, 0.75, 0.02, 0.33, 0.43, 0.83, 0.29};
	
	private double[] c3prob_ptrue  = {0.61, 0.66, 0.13, 0.27, 0.51, 0.40, 0.09, 0.38, 0.46, 0.70};
	private double[] c3prob_pfalse = {0.61, 0.04, 0.14, 0.74, 0.86, 0.84, 0.13, 0.71, 0.77, 0.07};
	
	private double[] c4prob_ptrue  = {0.53, 0.04, 0.23, 0.80, 0.10, 0.35, 0.29, 0.51, 0.44, 0.92};
	private double[] c4prob_pfalse = {0.53, 0.74, 0.81, 0.45, 0.48, 0.14, 0.37, 0.27, 0.28, 0.59};
	
	public double getProb(int c, int feature, int parentTrue){

		switch(c){
			case 1: 
				if (parentTrue == 1){
					return c1prob_ptrue[feature];
				} else {
					return c1prob_pfalse[feature];
				}
			case 2: 
				if (parentTrue == 1){
					return c2prob_ptrue[feature];
				} else {
					return c2prob_pfalse[feature];
				}
			case 3: 
				if (parentTrue == 1){
					return c3prob_ptrue[feature];
				} else {
					return c3prob_pfalse[feature];
				}
			case 4: 
				if (parentTrue == 1){
					return c4prob_ptrue[feature];
				} else {
					return c4prob_pfalse[feature];
				}
			default: 
				System.out.println("invalid class");
				return -1;
		}
	}
	
	public int getParentId(int n){ //get parent of feature n
		switch(n){
			case 0: return -1;
			case 1: return 0;
			case 2: return 0;
			case 3: return 1;
			case 4: return 1;
			case 5: return 2;
			case 6: return 3;
			case 7: return 3;
			case 8: return 3;
			case 9: return 5;
			default: return -1;
		}
	}
};
