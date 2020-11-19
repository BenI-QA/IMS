# IMS-Project

This project is my own attempt of the creation of a Item Management System which include the Integration and Interation between Customer, Order and Item Tables.

## Getting Started

To begin using the project download a clone of the git repository of ims_project above, Here you can find a .jar file which can run the project via the command line or powershell.
If you wish to make additions to the project clone to respository to your machine and run your prefered Java IDE (preferably Eclipse).
Once you have eclipse open, go into your workspace and open "Open Project". Locate the area which you saved your ims_project repository. DO NOT OPEN FIRST FILE: click into the directory ims_project where you can find a second directory with an identical title, this will be your Project File.

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites


Eclipse version 20.6 : A IDE which is highly compatible with work with java projects
jdk-8.271 : This allows you to compile java programs within windows. This can be found on the oricle websites:

What things you need to install the software and how to install them

####Eclipse

#### JDK-8.271 installation
-Once you have downloaded the jdk file, run it and follow the installation procecdure to enable it on your operating system.
-Once the installation is complete go to your "Edit your system Environment Variables" in your windows search bar.
-Click on "Environement Variables.." toward the bottom right of the window
-In the new popup window, we will create our new JAVA_HOME variable and give it a value corresponding to our JDK folder.
-Click "OK" and we should now see our new JAVA_HOME system variable in the "System variables" box.
-Now we need to edit the "Path" variable to include a reference to the /bin (binary files) folder in our JAVA_HOME.
-To do this select the "Path" variable under "System variables" and then click "Edit...".
-You need to append the path of the Java /bin folder to this "Path" variable, so to do this, we will reference the JAVA_HOME variable we created like so: %JAVA_HOME%\bin
-When you have done this your "Path" variable make sure that there is a semi-colon before and after %JAVA_HOME%\bin.


### Installing

A step by step series of examples that tell you how to get a development env running

End with an example of getting some data out of the system or using it for a little demo

## Running the tests

Explain how to run the automated tests for this system. Break down into which tests and what they do

### Unit Tests 

TExplain what these tests test, why and how to run them
JUnit test run the core methods of the functionality which doesn't involve objects, mostly varibales and we expect a return to ensure the code does not break.s
Give an example
```

### Integration Tests 
Explain what these tests test, why and how to run them
Mockito tests the object functions within your code.
Mockito
```
Give an example
```

## Deployment
Either run the .jar file throught powershell or command line javac "file.jar" then java "file.java"

Run through IDE editor in eclipse for testing
## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
  org.apache.maven.plugins
  -- maven-assembly-plugin
  -- maven-jar-plugin
  -- maven-deploy-plugin
  -- maven-jar-plugin
## Versioning
  3.2.0
We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Ben Irwin** - *Initial work* - [christophperrins](https://github.com/christophperrins)


## Acknowledgments

* Hat tip to anyone whose code was used
* christophperrins for basing the code structure of his customer section
* Piers Barber for guiding the project
