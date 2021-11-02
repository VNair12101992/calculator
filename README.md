Project: 
-----------------------
    miro-signup


Project Description :
-----------------------
    miro-signup - Automation Project for validating Miro's Sign Up Page
    
    
    1. Modules
		1.1. General Project Structure    
    2. Setup
		2.1 Running tests using Maven   
    3. Reporting
    
    

--------------------------------------------
Prerequisite software
   
    *Java: download and install Java in the system as per the system configuration and set the path in the environment variables.
    *Maven: download and install Maven in the system as per the system configuration and set the path in the environment variables.
    *IDE: download and install either eclipse or IntelliJ community version as per the system configuration.


1.Modules:
-----------------

    TestCases -                 all automated tests are stored in this module
    TestData  -                 all test data required for the executing testcases are in this module
    Environment -               all environment related data (ex: host urls) is stored in this module
    Extent Report Properties -  Simple property file for generating html report
	Domain DAO -                Domain class for simple data accessing, tightly coupled with UI functionality
	Manager -                   Driver initialization and manage
	Pages -                     POM and Page Factory for easy object storage and validation
	Utils -                     External library for writing test cases (Report, HttpConnection, JSON Reader)
	Generator -                 Data generator for mocking data
	
   
1.1.General Project Structure:
---------------------------------
    .
    ├──miro-signup                        # Main automation module
    │  ├──generator                       
    │  │   └── DataGenerator 			  # Data generator utilities
    │  ├──utils 
    │  │   ├── ExtentManager              # Extent report manager
    │  │   ├── ExtentTestManager          # Extent test manager for initialization               
    │  │   ├── HttpConnection             # Http connection utility for API calls
	│  │   └── ReadJsonData   			  # JSON reader/parser utility	
    │  ├──domain_dao 
	│  │   └── SignUp					  # Signup domain java class		
	│  ├──listners 
	│  │   └── TestListener				  # Listener class for extent report	
	│  ├──manager 
	│  │   └── WebDriverManager			  # Driver manager for initialization and configuration	
	│  ├──pages 
	│  │   ├── Cookies					  # Cookies page OR and validation	
    │  │   ├── EmailConfirmPage			  # Email-Confirm page OR and validation 
	│  │   └── SignupPage				  # Sign-Up page OR and validation 
    │  ├──testcases
	│  │   ├── signup	
	│  │   │    └── TestSignUpPage	      # Sign-Up page testcases
	│  │   └── BaseTest 	              # Base utility for testcases
	│  └──resources
	│      ├── environment 	              # Environment variables
    │      ├── extent.properties
	│      └── data                       # Test Data
    │
    ├──extent-report                      # Extent Report	
    ├──target/reports                     # Reports
    ├──pom.xml
    ├──testng.xml
    └──README.md	
     
      
2.Setup:
--------
 Using IntelliJ/Ecllipse IDEA
    
    IntelliJ -> Start IDEA -> File -> Open -> Choose pom.xml file located in miro-sigup folder
    Ecllipse -> Star IDEA -> File -> Import -> Existing maven project -> Choose pom.xml file located in miro-sigup folder -> Ok


2.1 Running tests using Maven:
------------------------------

   Start command line
    cd to $(project_dir)/miro-signup
    Run command 
    mvn clean test -Dbrowser={browser} -Dsurefire.suiteXmlFiles={testng.xml} -Denv=qa
        
        Example:
        mvn clean test -Dbrowser=chrome -Dsurefire.suiteXmlFiles=testng.xml -Denv=prod
        Note: Only chrome and firefox supported


3.Reporting:
-------------
    
	Simple extent report generated at $(project_dir)/miro-signup/extent-report
	Surefire report will be generated at  miro-signup\target\surefire-reports
	
	
4.Scenerios:
------------

	1.  validateSignUpPage          		  -  validates the signup page loading with all the objects
	2.  validateCookieSettingsModal 		  -  validates the cookie modal for buttons and title and closure
	3.  validateBlankSignUpErrorMessages 	  -  validates error messages on leaving all fields blank for sign-up
	4.  validateBlankEmailErrorMsg            -  validates error message for leaving email field blank during sign-up
	5.  validateInvalidEmailErrorMsgAndReset  -  validates error message for invalid email (without ".")
	6.  validateIncorrectEmailErrorMsg        -  validates error message for incorrect email (without "@")
	7.  validateUncheckedTermsErrorMsg        -  validates error message for not selecting terms checkbox during sign-up
	8.  validateBlankNameErrorMessage         -  validates error message for leaving name field blank during sign-up
	9.  validateHintPassword                  -  validates hint password for weak, so-so, good, great
	10. validateNameMaxLength 			      -  validates name field max length 
	11. validateResetOnReload 			      -  validates the fields reset on reload
	12. validateSocialSignUp                  -  validates social signup modals 
	13. validateSuccessfullSignUp             -  validates successful sign up with email verification
	14. validateDuplicateEmailSignUp          -  validates duplicate email error message during signup
	17. validateLinks                         -  validates the links are working fine in the sign-up page
	18. validateConfirmFormSubmissionIfBackNavigated  -  confirms submission even if back navigated from email-confirm page
	19. validateAllTheText                    -   validates all the text present in sign-up page.
	20. validateBlankNameErrorMessage         -   validates error message for leaving password field blank during sign-up
   
   