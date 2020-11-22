Coverage: 66%

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
A google cloud sql database has already been setup therefore no sql installation is required on your system.

#### JDK-8.271 installation
 Once you have downloaded the jdk file, run it and follow the installation procecdure to enable it on your operating system.
- Once the installation is complete go to your "Edit your system Environment Variables" in your windows search bar.
- Click on "Environement Variables.." toward the bottom right of the window
- In the new popup window, we will create our new JAVA_HOME variable and give it a value corresponding to our JDK folder.
- Click "OK" and we should now see our new JAVA_HOME system variable in the "System variables" box.
- Now we need to edit the "Path" variable to include a reference to the /bin (binary files) folder in our JAVA_HOME.
- To do this select the "Path" variable under "System variables" and then click "Edit...".
- You need to append the path of the Java /bin folder to this "Path" variable, so to do this, we will reference the JAVA_HOME variable we created like so: %JAVA_HOME%\bin
- When you have done this your "Path" variable make sure that there is a semi-colon before and after %JAVA_HOME%\bin.



#### Maven Installtion
Download the maven installation file which can be found here: https://maven.apache.org/download.cgi \n
- Save the maven file into your c:\program files.
- Once again go into your system variables and add two new variables.
- Setting the path of Maven in environment Variables: 
- Search the Environment Variable --> Edit the System Environment variables--> Navigate to Advanced tab --> Environment --Variables)
- MAVEN_HOME : Click New --> Variable Name : MAVEN_HOME , Variable Value: C:\Program Files\apache-maven-3.5.2ii)
- M2_HOME : Click New --> Variable Name : M2_HOME , --Variable Value: C:\Program Files\apache-maven-3.5.2iii) Edit the 'Path' Environment Variable --> %M2_HOME%\binTesting -whether Maven is installed: mvn -version

### Installing

From this github repository click on the option to "clone"
On your local machine use git bash to move into a directory in which you would like your git repository to be located.
Use the command:
```
git clone https://github.com/BenI-QA/ims_project.git
```
This will import this repository to your selected file directory


## Functionality

###Login Details
Login details, to connect to the google cloud db on your console:
Username: root
Password: Passwordunknown123!

###Main menu
When your first enter your program you have the options of either selecting between 'customer', 'order' or 'item':
type in on the console your option:
```
1)Customer
2)Order
3)Item

Input: 'customer'
```
### Adding a Customer
This will then bring you to the customer menu where you can create, read, update or delete a customer:
```
1)Create
2)Read
3)Update
4)Delete
```
If you choose the delete function you will be asked to input the customer details:
```
What is the customer first name?
Ben
What is the customer Last name?
McManus
What is the customer email?
bm@gmail.com
What is the customer phone No.?
12345
```
### Reading a customer
Selecting the read option from the menu will automatically present all of the customers current on the system.

## Update Customer
From this you can update the customer through their first and last name and the user is able to change the customer email or phone no
```
What is the customer firstname?
Ben
What is the customer lastname?
Irwin
What would you like to change the email to?
change@gmail.com
What would you like to change the phone no. to?
54321
```
### Deleting a customer
Doing into the delete menu the user is presented with a read of all customers on the system. Here they are able to delete a customer by their ID.
```
What Customer ID do you wish to delete?
3
```


### Unit Tests 
The JUnit tests in the program can be located in the src/test/java folder and are used in the DAO classes, this contains code which tests out the variables and minor functionality which has be set up and uses fixed data to test. This will foes not involved running object in your testing. Do not expect return code for this. The JUnit plugin will indicate green if everything is functioning normally. To run this test on your code you run the project as  a 'JUnit test'. You can check how much the code has covered through the coverage option. 
Example:
```
@Test
	public void testCreate() {
		final Long id = 3L;
		final String f_name ="jake";
		final String l_name = "mac";
		final String email = "jm@gmail.com";
		final Long phone = 0232323L ;
		final Customer created = new Customer(id,f_name, l_name, email, phone);
		assertEquals(custDAO.readLatest(), custDAO.create(created));
	}
```
Here we are testing the variables that are entered into the customer class and check that they are of the correct type and we obtain the correct output.

### Integration Tests 
The Integration tests in the program can be located in the src/test/java folder and are used in the Controller classes, this tests the object functions within your code. With the use of dumby data that are fixed it checks that your object functions can function normally. To run this we use the same method as JUnit where we run the .class file as a 'run junit test'.
Example
```
@Test
	public void testCreate() {
		final String f_name ="jake";
		final String l_name = "mac";
		final String email = "jm@gmail.com";
		final Long phone = 0232323L ;
		final Customer customer = new Customer(f_name, l_name, email, phone);
			
		when(utils.getString()).thenReturn(f_name, l_name, email);
		when(utils.getLong()).thenReturn(phone);
		when(custDAO.create(customer)).thenReturn(customer);

		assertEquals(customer, custCon.create());

		verify(utils, times(3)).getString();
		verify(utils, times(1)).getLong();
		verify(custDAO, times(1)).create(customer);
			
	}
```
Here we are testing the the onjects used in the create() function within the customer controller.

## Deployment
To run your .jar file for your project move into the command line or power shell, move to the file location for your .jar file and enter
```
java -jar 'file'.jar
```
This will then run your test on your system and open your up to the first class of your project which is the runner and it will ask you for login details

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
