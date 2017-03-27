import java.util.ArrayList;
import java.util.Collections;

public class Controller {

		Wine_Sample_Factory wineFact = new Wine_Sample_Factory();
		
		
		public Controller(){
			ArrayList<Wine_Sample> wineList;
			wineList = wineFact.getWineList();
			
			crossTrain(wineList, 5);
		}

		private void crossTrain(ArrayList<Wine_Sample> wineList, int x){ //x-fold cross validations
			
			Wine_Classifier[] wineSorters 	   = new Wine_Classifier[x];  //one classifier for each training set
			ArrayList<Wine_Sample> trainingSet = new ArrayList<Wine_Sample>();
			ArrayList<Wine_Sample> testingSet  = new ArrayList<Wine_Sample>();
			
			double results =0, accuracy;
			Collections.shuffle(wineList);
			for (int i=0; i<x; i++){ //5-fold cross validation
				
				//set up sets
				trainingSet.clear();
				testingSet.clear();
				for (int s =0; s<wineList.size(); s++){ //add each element to training or testing set
					if (s%x == i) {
						testingSet.add(wineList.get(s));
					} else {
						trainingSet.add(wineList.get(s));
					}
				}
				
				//train
				wineSorters[i] = new Wine_Classifier();
				wineSorters[i].trainIndependant(trainingSet); 
				
				//test
				results += wineSorters[i].testSet(testingSet);
			}
			accuracy = results/x;
			System.out.println("Average of "+accuracy+"% accuracy");
		}
		
		private void printWineSet(ArrayList<Wine_Sample> set){
			for (int i=0; i<set.size(); i++){
				System.out.println(set.get(i));
			}
		}
};
