# groferAssignment


We can execute the code using below mentioned ways:

1- /groferAssignment/groferTest/testSuite/SuiteFile.xml 

2- Directly executing test from the TestClass.java

There are two test methods,

1- getTaskCompletedUser() method has input data coming from properties file, 
2- getTaskCompletedUsingDataProvider() method is getting the data from data provider and the data is saved in mongo database.

For both testcases to execute just start mongo in local and create below database and collection:

Database Name: "testGo" Collection Name: "testGoData"

The document structure is as mentioned below:


{

"executeTest" : true,   --> This field activates and disabled the testcases in cases something goes wrong with test data.

"groferLatUpper" : "5",

"groferLatLower" : "-40",

"groferLongUpper" : "100",

"groferLongLower" : "5",

"percentage" : 50      --> This is also configurable as it is getting picked either from mongo or properties file depending on test cases

}
