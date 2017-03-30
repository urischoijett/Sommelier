
public class Misc_Sample extends Sample{
	
	public  int[] features_binary = new int[10];
	
	
	public Misc_Sample(int[] new_features){
		true_class = new_features[0];	
		for (int i=0; i<10; i++){ //for each feature, if the value is greater than the threshold, set to true, else false
			features_binary[i] = new_features[i+1];
		}		
	}

	@Override
	public int getTrueClass() {
		return true_class;
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
