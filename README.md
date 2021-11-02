Project: 
-----------------------
    calculator


Project Description :
-----------------------
    calculator - Automation Project for Calculator app
    
    
    1. Modules
    2. Setup
		2.1 Running tests using Maven
		2.2 Running tests using Dockerfile	
		2.3 Maven Properties description	
    3. Reporting
    
    

Prerequisite software
--------------------------------------------
   
    *Java: download and install Java in the system as per the system configuration and set the path in the environment variables.
    *Maven: download and install Maven in the system as per the system configuration and set the path in the environment variables.
    *IDE: download and install either eclipse or IntelliJ community version as per the system configuration.


1.Modules:
-----------------

    features -                  all automated tests(.features) are stored in this module
    app  -                 		module contains code for interaction with calculator app
    config -  					configuration of the device and browser stack
	stepdefs -                	Contains features Step definition implementation
	Manager -                   Driver initialization and manage
	Utils -                     External library for writing test cases
	Dockerfile -				to run the test in dockerized container
     
      
2.Setup:
--------
 Using IntelliJ/Ecllipse IDEA
    
    IntelliJ -> Start IDEA -> File -> Open -> Choose pom.xml file located in calculator folder
    Ecllipse -> Star IDEA -> File -> Import -> Existing maven project -> Choose pom.xml file located in calculator folder -> Ok


2.1 Running tests using Maven:
------------------------------

   Start command line
    cd to $(project_dir)/calculator
    Run command 
    mvn clean test -Dorientation={orientation} -DdeviceName={DeviceName} -DosVersion={version}
        
        Example:
        mvn clean test -Dorientation=Landscape -DdeviceName="Samsung Galaxy S10 Plus" -DosVersion=9.0
		


2.2 Running tests using Dockerfile:
-----------------------------------

	To run using dockerfile, can use below command
		- 	 docker build --no-cache -t calculator .
		
		

2.3 Maven properties description:
---------------------------------

	-Dorientation
				Describes the device orientation for executing the test cases. Options {Landscape,Portrait}
				
	-DdeviceName
				Describes the device name to run the test cases, will be used in browserstack
	
	-Dversion
				Describes the device OS version
				
	-Dbrowserstackuser
				Describes the browserstack user.

	-Dbrowserstackkey
				Describes the browserstack key.	
				
	-Dapp
				Describes the app details in browser stack.		

	-Dtags(Optional)
				Describes the tags for filtering the scenerios from feature for execution
				
				

3.Reporting:
-------------
    
	Simple cucumber report genarated at /target/cucumber.html