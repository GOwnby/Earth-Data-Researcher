import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Scanner;

import org.json.*;
import org.json.simple.JSONValue;

public class GlobalTemperature {
	
	private static String localFileString = "data/TemperatureData";
	// Credit: NASA
	// https://data.giss.nasa.gov/gistemp/graphs/graph_data/Global_Mean_Estimates_based_on_Land_and_Ocean_Data/graph.txt
	private static String fileURL = "https://data.giss.nasa.gov/gistemp/graphs/graph_data/Global_Mean_Estimates_based_on_Land_and_Ocean_Data/graph.txt";
	private static Scanner scanTemp;
	private static int year = Calendar.getInstance().get(Calendar.YEAR);
	
	public static double getFutureRateOfChange (String yearDate) throws MalformedURLException, IOException
	{
	
		
		return 0.0;
	}
	
	public static double getFutureRateOfChange (int yearDate)
	{
		
		
		return 0.0;
	}
	
	public static double getAvgRateOfChangeOfRateOfChange (String yearOne, String yearTwo)
	{
		
		
		return 0.0;
	}
	
	public static double getAvgRateOfChangeOfRateOfChange (int yearOne, int yearTwo)
	{
		
		
		return 0.0;
	}
	
	public static double getAvgRateOfChangeOfRateOfChange (double rateOfChange, String yearOne, String yearTwo)
	{
		
		
		return 0.0;
	}
	
	public static double getAvgRateOfChangeOfRateOfChange (double rateOfChange, int yearOne, int yearTwo)
	{
		
		
		return 0.0;
	}
	
	public static String getLatestDate ()
	{
		
		
		return "";
	}
	// Load the file
	public static boolean isLoaded () throws MalformedURLException, IOException
	{
		File globalTemp = null;
		Scanner scanGlobalTemp = null;
		try 
		{
			globalTemp = new File(localFileString);
			scanGlobalTemp = new Scanner(globalTemp);
		} 
		catch (FileNotFoundException e)
		{
			 BufferedInputStream in = null;
			 FileOutputStream fout = null;
			 try 
			 {
				 in = new BufferedInputStream(new URL(fileURL).openStream());
				 fout = new FileOutputStream(localFileString);
			 
			 	 byte data[] = new byte[1024];
			 	 int count;
			 while ( (count = in.read(data, 0, 1024) ) != -1) 
			 {
				 fout.write(data, 0, count);
			 }
			 } 
			 finally
			 {
				 if (in != null) in.close();
				 if (fout != null) fout.close();
			 }
			 
				try 
				{
					globalTemp = new File(localFileString);
					scanGlobalTemp = new Scanner(globalTemp);
				} 
				catch (FileNotFoundException f)
				{
					f.printStackTrace();
					System.out.println("Bad Request. Check connection and data source.");
					return false;
				}
		}
		
		scanGlobalTemp.close();
		return true;
	}
	
