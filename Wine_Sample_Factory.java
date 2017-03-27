import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Wine_Sample_Factory {
	
	String file_name = "bin/wine_dataset.csv";
	String splitter  = ",";
	
	public Wine_Sample_Factory(){
		
	}
	
	
	public ArrayList<Wine_Sample> getWineList(){ //reads from file wine_dataset and returns an arrayList of wine_sample objs
		ArrayList<Wine_Sample> wineList = new ArrayList<Wine_Sample>();
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
	
	private Wine_Sample makeSample(String[] data){ //helper*, takes string[] and turns it into double[] to make new samples
		Wine_Sample newWine;
		double[] wineData = new double[14];
		
		for (int i =0; i<14; i++) {
			wineData[i] = Double.valueOf(data[i]);
		}
		newWine = new Wine_Sample(wineData);
		
		return newWine;
	}
	
	
};
