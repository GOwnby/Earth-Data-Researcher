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

![Screenshot 2024-04-14 101430](https://github.com/GOwnby/Earth-Data-Researcher/assets/37450012/8806e5f3-e741-4936-b957-d3280762464b)
![Screenshot 2024-04-14 101523](https://github.com/GOwnby/Earth-Data-Researcher/assets/37450012/5e1b0bce-2aef-4ea1-b2ff-20b1242c8284)
![Screenshot 2024-04-14 101644](https://github.com/GOwnby/Earth-Data-Researcher/assets/37450012/40780f09-bb61-43ea-9c07-30374ac28341)
![Screenshot 2024-04-14 101747](https://github.com/GOwnby/Earth-Data-Researcher/assets/37450012/36688f8e-6d4c-4e6e-84b2-09a6a299260f)


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