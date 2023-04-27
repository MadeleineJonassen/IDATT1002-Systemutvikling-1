<h1>Personal finance application</h1>
A personal finance application with Java backend

##Contents
- [How to run the application localy](#how-to-run-the-application-localy)
- [JavaDocs] (#javadocs)
- [Package Structure] (#package-Structure)
- [Maven] (#maven)
- [Information about the client] (#information-about-the-client)
- [Information about the tests] (#information-about-the-tests)


## How to run the application localy
To run the application localy, you will need Maven on your computer. Firstly, check to see if you have already installed it. If you do not have Maven on your computer, download it [here](https://maven.apache.org/download.cgi). Unpack Maven and read the README within Maven.
...


## JavaDocs
// Vedlegg til repository


## Package Structure
```text
idatt-1002-2023-5/
├── .idea/
|   ├── .gitignore
|   ├── checkstyle-idea.xml
|   ├── compiler.xml
|   ├── encodings.xml 
|   ├── jarRepositories.xml
|   ├── misc.xml
|   ├── vcs.xml
|   └── workspace.xml
|
├── javadoc/
|   ├── legal/
|   |   ├── ADDITIONAL_LICENCE_INFO
|   |   ├── ASSEMPLY_EXPEPTION
|   |   ├── jquery.md
|   |   ├── jqueryUI.md
|   |   └── LINCENSE
|   |
|   ├── no/
|   |   └── ntnu/
|   |       └── idatt1002/
|   |           └── demo/
|   |               ├── class-use/
|   |               ├── data/
|   |               ├── repo/
|   |               ├── view/
|   |               ├── MyApp.html
|   |               ├── package-summary.html
|   |               ├── package-tree.html
|   |               └── package-use.html
|   |     
|   ├── resources/
|   |   ├── glass.png
|   |   ├── home.png
|   |   └── x.png
|   |
|   ├── script-dir/
|   |   ├── jquery-3.6.0.min.js
|   |   ├── jquery-ui.min.css
|   |   └── jquery-ui.min.js
|   |   
|   ├── allclasses-index.html
|   ├── allpackages-index.ntml
|   ├── copy.svg
|   ├── element-list
|   ├── help-doc.html
|   ├── index.html
|   ├── jquery-ui.overrides.css
|   ├── member-search-index.js
|   ├── module-search-index.js
|   ├── overview-summary-html
|   ├── overview-tree.html
|   ├── package-search-index.js
|   ├── script.js
|   ├── search.html
|   ├── search.js
|   ├── search-page.js
|   ├── serialized-form.html
|   ├── stylesheet.css
|   ├── tag-search-index.js
|   └── type-search-index.js
|
├── lib/
|   ├── hamcrest-core-1.3.jar
|   └── junit-4.12.jar
|
├── src/
|   ├── main/
|   |   ├── java/
|   |   |   ├── dao/
|   |   |   |   ├── Database.java
|   |   |   |   ├── GroupChatDAO.java
|   |   |   |   ├── MessageDAO.java
|   |   |   |   └── UserDAO.java
|   |   |   | 
|   |   |   ├── data/
|   |   |   |   ├── GroupChat.java
|   |   |   |   ├── Message.java
|   |   |   |   └── User.java
|   |   |   |  
|   |   |   ├── resources/
|   |   |   |   ├── CalculatorResource.java
|   |   |   |   ├── GroupChatResource.java
|   |   |   |   ├── MessageResource.java
|   |   |   |   └── UserResource.java
|   |   |   |   
|   |   |   └── websockets/
|   |   |       └── Websocket.java
|   |   |       
|   |   └── webapp/
|   |       ├── css/
|   |       |   ├── app.css
|   |       |   ├── index.css
|   |       |   ├── popupform.css
|   |       |   └── settings.css
|   |       |
|   |       ├── js/
|   |       |   ├── calculator.js
|   |       |   ├── createElements.js
|   |       |   ├── groupchat.js
|   |       |   ├── index.js
|   |       |   ├── message.js
|   |       |   ├── responsive.js
|   |       |   ├── settings.js
|   |       |   ├── user.js
|   |       |   └── websocket.js
|   |       |
|   |       ├── WEB-INF/
|   |       |   └── web.xml
|   |       |
|   |       ├── app.html
|   |       ├── index.html
|   |       └── settings.html
|   |
|   └── test/
|       └── java/
|           ├── CalculatorResourceTest.java
|           ├── MessageDAOTest.java
|           ├── MessageResourceTest.java
|           ├── UserDAOTest.java
|           └── UserResourceTest.java      
|
├── target/
|   ├── classes/
|   ├── generated-sources/
|   └── maven-status/
|
├── .gitignore
├── .gitlab-ci.yml
├── pom.xml
├── README.md
└── run-bat
```


## Maven
This system uses Maven. Maven is a tool that handles dependencies within a system. By using Maven, one can assure that every team member uses the same version of dependencies. These dependencies are declared in the pom.xml file. Not only can Maven be used for dependencies, but it can also used to compile and run tests.


## Information about the client
The client consists of two different types of files:
- Java.fxml
- Java

The Java.fxml files are used for user experience and the universal design. Java is used to give the elements within the application functionality; such as user interaction and changes within the application.


## Information about the tests
To test the application, JUnit was used. These test can be compiled by writing
```text
mvn clean test
```
in the terminal. It is important to have Maven installed on the computer before doing this. An alternative option is to run the test in IDE.

