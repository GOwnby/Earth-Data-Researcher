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

public class ArcticIce {
		
	private static String localFileString1 = "data/ArcticData1";
	// Credit: NASA
	// https://climate.nasa.gov/system/internal_resources/details/original/1270_minimum_extents_and_area_north_SBA_reg_20171001_2_.txt
	private static String fileURL1 = "https://climate.nasa.gov/system/internal_resources/details/original/1270_minimum_extents_and_area_north_SBA_reg_20171001_2_.txt";
	private static String localFileString2 = "data/ArcticData2";
	// Credit: Colorado University
	// ftp://sidads.colorado.edu/pub/DATASETS/NOAA/G10006/sea_ice_CDR_data_set_overview_v1.1.txt
	private static String fileURL2 = "ftp://sidads.colorado.edu/pub/DATASETS/NOAA/G10006/sea_ice_CDR_data_set_overview_v1.1.txt";
	private static int year = Calendar.getInstance().get(Calendar.YEAR);
	
	// Find the rate of change of the arctic ice extent due to a rate of change in global temperature
	public static double getRateOfChangeOfExtentMinimumFromGlobalTemperature () throws MalformedURLException, IOException
	{

		double globalTempIn1979 = GlobalTemperature.getLandOceanTemperatureIndex(1979);
		double globalTempIn1980 = GlobalTemperature.getLandOceanTemperatureIndex(1980);
		double globalTempIn1981	= GlobalTemperature.getLandOceanTemperatureIndex(1981);
		
		double extentMinimumIn1979 = getExtentMinimum(1979);
		double extentMinimumIn1980 = getExtentMinimum(1980);
		double extentMinimumIn1981 = getExtentMinimum(1981);
		
		double globalTempCurrentMinus2 = GlobalTemperature.getLandOceanTemperatureIndex(year - 2);
		double globalTempCurrentMinus3 = GlobalTemperature.getLandOceanTemperatureIndex(year - 3);
		double globalTempCurrentMinus4 = GlobalTemperature.getLandOceanTemperatureIndex(year - 4);
		
		double extentMinimumCurrentMinus2 = getExtentMinimum(year - 2);
		double extentMinimumCurrentMinus3 = getExtentMinimum(year - 3);
		double extentMinimumCurrentMinus4 = getExtentMinimum(year - 4);
		
		
		// Square kilometer change per degree celsius increase
		// The arctic ice extent decreases by X for every celsius increase on average
		// Finds the average arctic ice extent change from 1979 to current and divides it by the average global temperature increase from 1979 to current
		return ( ( ((extentMinimumCurrentMinus2 + extentMinimumCurrentMinus3 + extentMinimumCurrentMinus4) / 3.0) 
				- ((extentMinimumIn1979 + extentMinimumIn1980 + extentMinimumIn1981) / 3.0) ) 
				/ ( ((globalTempCurrentMinus2 + globalTempCurrentMinus3 + globalTempCurrentMinus4) / 3.0)
				- ((globalTempIn1979 + globalTempIn1980 + globalTempIn1981) / 3.0)) );
	}
	
	public static double getRateOfChangeOfAreaMinimumFromGlobalTemperature () throws MalformedURLException, IOException
	{
		double globalTempIn1979 = GlobalTemperature.getLandOceanTemperatureIndex(1979);
		double globalTempIn1980 = GlobalTemperature.getLandOceanTemperatureIndex(1980);
		double globalTempIn1981	= GlobalTemperature.getLandOceanTemperatureIndex(1981);
		
		double areaMinimumIn1979 = getAreaMinimum(1979);
		double areaMinimumIn1980 = getAreaMinimum(1980);
		double areaMinimumIn1981 = getAreaMinimum(1981);
		
		double globalTempCurrentMinus2 = GlobalTemperature.getLandOceanTemperatureIndex(year - 2);
		double globalTempCurrentMinus3 = GlobalTemperature.getLandOceanTemperatureIndex(year - 3);
		double globalTempCurrentMinus4 = GlobalTemperature.getLandOceanTemperatureIndex(year - 4);
		
		double areaMinimumCurrentMinus2 = getExtentMinimum(year - 2);
		double areaMinimumCurrentMinus3 = getExtentMinimum(year - 3);
		double areaMinimumCurrentMinus4 = getExtentMinimum(year - 4);
		
		
		// Square kilometer change per degree celsius increase
		// The arctic ice area decreases by X for every celsius increase on average
		// Finds the average arctic ice area change from 1979 to current and divides it by the average global temperature increase from 1979 to current
		return ( ( ((areaMinimumCurrentMinus2 + areaMinimumCurrentMinus3 + areaMinimumCurrentMinus4) / 3.0) 
				- ((areaMinimumIn1979 + areaMinimumIn1980 + areaMinimumIn1981) / 3.0) ) 
				/ ( ((globalTempCurrentMinus2 + globalTempCurrentMinus3 + globalTempCurrentMinus4) / 3.0)
				- ((globalTempIn1979 + globalTempIn1980 + globalTempIn1981) / 3.0)) );
	}

