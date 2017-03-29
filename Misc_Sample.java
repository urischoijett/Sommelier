
public class Misc_Sample extends Sample{
	
	public  int[] 		features_binary = new int[13];
	
	
	public Misc_Sample(double[] new_features){
		features_detailed = new_features.clone();
		
		true_class = (int) features_detailed[0];	
		
		for (int i=0; i<13; i++){ //for each feature, if the value is greater than the threshold, set to true, else false
			if (features_detailed[i+1] > thresholds[i]){
				features_binary[i] = 1; 
			} else {
				features_binary[i] = 0; 
			}
		}		
	}

	@Override
	public int getTrueClass() {
		// TODO Auto-generated method stub
		return 0;
	}
};
