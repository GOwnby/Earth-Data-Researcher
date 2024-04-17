import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import org.json.*;
import org.json.simple.JSONValue;

public class SeaLevel {
	
	private static String localFileString = "data/SeaLevelData";
	// Comes as a zip file and only records data from part way through 1993 to part way through 2015...
	// Need a new data source
	private static String fileURL = "http://www.cmar.csiro.au/sealevel/GMSL_SG_2011_up.html";

	public static double getAvgRateOfChange (String yearOne, String yearTwo)
	{
		
		
		return 0.0;
	}
	
	public static double getAvgRateOfChange (int yearOne, int yearTwo)
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
		File seaLevel = null;
		Scanner scanSeaLevel = null;
		try 
		{
			seaLevel = new File(localFileString);
			scanSeaLevel = new Scanner(seaLevel);
		} 
		catch (FileNotFoundException e)
		{
			 InputStream in = null;
			 try 
			 {
				 URL url = new URL(fileURL);
				 HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				 connection.setRequestMethod("GET");
				 in = connection.getInputStream();
				 Files.copy(in, Paths.get(localFileString));
			 } 
			 finally
			 {
				try 
				{
					seaLevel = new File(localFileString);
					scanSeaLevel = new Scanner(seaLevel);
				} 
				catch (FileNotFoundException f)
				{
					f.printStackTrace();
					System.out.println("Bad Request. Check connection and data source.");
					return false;
				}
			 }
		}
		
		scanSeaLevel.close();
		return true;
	}
	
	public static double getAvgGMSLVariation (String yearDate)
	{
		
		
		return 0.0;
	}
	
	public static double getAvgGMSLVariation (int yearDate)
	{
		
		
		return 0.0;
	}

	public static double getFutureGSMLVariation (String yearDate, double rateOfChange)
	{
		
		
		return 0.0;
	}
	
	public static double getFutureGSMLVariation (int yearDate, double rateOfChange)
	{
		
		
		return 0.0;
	}
	
	public static double getFutureGSMLVariation (String yearDate, String yearOne, String yearTwo)
	{
		
		
		return 0.0;
	}
	
	public static double getFutureGSMLVariation (int yearDate, int yearOne, int yearTwo)
	{
		
		
		return 0.0;
	}
	
	public static double getLatestGSMLVariation ()
	{
		
		
		return 0.0;
	}
	
}
