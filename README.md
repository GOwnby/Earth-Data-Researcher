# Earth-Data-Researcher

A Java application for researching Earth's atmosphere using public data released by NASA and NOAA. 

This application may be used by the researcher to interpret data, or to
graph important elements of the atmosphere as well as average temperature.

Here are some common examples of how this program can be used:

```
System.out.println(CarbonDioxide.getAvgPPM(1989));
System.out.println(CarbonDioxide.getAvgRateOfChange(1980, 2000));

System.out.println(GlobalTemperature.getAvgLandOceanTemperatureIndex("1880"));
System.out.println(GlobalTemperature.getAvgRateOfChangeFromCarbonDioxide());
```

The program may be used to generate JSON that in turn may be used by a javascript
application.

```
CarbonDioxide.generateJSONFilePastValues();
CarbonDioxide.generateJSONFileCurrentValue();
CarbonDioxide.generateJSONFileFutureValues(CarbonDioxide.getAvgRateOfChange(year - 2, year - 1));
```

![Screenshot 2024-04-17 111746](https://github.com/GOwnby/Earth-Data-Researcher/assets/37450012/be133106-b1f6-47fa-9cf8-91d348533b02)
![Screenshot 2024-04-17 111848](https://github.com/GOwnby/Earth-Data-Researcher/assets/37450012/7e8ca3cc-89a4-4d67-9656-c2bb799eb2a8)
![Screenshot 2024-04-17 111908](https://github.com/GOwnby/Earth-Data-Researcher/assets/37450012/366363d8-304d-4c33-90fa-9ccfbf08a38b)
![Screenshot 2024-04-17 111944](https://github.com/GOwnby/Earth-Data-Researcher/assets/37450012/761c9f69-1d8e-4917-bae7-a586f4159008)


# Setting up the Java Application
To compile this program, install the [Java JDK](https://www.oracle.com/java/technologies/downloads/#jdk22-linux)

The default Main function generates JSON files for the Javascript application, it may be changed to suit your research. 
Download this repository and unzip the files to their own folder; then, open a terminal and navigate to the folder. 
Compile and run the Main function with the following commands.

```
javac Main.java
java Main
```

# Setting up the Javascript Application
In order to run the Javascript application, an Apache web server can be setup and installed relatively easily
on your local Linux, Mac, or Windows system.

[Linux Instructions](https://httpd.apache.org/docs/2.4/install.html)
[Mac Instructions](https://www.javatpoint.com/how-to-install-apache-on-mac)
[Windows Instructions](https://mid.as/kb/00143/install-configure-apache-on-windows)

Once the apache webserver is installed and running, use your browser to navigate to 127.0.0.1.
Once the test page is showing "It works!", simply copy all files from the project's "Site" folder 
and paste them into the Apache's "htdocs" folder, overwriting the "Index.html" file.
