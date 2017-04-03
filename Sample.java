
public class Sample {
	
	private int true_class;
	private int sorted_class;
	private int num_features, num_classes;
	private int[] features_binary;
	
	public Sample(int[] new_features, int nc){
		features_binary = new int[new_features.length-1];
		num_features = new_features.length-1;
		num_classes = nc;
		
		//assign class and features
		true_class = new_features[0];
		sorted_class = 0; //unsorted
		for (int i=0; i<new_features.length-1; i++){ 
			features_binary[i] = new_features[i+1];
		}		
	}
	public void sort(int c){ // sort into class n
		sorted_class = c;
	}
	public int getFeature(int n) {
		return features_binary[n];
	}
	public int getNumClases(){
		return num_classes;
	}
	public int getNumFeatures(){
		return num_features;
	}
	public int getTrueClass(){
		return true_class;
	}
	public int getSortedClass(){
		return sorted_class;
	}
	public String toString(){
		String s="";
		
		s+="class: "+true_class;
		for (int i=0; i<features_binary.length; i++){
			s+=", f"+(i+1)+": "+features_binary[i];
		}
				
		return s;
	}

};