	// Load the file
	public static boolean isLoaded () throws MalformedURLException, IOException
	{
		File arctic = null;
		Scanner scanArctic = null;
		try 
		{
			arctic = new File(localFileString1);
			scanArctic = new Scanner(arctic);
		} 
		catch (FileNotFoundException e)
		{
			 BufferedInputStream in = null;
			 FileOutputStream fout = null;
			 try 
			 {
				 in = new BufferedInputStream(new URL(fileURL1).openStream());
				 fout = new FileOutputStream(localFileString1);
			 
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
					arctic = new File(localFileString1);
					scanArctic = new Scanner(arctic);
				} 
				catch (FileNotFoundException f)
				{
					f.printStackTrace();
					System.out.println("Bad Request. Check connection and data source.");
					return false;
				}
		}
		
		try 
		{
			arctic = new File(localFileString2);
			scanArctic = new Scanner(arctic);
		} 
		catch (FileNotFoundException e)
		{
			 BufferedInputStream in = null;
			 FileOutputStream fout = null;
			 try 
			 {
				 in = new BufferedInputStream(new URL(fileURL2).openStream());
				 fout = new FileOutputStream(localFileString2);
			 
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
					arctic = new File(localFileString2);
					scanArctic = new Scanner(arctic);
				} 
				catch (FileNotFoundException f)
				{
					f.printStackTrace();
					System.out.println("Bad Request. Check connection and data source.");
					return false;
				}
		}
		
		scanArctic.close();
		return true;
	}
	// Find the minimum extent of arctic ice given a year in data
	public static double getExtentMinimum (String yearDate) throws MalformedURLException, IOException
	{
		Scanner scnr;
		scnr = null;
		
		File thisFile = new File(localFileString1);
		try {
			scnr = new Scanner(thisFile);
		} catch (FileNotFoundException file0) {
			file0.printStackTrace();
			if(isLoaded() == true) { scnr = new Scanner(thisFile); }
			else { return 0.0; } // Could not parse file
		}
		
		double suspectedValue = 0.0;
		double actualValue = 0.0;
		int counter = 0;
		while (scnr.hasNext()) {
			
			if (scnr.next().contains(yearDate)) {
				scnr.next(); scnr.next();
				suspectedValue = Double.parseDouble(scnr.next());		
					
				if (scnr.next().contains(yearDate)) {
					actualValue = suspectedValue;
				} else {
					scnr.reset();
					for (int i = 0; i < counter - 1; ++i)
					{
						scnr.next();
					}
					actualValue = Double.parseDouble(scnr.next());
				}
			}
			
			
			++counter;
		}
			
		
		scnr.close();
		return actualValue;
	}
	// Find the minimum extent of arctic ice given a year in data
	public static double getExtentMinimum (int yearDate) throws MalformedURLException, IOException
	{
		String yearDateString = Integer.toString(yearDate);
		Scanner scnr;
		scnr = null;
		
		File thisFile = new File(localFileString1);
		try {
			scnr = new Scanner(thisFile);
		} catch (FileNotFoundException file0) {
			file0.printStackTrace();
			if(isLoaded() == true) { scnr = new Scanner(thisFile); }
			else { return -1.0; } // Could not parse file
		}
		
		double suspectedValue = 0.0;
		double actualValue = 0.0;
		int counter = 0;
		while (scnr.hasNext()) {
			
			if (scnr.next().contains(yearDateString)) {
				scnr.next(); scnr.next();
				suspectedValue = Double.parseDouble(scnr.next());		
					
				if (scnr.next().contains(yearDateString)) {
					actualValue = suspectedValue;
				} else {
					scnr.reset();
					for (int i = 0; i < counter - 1; ++i)
					{
						scnr.next();
					}
					actualValue = Double.parseDouble(scnr.next());
				}
			}
			
			
			++counter;
		}
			
		
		scnr.close();
		return actualValue;
	}
	// Find the minimum area of arctic ice given a year in data
	public static double getAreaMinimum (String yearDate) throws MalformedURLException, FileNotFoundException, IOException
	{
		Scanner scnr;
		scnr = null;
		
		File thisFile = new File(localFileString1);
		try {
			scnr = new Scanner(thisFile);
		} catch (FileNotFoundException file0) {
			file0.printStackTrace();
			if(isLoaded() == true) { scnr = new Scanner(thisFile); }
			else { return 0.0; } // Could not parse file
		}
		
		while (scnr.hasNext())
		{
			if (scnr.next().contains(yearDate))
			{
				scnr.next();scnr.next();scnr.next();scnr.next();scnr.next();scnr.next();
				double value = Double.parseDouble(scnr.next());
				scnr.close();
				return value;
			} else {
				scnr.next();
			}
		}
		scnr.close();
		return -1.0;
	}
	// Find the minimum area of arctic ice given a year in data
	public static double getAreaMinimum (int yearDate) throws MalformedURLException, IOException
	{
		String yearDateString = Integer.toString(yearDate);
		Scanner scnr;
		scnr = null;
		
		File thisFile = new File(localFileString1);
		try {
			scnr = new Scanner(thisFile);
		} catch (FileNotFoundException file0) {
			file0.printStackTrace();
			if(isLoaded() == true) { scnr = new Scanner(thisFile); }
			else { return 0.0; } // Could not parse file
		}
		
		while (scnr.hasNext())
		{
			if (scnr.next().contains(yearDateString))
			{
				scnr.next();scnr.next();scnr.next();scnr.next();scnr.next();scnr.next();
				double value = Double.parseDouble(scnr.next());
				scnr.close();
				return value;
			} else {
				scnr.next();
			}
		}
		scnr.close();
		return -1.0;
	}
	// Find the arctic ice extent minimum at a year in the future due to a projected change in temperature
	public static double getFutureExtentMinimum (String yearDate) throws NumberFormatException, MalformedURLException, IOException
	{
		double projectedGlobalTempYearDate = GlobalTemperature.getFutureLandOceanTemperatureIndex(Integer.parseInt(yearDate)); 
		double currentGlobalTemp = GlobalTemperature.getLatestLandOceanTemperatureIndex();
		double changeInGlobalTemp = projectedGlobalTempYearDate - currentGlobalTemp;
		
		return getLatestExtentMinimum() + (changeInGlobalTemp * getRateOfChangeOfExtentMinimumFromGlobalTemperature());
	}
	// Find the arctic ice extent minimum at a year in the future due to a projected change in temperature
	public static double getFutureExtentMinimum (int yearDate) throws MalformedURLException, IOException
	{
		double projectedGlobalTempYearDate = GlobalTemperature.getFutureLandOceanTemperatureIndex(yearDate); 
		double currentGlobalTemp = GlobalTemperature.getLatestLandOceanTemperatureIndex();
		double changeInGlobalTemp = projectedGlobalTempYearDate - currentGlobalTemp;
		
		return getLatestExtentMinimum() + (changeInGlobalTemp * getRateOfChangeOfExtentMinimumFromGlobalTemperature());
	}

