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
			
//			crossTrain(wineList, 5, types.WINE);
//			crossTrain(miscList, 5, types.MISC);
			
			crossTrainDT(wineList, 5, types.WINE);
			crossTrainDT(miscList, 5, types.MISC);

			
		}

		private void crossTrain(ArrayList<Sample> list, int x, types t){ //x-fold cross validations
			
			Classifier[] sorters = new Classifier[x];  //one classifier for each training set
			ArrayList<Sample> trainingSet = new ArrayList<Sample>();
			ArrayList<Sample> testingSet  = new ArrayList<Sample>();
			
			double results =0, accuracy;
			Collections.shuffle(list);
			for (int i=0; i<x; i++){ //5-fold cross validation
				
				//set up sets
				trainingSet.clear();
				testingSet.clear();
				
				for (int s =0; s<list.size(); s++){ //add each element to training or testing set
					if (s%x == i) {
						testingSet.add(list.get(s));
					} else {
						trainingSet.add(list.get(s));
					}
				}
				
				//train
				sorters[i] = new Classifier(t);
				sorters[i].trainIndependant(trainingSet); 
				
				//test
				results += sorters[i].testSet(testingSet);
			}
			accuracy = results/x;
			System.out.println("Average of "+accuracy*100+"% accuracy for "+t);
		}
		
		private void crossTrainDT(ArrayList<Sample> list, int x, types t){ //x-fold cross validations
			
			DTClassifier[] sorters = new DTClassifier[x];  //one classifier for each training set
			ArrayList<Sample> trainingSet = new ArrayList<Sample>();
			ArrayList<Sample> testingSet  = new ArrayList<Sample>();
			
			double results =0, accuracy;
			Collections.shuffle(list);
			for (int i=0; i<x; i++){ //5-fold cross validation
				
				//set up sets
				trainingSet.clear();
				testingSet.clear();
				
				for (int s =0; s<list.size(); s++){ //add each element to training or testing set
					if (s%x == i) {
						testingSet.add(list.get(s));
					} else {
						trainingSet.add(list.get(s));
					}
				}
				
				//train
				sorters[i] = new DTClassifier(t);
				sorters[i].buildTree(trainingSet); 
				
				//test
				results += sorters[i].testSet(testingSet);
			}
			accuracy = results/x;
			System.out.println("Average of "+accuracy*100+"% accuracy for "+t);
		}
		
		private void printSet(ArrayList<Sample> set){
			for (int i=0; i<set.size(); i++){
				System.out.println(set.get(i));
			}
		}

};