	// Finds the average rate of change of temperature due to the rate of change of CO2 since data was first recorded
	public static double getRateOfChangeFromCarbonDioxide () throws MalformedURLException, IOException
	{
		double CO2In1979 = CarbonDioxide.getAvgPPM(1979);
		double CO2In1980 = CarbonDioxide.getAvgPPM(1980);
		double CO2In1981 = CarbonDioxide.getAvgPPM(1981);
		
		double tempIndexIn1979 = getLandOceanTemperatureIndex(1979);
		double tempIndexIn1980 = getLandOceanTemperatureIndex(1980);
		double tempIndexIn1981 = getLandOceanTemperatureIndex(1981);
		
		double CO2CurrentMinus2 = CarbonDioxide.getAvgPPM(year - 2);
		double CO2CurrentMinus3 = CarbonDioxide.getAvgPPM(year - 3);
		double CO2CurrentMinus4 = CarbonDioxide.getAvgPPM(year - 4);
		
		double tempIndexCurrentMinus2 = getLandOceanTemperatureIndex(year - 2);
		double tempIndexCurrentMinus3 = getLandOceanTemperatureIndex(year - 3);
		double tempIndexCurrentMinus4 = getLandOceanTemperatureIndex(year - 4);
		
		// Degree Celsius change per CO2 PPM
		// The temperature changes by X for every CO2 PPM added on average
		// Finds the average temperature change from 1958 to current and divides it by the average CO2 change from 1958 to current
		return ( ( ((tempIndexCurrentMinus2 + tempIndexCurrentMinus3 + tempIndexCurrentMinus4) / 3.0) 
				- ((tempIndexIn1979 + tempIndexIn1980 + tempIndexIn1981) / 3.0) ) 
				/ ( ((CO2CurrentMinus2 + CO2CurrentMinus3 + CO2CurrentMinus4) / 3.0)
				- ((CO2In1979 + CO2In1980 + CO2In1981) / 3.0)) );
	}
	// Find the land ocean temperature index given a year in data
	public static double getLandOceanTemperatureIndex (String yearDate) throws MalformedURLException, IOException
	{
		File temperatureFile = new File(localFileString);
		try {
			scanTemp = new Scanner(temperatureFile);
		} catch (FileNotFoundException file0) {
			file0.printStackTrace();
			if(isLoaded() == true) { scanTemp = new Scanner(temperatureFile); }
			else { return -1.0; }
		}
		while (scanTemp.hasNextLine())
		{
			if (scanTemp.next().equals(yearDate))
			{
				return Double.parseDouble(scanTemp.next());
			}
		}
		
		return 0.0;
	}
	// Find the land ocean temperature index given a year in data
	public static double getLandOceanTemperatureIndex (int yearDate) throws MalformedURLException, IOException
	{
		File temperatureFile = new File(localFileString);
		try {
			scanTemp = new Scanner(temperatureFile);
		} catch (FileNotFoundException file0) {
			file0.printStackTrace();
			if(isLoaded() == true) { scanTemp = new Scanner(temperatureFile); }
			else { return -1.0; }
		}
		while (scanTemp.hasNextLine())
		{
			if (scanTemp.next().equals(Integer.toString(yearDate)))
			{
				return Double.parseDouble(scanTemp.next());
			}
		}
		
		return 0.0;
	}
	// Find the future land ocean temperature index based on the projected change due to CO2
	public static double getFutureLandOceanTemperatureIndex (String yearDate) throws MalformedURLException, IOException
	{
		
		double projectedCO2YearDate = CarbonDioxide.getFuturePPM(Integer.parseInt(yearDate), year - 2, year - 1); //Projected CO2 level using rateofchange in past year
		double currentCO2 = CarbonDioxide.getLatestPPM();
		double changeInCO2 = projectedCO2YearDate - currentCO2;
		
		return getLatestLandOceanTemperatureIndex() + (changeInCO2 * getRateOfChangeFromCarbonDioxide());
	}
	// Find the future land ocean temperature index based on the projected change due to CO2
	public static double getFutureLandOceanTemperatureIndex (int yearDate) throws MalformedURLException, IOException
	{
		double projectedCO2YearDate = CarbonDioxide.getFuturePPM(yearDate, year - 2, year - 1); //Projected CO2 level using rateofchange in past year
		double currentCO2 = CarbonDioxide.getLatestPPM();
		double changeInCO2 = projectedCO2YearDate - currentCO2;
		
		return getLatestLandOceanTemperatureIndex() + (changeInCO2 * getRateOfChangeFromCarbonDioxide());
	}
	// Find the latest land ocean temperature index in data
	public static double getLatestLandOceanTemperatureIndex () throws MalformedURLException, IOException
	{
		File temperatureFile = new File(localFileString);
		try {
			scanTemp = new Scanner(temperatureFile);
		} catch (FileNotFoundException file0) {
			file0.printStackTrace();
			if(isLoaded()) { scanTemp = new Scanner(temperatureFile); }
			else { return -1.0; }
		}
		
		int countLines = 0;
		while (scanTemp.hasNextLine())
		{
			scanTemp.nextLine();
			++countLines;
		}
		
		scanTemp.reset();
		scanTemp = new Scanner(temperatureFile);
		
		for (int i = 0; i < countLines - 1; ++i)
		{
			scanTemp.nextLine();
		}
		
		scanTemp.next();
		return Double.parseDouble(scanTemp.next());
	}
	// Find the latest full year of the land ocean temperature index in data
	public static int getLatestYearInData () throws MalformedURLException, IOException
	{
		File temperatureFile = new File(localFileString);
		try {
			scanTemp = new Scanner(temperatureFile);
		} catch (FileNotFoundException file0) {
			file0.printStackTrace();
			if(isLoaded()) { scanTemp = new Scanner(temperatureFile); }
			else { return -1; }
		}
		
		int countLines = 0;
		while (scanTemp.hasNextLine())
		{
			scanTemp.nextLine();
			++countLines;
		}
		
		scanTemp.reset();
		scanTemp = new Scanner(temperatureFile);
		
		for (int i = 0; i < countLines - 1; ++i)
		{
			scanTemp.nextLine();
		}
		
		return Integer.parseInt(scanTemp.next());
	}
	// Find the lowess land ocean temperature index given a year in data
	public static double getLowessLandOceanTemperatureIndex (String yearDate) throws MalformedURLException, IOException
	{
		File temperatureFile = new File(localFileString);
		try {
			scanTemp = new Scanner(temperatureFile);
		} catch (FileNotFoundException file0) {
			file0.printStackTrace();
			if(isLoaded() == true) { scanTemp = new Scanner(temperatureFile); }
			else { return -1.0; }
		}
		while (scanTemp.hasNextLine())
		{
			if (scanTemp.next().equals(yearDate))
			{
				scanTemp.next();
				return Double.parseDouble(scanTemp.next());
			}
		}
		
		return 0.0;
	}
	// Find the lowess land ocean temperature index given a year in data
	public static double getLowessLandOceanTemperatureIndex (int yearDate) throws MalformedURLException, IOException
	{
		File temperatureFile = new File(localFileString);
		try {
			scanTemp = new Scanner(temperatureFile);
		} catch (FileNotFoundException file0) {
			file0.printStackTrace();
			if(isLoaded() == true) { scanTemp = new Scanner(temperatureFile); }
			else { return -1.0; }
		}
		while (scanTemp.hasNextLine())
		{
			if (scanTemp.next().equals(Integer.toString(yearDate)))
			{
				scanTemp.next();
				return Double.parseDouble(scanTemp.next());
			}
		}
		
		return 0.0;
	}
	
