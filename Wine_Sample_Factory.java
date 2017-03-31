import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Wine_Sample_Factory {
	
	String file_name = "bin/wine_dataset.csv";
	String splitter  = ",";
	public static enum feature_names {Class, Alcohol, Malic_acid, Ash, Alcalinity, Magnesium, Penols, Flavanoids, Nonflavanoids, Proanthocyanins, Color, Hue, ODs, Proline};
	private static double[]   thresholds = {13, 2.34, 2.37, 19.49, 99.74, 2.3, 2.03, 0.36, 1.59, 5.06, 0.96, 2.61, 746.89};
		
	
	public ArrayList<Sample> getWineList(){ //reads from file wine_dataset and returns an arrayList of wine_sample objs
		ArrayList<Sample> wineList = new ArrayList<Sample>();
		BufferedReader reader;
		String line = "";
		String[] entry;
		
		try {
			reader = new BufferedReader(new FileReader(file_name));
			
			while ((line = reader.readLine()) != null){
				entry = line.split(splitter);
				wineList.add(makeSample(entry));
			}
			reader.close();
			
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
            e.printStackTrace();
		}
		
		return wineList;
	}
	
	private Sample makeSample(String[] data){ //helper*, takes string[] and turns it into double[] to make new samples
		Sample newWine;
		double[] wineData = new double[14];
		int[] binaryData = new int[14];
		
		for (int i =0; i<14; i++) {
			wineData[i] = Double.valueOf(data[i]);
		}
		binaryData[0] = (int) wineData[0];
		
		for (int i=1; i<14; i++){ //for each feature, if the value is greater than the threshold, set to true, else false
			if (wineData[i] > thresholds[i-1]){
				binaryData[i] = 1; 
			} else {
				binaryData[i] = 0; 
			}
		}
		newWine = new Sample(binaryData, 3);
		
		return newWine;
	}
	
	
};
