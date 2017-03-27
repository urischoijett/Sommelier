import java.util.ArrayList;

public class Wine_Sample extends Sample{
	public static enum classes {RED, ROSE, WHITE}; //maybe
	public static enum feature_names {Class, Alcohol, Malic_acid, Ash, Alcalinity, Magnesium, Penols, Flavanoids, Nonflavanoids, Proanthocyanins, Color, Hue, ODs, Proline};
	
	//the thresholds are the average value across all samples for each feature
	private static double[]   thresholds = {13, 2.34, 2.37, 19.49, 99.74, 2.3, 2.03, 0.36, 1.59, 5.06, 0.96, 2.61, 746.89};
	
	private double[]    features_detailed = new double[14];
	public  int[] 		features_binary = new int[13];
	//int true_class, sorted_class;
	
	public Wine_Sample(double[] new_features){
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
	public String toString(){
		String s="";
		
		s+="class: "+true_class;
		for (int i=0; i<features_binary.length; i++){
			s+=", f"+(i+1)+": "+features_binary[i];
		}
				
		return s;
	}
	
	@Override
	public int getTrueClass() {
		return true_class;
	}
	
	public int getFeatureVal(int n){
		return features_binary[n];
	}

};