	// Find the arctic ice area minimum at a year in the future due to a projected change in temperature
	public static double getFutureAreaMinimum (String yearDate) throws NumberFormatException, MalformedURLException, IOException
	{
		double projectedGlobalTempYearDate = GlobalTemperature.getFutureLandOceanTemperatureIndex(Integer.parseInt(yearDate)); 
		double currentGlobalTemp = GlobalTemperature.getLatestLandOceanTemperatureIndex();
		double changeInGlobalTemp = projectedGlobalTempYearDate - currentGlobalTemp;
		
		return getLatestAreaMinimum() + (changeInGlobalTemp * getRateOfChangeOfAreaMinimumFromGlobalTemperature());
	}
	// Find the arctic ice area minimum at a year in the future due to a projected change in temperature
	public static double getFutureAreaMinimum (int yearDate) throws MalformedURLException, IOException
	{
		double projectedGlobalTempYearDate = GlobalTemperature.getFutureLandOceanTemperatureIndex(yearDate); 
		double currentGlobalTemp = GlobalTemperature.getLatestLandOceanTemperatureIndex();
		double changeInGlobalTemp = projectedGlobalTempYearDate - currentGlobalTemp;
		
		return getLatestAreaMinimum() + (changeInGlobalTemp * getRateOfChangeOfAreaMinimumFromGlobalTemperature());
	}
	// Find the latest arctic ice extent minimum in data
	public static double getLatestExtentMinimum () throws MalformedURLException, FileNotFoundException, IOException
	{
		Scanner scnr;
		scnr = null;
		
		File thisFile = new File(localFileString1);
		try {
			scnr = new Scanner(thisFile);
		} catch (FileNotFoundException file0) {
			file0.printStackTrace();
			if(isLoaded() == true) { scnr = new Scanner(thisFile); }
			else { return -1.0; } // Could not parse file
		}
		
		int countLines = 0;
		while (scnr.hasNextLine())
		{
			scnr.nextLine();
			++countLines;
		}
		
		scnr.reset();
		scnr = new Scanner(thisFile);
		
		for (int i = 0; i < countLines - 1; ++i)
		{
			scnr.nextLine();
		}
		
		scnr.next();scnr.next();scnr.next();
		double value = Double.parseDouble(scnr.next());
		scnr.close();
		return value;
		
	}
	// Find the latest arctic ice area minimum in data
	public static double getLatestAreaMinimum () throws MalformedURLException, IOException
	{
		Scanner scnr;
		scnr = null;
		
		File thisFile = new File(localFileString1);
		try {
			scnr = new Scanner(thisFile);
		} catch (FileNotFoundException file0) {
			file0.printStackTrace();
			if(isLoaded() == true) { scnr = new Scanner(thisFile); }
			else { return -1.0; } // Could not parse file
		}
		
		int countLines = 0;
		while (scnr.hasNextLine())
		{
			scnr.nextLine();
			++countLines;
		}
		
		scnr.reset();
		scnr = new Scanner(thisFile);
		
		for (int i = 0; i < countLines - 1; ++i)
		{
			scnr.nextLine();
		}
		
		scnr.next();scnr.next();scnr.next();scnr.next();scnr.next();scnr.next();scnr.next();
		double value = Double.parseDouble(scnr.next());
		scnr.close();
		return value;
	}
	// Find the latest year that has been released in data
	public static int getLatestYearInData () throws MalformedURLException, FileNotFoundException, IOException
	{
		Scanner scnr;
		scnr = null;
		
		File thisFile = new File(localFileString1);
		try {
			scnr = new Scanner(thisFile);
		} catch (FileNotFoundException file0) {
			file0.printStackTrace();
			if(isLoaded()) { scnr = new Scanner(thisFile); }
			else { return -1; } // Could not parse file
		}
		
		int countLines = 0;
		while (scnr.hasNextLine())
		{
			scnr.nextLine();
			++countLines;
		}
		
		scnr.reset();
		scnr = new Scanner(thisFile);
		
		for (int i = 0; i < countLines - 1; ++i)
		{
			scnr.nextLine();
		}
		int value = Integer.parseInt(scnr.next());
		scnr.close();
		return value;
	}
	// Generate a JSON file of past arctic ice extent values to be used by the javascript application
	public static void generateJSONFileExtentPastValues () throws IOException, JSONException 
	{
		JSONObject pastData = new org.json.JSONObject();
		
		for (int i = 1979; i < year ; i = i + 4)
		{
			String intI = Integer.toString(i);
			
			pastData.put(intI, getExtentMinimum(intI) );
		}
		
		File file = new File("Site/pastArcticIceExtentData.json");
		file.createNewFile();
		FileWriter fileWriter = new FileWriter(file);
	 	JSONValue.writeJSONString(pastData, fileWriter);
		fileWriter.close();	
	}
	// Generate a JSON file of the current arctic ice extent values to be used by the javascript application
	public static void generateJSONFileExtentCurrentValue () throws MalformedURLException, FileNotFoundException, JSONException, IOException
	{
		int latestYear = getLatestYearInData();
		
		String yearString = Integer.toString(latestYear);
		JSONObject currentData = new org.json.JSONObject();
		
		currentData.put(yearString, getLatestExtentMinimum());
		
		File file = new File("Site/currentArcticIceExtentData.json");
		file.createNewFile();
		FileWriter fileWriter = new FileWriter(file);
	 	JSONValue.writeJSONString(currentData, fileWriter);
		fileWriter.close();	
	}
	// Generate a JSON file of future arctic ice extent values to be used by the javascript application
	public static void generateJSONFileExtentFutureValues () throws NumberFormatException, MalformedURLException, JSONException, IOException
	{
		int latestYear = getLatestYearInData();
		JSONObject futureData = new org.json.JSONObject();
		
		for (int i = latestYear + 4; i < 2045 ; i = i + 4)
		{
			String intI = Integer.toString(i);
			
			futureData.put(intI, getFutureExtentMinimum(intI) );
		}
		
		File file = new File("Site/futureArcticIceExtentData.json");
		file.createNewFile();
		FileWriter fileWriter = new FileWriter(file);
	 	JSONValue.writeJSONString(futureData, fileWriter);
		fileWriter.close();
	}
	// Generate a JSON file of past arctic ice area values to be used by the javascript application
	public static void generateJSONFileAreaPastValues () throws MalformedURLException, FileNotFoundException, JSONException, IOException
	{
		JSONObject pastData = new org.json.JSONObject();
		
		for (int i = 1979; i < year ; i = i + 4)
		{
			String intI = Integer.toString(i);
			
			pastData.put(intI, getAreaMinimum(intI) );
		}
		
		File file = new File("Site/pastArcticIceAreaData.json");
		file.createNewFile();
		FileWriter fileWriter = new FileWriter(file);
	 	JSONValue.writeJSONString(pastData, fileWriter);
		fileWriter.close();	
	}
	// Generate a JSON file of the current arctic ice area values to be used by the javascript application
	public static void generateJSONFileAreaCurrentValue () throws MalformedURLException, JSONException, IOException
	{
		int latestYear = getLatestYearInData();
		
		String yearString = Integer.toString(latestYear);
		JSONObject currentData = new org.json.JSONObject();
		
		currentData.put(yearString, getLatestAreaMinimum());
		
		File file = new File("Site/currentArcticIceAreaData.json");
		file.createNewFile();
		FileWriter fileWriter = new FileWriter(file);
	 	JSONValue.writeJSONString(currentData, fileWriter);
		fileWriter.close();	
	}
	// Generate a JSON file of future arctic ice area values to be used by the javascript application
	public static void generateJSONFileAreaFutureValues () throws NumberFormatException, MalformedURLException, JSONException, IOException
	{
		int latestYear = getLatestYearInData();
		JSONObject futureData = new org.json.JSONObject();
		
		for (int i = latestYear + 4; i < 2045 ; i = i + 4)
		{
			String intI = Integer.toString(i);
			
			futureData.put(intI, getFutureAreaMinimum(intI) );
		}
		
		File file = new File("Site/futureArcticIceAreaData.json");
		file.createNewFile();
		FileWriter fileWriter = new FileWriter(file);
	 	JSONValue.writeJSONString(futureData, fileWriter);
		fileWriter.close();	
	}
	
}
