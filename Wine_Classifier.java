import java.util.ArrayList;

public class Wine_Classifier { //extends classifier
	
	private double[] c1_probTrues;
	private double[] c2_probTrues;
	private double[] c3_probTrues;
	private boolean trained = false;
	
	public Wine_Classifier(){
		c1_probTrues = new double[13];
		c2_probTrues = new double[13];
		c3_probTrues = new double[13];
	}
	
	public void trainIndependant(ArrayList<Wine_Sample> trainers) { 
		int c1Sum = 0, c1Count = 0;
		int c2Sum = 0, c2Count = 0;
		int c3Sum = 0, c3Count = 0;
		
		for (int i=0; i<13; i++){ //for each feature
			for (int j=0; j<trainers.size(); j++){ //for each test sample
				if (trainers.get(j).getTrueClass() == 1){ 
					c1Count++;
					c1Sum += trainers.get(i).getFeatureVal(i);
					
				} else if (trainers.get(j).getTrueClass() == 2){
					c2Count++;
					c2Sum += trainers.get(i).getFeatureVal(i);
					
				} else if (trainers.get(j).getTrueClass() == 3){
					c3Count++;
					c3Sum += trainers.get(i).getFeatureVal(i);
				}			
			}
			
			c1_probTrues[i] = c1Sum/c1Count;
			c2_probTrues[i] = c2Sum/c2Count;
			c3_probTrues[i] = c3Sum/c3Count;
			c1Sum = 0;
			c2Sum = 0;
			c3Sum = 0;
			
		}
		trained = true;
	}
	
	public void printProbabilities(){
		
	}
	
	public double testSet(ArrayList<Wine_Sample> wineList){ //tests each sample, returns accuracy
		double accuracy, results =0;
		
		for (int i=0; i<wineList.size(); i++){
			results += testSample(wineList.get(i));
		}		
		
		accuracy = results / wineList.size();
		System.out.println(accuracy+"% accuracy");
		return accuracy;
	}
	
	
	private int testSample(Wine_Sample s){ //classifies a sample, returns 1 if sorted correctly, else 0 (-1 if err)
		float prob_c1=1 , prob_c2=1 , prob_c3=1;
		
		if (!trained){
			System.out.println("untrained classifier");
		}
		//prob c1
		for (int f=0; f<13; f++){
			if (s.getFeatureVal(f) == 1){
				prob_c1 *= c1_probTrues[f];
			} else {
				prob_c1 *= 1-c1_probTrues[f];
			}
		}
		
		//prob c2
		for (int f=0; f<13; f++){
			if (s.getFeatureVal(f) == 1){
				prob_c2 *= c2_probTrues[f];
			} else {
				prob_c2 *= 1-c2_probTrues[f];
			}
		}
		
		//prob c3
		for (int f=0; f<13; f++){
			if (s.getFeatureVal(f) == 1){
				prob_c3 *= c3_probTrues[f];
			} else {
				prob_c3 *= 1-c3_probTrues[f];
			}
		}
		System.out.println("prob c1 = "+prob_c1+", prob c2 = "+prob_c2+ ", prob c3 = "+prob_c3);
		//set class to max (c1, c2, c3)
		if (prob_c1 > prob_c2 && prob_c1 > prob_c3){
			s.sorted_class=1;
		} else if (prob_c2 > prob_c1 && prob_c2 > prob_c3) {
			s.sorted_class=2;
		} else {
			s.sorted_class=3;
		}
		System.out.println("classified as "+s.sorted_class);
		
		return s.sorted_class==s.true_class?1:0; 
	}
	
	

};