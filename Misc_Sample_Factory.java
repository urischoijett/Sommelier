import java.util.ArrayList;
import java.util.Random;

public class Misc_Sample_Factory {
	ArrayList<int[]> probSets = new ArrayList<int[]>();
	DepTree tree = new DepTree();
	
	public Misc_Sample_Factory(){
		
	}
	
	
	public ArrayList<Sample> getSampleList() { //returns a list of n samples
		ArrayList<Sample> sampleList = new ArrayList<Sample>();
		for (int i=0; i<2000; i++){ // make 2000 sample 
			for (int j=1; j<5; j++) { //of each class
				sampleList.add(makeSample(j));
			}
		}
		return sampleList;

	}
	
	private Sample makeSample(int class_num){ // helper for make sample list
		Sample newSample;
		int[] binary_features;
		
		binary_features = makeFeatures(class_num);
		newSample = new Sample(binary_features, 4);
		return newSample;
		}
	
	private int[] makeFeatures(int class_num){ //helper for make sample, makes a list of 1/0 feature values
		int[] features = new int[11];
		int p_id, pTrue;
		features[0] = class_num;
		double tempProb;
		Random rand = new Random();
		
		for (int i=0; i<10; i++){
			p_id = tree.getParentId(i);
			pTrue = (p_id==-1)? 1 : features[p_id];
			
			tempProb = tree.getProb(class_num, i, pTrue);
			
			if (rand.nextFloat() > tempProb) {
				features[i+1] = 0;
			} else {
				features[i+1] = 1;
			}
		}
		
		
		return features;
	}
};