	public static double getLowessLandOceanTemperatureIndex (String yearDate, double rateOfChange)
	{
		
		
		return 0.0;
	}
	
	public static double getLowessLandOceanTemperatureIndex (int yearDate, double rateOfChange)
	{
		
		
		return 0.0;
	}
	
	public static double getLowessLandOceanTemperatureIndex (String yearDate, String yearOne, String yearTwo)
	{
		
		
		return 0.0;
	}
	
	public static double getLowessLandOceanTemperatureIndex (int yearDate, int yearOne, int yearTwo)
	{
		
		
		return 0.0;
	}
	
	public static double getLatestLowessLandOceanTemperatureIndex ()
	{
		
		return 0.0;
	}
	// Generate a JSON file of past CO2 values to be used by the javascript application
	public static void generateJSONFilePastValues () throws MalformedURLException, JSONException, IOException
	{
		JSONObject pastData = new org.json.JSONObject();
		
		for (int i = 1880; i < year ; i = i + 4)
		{
			String intI = Integer.toString(i);
			
			pastData.put(intI, getLandOceanTemperatureIndex(intI) );
		}
		
		File file = new File("Site/pastGlobalTempData.json");
		file.createNewFile();
		FileWriter fileWriter = new FileWriter(file);
	 	JSONValue.writeJSONString(pastData, fileWriter);
		fileWriter.close();
	}
	// Generate a JSON file of past CO2 values to be used by the javascript application
	public static void generateJSONFileCurrentValue () throws MalformedURLException, JSONException, IOException
	{
		int latestYear = getLatestYearInData();
		
		String yearString = Integer.toString(latestYear);
		JSONObject currentData = new org.json.JSONObject();
		
		currentData.put(yearString, getLatestLandOceanTemperatureIndex());
		
		File file = new File("Site/currentGlobalTempData.json");
		file.createNewFile();
		FileWriter fileWriter = new FileWriter(file);
	 	JSONValue.writeJSONString(currentData, fileWriter);
		fileWriter.close();
	}
	// Generate a JSON file of past CO2 values to be used by the javascript application
	public static void generateJSONFileFutureValues () throws MalformedURLException, IOException, JSONException
	{
		int latestYear = getLatestYearInData();
		JSONObject futureData = new org.json.JSONObject();
		
		for (int i = latestYear + 4; i < 2070 ; i = i + 4)
		{
			String intI = Integer.toString(i);
			
			futureData.put(intI, getFutureLandOceanTemperatureIndex(intI) );
		}
		
		File file = new File("Site/futureGlobalTempData.json");
		file.createNewFile();
		FileWriter fileWriter = new FileWriter(file);
	 	JSONValue.writeJSONString(futureData, fileWriter);
		fileWriter.close();
	}
}
