import java.util.ArrayList;
import java.util.Collections;

public class Controller {
		public static enum types {WINE, MISC};
		
		Wine_Sample_Factory wineFact = new Wine_Sample_Factory();
		Misc_Sample_Factory miscFact = new Misc_Sample_Factory();
		
		public Controller(){
			ArrayList<Sample> wineList;
			ArrayList<Sample> miscList;
			
			wineList = wineFact.getWineList();
			miscList = miscFact.getSampleList();
			
//			printSet(wineList);
//			printSet(miscList);
			
			crossTrain(wineList, 5);
			
			
			
		}

		private void crossTrain(ArrayList<Sample> wineList, int x){ //x-fold cross validations
			
			Classifier[] wineSorters = new Classifier[x];  //one classifier for each training set
			ArrayList<Sample> trainingSet = new ArrayList<Sample>();
			ArrayList<Sample> testingSet  = new ArrayList<Sample>();
			
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
				wineSorters[i] = new Classifier(types.WINE);
				wineSorters[i].trainIndependant(trainingSet); 
				
				//test
				results += wineSorters[i].testSet(testingSet);
			}
			accuracy = results/x;
			System.out.println("Average of "+accuracy*100+"% accuracy");
		}
		
		private void printSet(ArrayList<Sample> set){
			for (int i=0; i<set.size(); i++){
				System.out.println(set.get(i));
			}
		}

};
