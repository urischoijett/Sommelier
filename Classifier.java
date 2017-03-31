import java.util.ArrayList;
import java.util.Arrays;

public  class Classifier {
	double[][] probTrues;
	int numC;
	int numF;
	boolean trained;
	
	public Classifier(Controller.types t){
		if(t==Controller.types.WINE){
			numC = 3;
			numF = 13;
		} else {
			numC = 4;
			numF = 10;
		}
	}
	public void trainIndependant(ArrayList<Sample> trainers) {	

		
		//probTrues[class][feature]
		probTrues = new double[numC][numF];
		double[] classCount = new double[numC];
		double[] classSum = new double[numC];
		
//		double[] c1_probTrues = new double[trainers.size()];
//		double[] c2_probTrues = new double[trainers.size()];
//		double[] c3_probTrues = new double[trainers.size()];
//		
//		double c1Sum = 0, c1Count = 0;
//		double c2Sum = 0, c2Count = 0;
//		double c3Sum = 0, c3Count = 0;
		
		for (int samp=0; samp<trainers.size(); samp++){
			for (int c=0; c<numC; c++){
				classCount[trainers.get(samp).getTrueClass()-1]++; //count the # of sample of each class
			}	
		}
		
		for (int f=0; f<numF; f++){ //for each feature
			for (int j=0; j<trainers.size(); j++){ //for each test sample
				classSum[trainers.get(j).getTrueClass()-1] += trainers.get(j).getFeature(f);
			}
			
			for (int c=0; c<numC; c++){
				probTrues[c][f] = classSum[c]/classCount[c];
				classSum[c] = 0;
			}
		}
				
				
				
//				if (trainers.get(j).getTrueClass() == 1){ 
//					c1Sum += trainers.get(j).getFeature(i);
//					
//				} else if (trainers.get(j).getTrueClass() == 2){
//					c2Sum += trainers.get(j).features_binary[i];
//					
//				} else if (trainers.get(j).getTrueClass() == 3){
//					c3Sum += trainers.get(j).features_binary[i];
//				}						
//			c1_probTrues[i] = c1Sum/c1Count;
//			c2_probTrues[i] = c2Sum/c2Count;
//			c3_probTrues[i] = c3Sum/c3Count;
//			c1Sum = 0;
//			c2Sum = 0;
//			c3Sum = 0;
//			System.out.println("feature "+i+", c1: "+c1_probTrues[i]+", c2: "+c2_probTrues[i]+", c3: "+c3_probTrues[i]);

		trained = true;
	}
	
	public double testSet(ArrayList<Sample> testList){ //tests each sample, returns accuracy
		double accuracy, results =0;
		
		for (int i=0; i<testList.size(); i++){
			results += testSample(testList.get(i));
		}		
		
		accuracy = results / testList.size();
		System.out.println(accuracy*100+"% accuracy");
		return accuracy;
	}
	
	private int testSample(Sample s){ //classifies a sample, returns 1 if sorted correctly, else 0 (-1 if err)
		double[] probC = new double[numC];
		Arrays.fill(probC, 1);
		
		if (!trained){
			System.out.println("untrained classifier");
		}
		
		for (int c=0; c<numC; c++){
			for (int f=0; f<numF; f++){
				if (s.getFeature(f) == 1){
					probC[c] *= probTrues[c][f];
				} else {
					probC[c] *= (1 - probTrues[c][f]);
				}
			}	
		}
		
		int maxIndex =0;
		for (int i=0; i<numC; i++){
			if(probC[i] > probC[maxIndex]){
				maxIndex = i;
			}
		}
		s.sort(maxIndex+1);
		
//		System.out.println("prob c1 = "+prob_c1+", prob c2 = "+prob_c2+ ", prob c3 = "+prob_c3+"    classified as "+s.sorted_class);
		
		return (s.getSortedClass()==s.getTrueClass())?1:0; 
	}
};
