<h1>Personal finance application</h1>
A personal finance application with Java backend.

## Contents
- [How to run the application localy](#how-to-run-the-application-localy)
- [Package Structure]
- [Maven] 
- [Information about the client]
- [Information about the tests] 


## How to run the application localy
To run the application localy, you will need Maven on your computer. Firstly, check to see if you have already installed it. If you do not have Maven on your computer, download it [here](https://maven.apache.org/download.cgi). Unpack Maven and read the README-file within Maven.

After installing maven, you will need to download the SpendWise application zip folder from git. To do this, simply go to the home page of the the gitlab project and press the download button, then select zip.
Once it is done downloading, right clicking the application folder and then click on "copy as path", or by pressing ctrl+shift+c with the folder selected.
Then open a command prompt and type: "cd {your_path_goes_here}". (no brackets).
To then run the program you will have to type: 
```text
mvn javafx:run
```
in the terminal.


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
|   |   └── java/
|   |       ├── no.ntnu.idatt1002.spendwise/
|   |       |   ├── budgeting/
|   |       |   |   ├── Budgeting
|   |       |   |   ├── BudgetingCell
|   |       |   |   └── BudgetingController
|   |       |   |
|   |       |   ├── category/
|   |       |   |   ├── EditCategory
|   |       |   |   └── EditCategoryController
|   |       |   |
|   |       |   ├── data/
|   |       |   |   ├── Category
|   |       |   |   ├── Expense
|   |       |   |   ├── Income
|   |       |   |   ├── RecurringExpense
|   |       |   |   ├── RecurringIncome
|   |       |   |   ├── Register
|   |       |   |   ├── RegisterController
|   |       |   |   └── Transactions
|   |       |   |
|   |       |   ├── expeptions/
|   |       |   |   ├── ConformityExpeption
|   |       |   |   └── DuplicateNameException
|   |       |   |
|   |       |   ├── expenses/
|   |       |   |   ├── EditExpenses
|   |       |   |   ├── EditExpensesController
|   |       |   |   ├── Expenses
|   |       |   |   └── ExpensesController
|   |       |   |
|   |       |   ├── help/
|   |       |   |   └── Help
|   |       |   |
|   |       |   ├── home/
|   |       |   |   ├── ConfirmBox
|   |       |   |   ├── HomePage
|   |       |   |   └── HomePageController
|   |       |   |
|   |       |   ├── income/
|   |       |   |   ├── EditIncome
|   |       |   |   ├── EditIncomeController
|   |       |   |   ├── Income
|   |       |   |   └── IncomeController
|   |       |   |
|   |       |   └── recurring/
|   |       |       ├── RecurringTransactions
|   |       |       └── RecurringTransactionsController
|   |       |  
|   |       └── resources/
|   |           ├── database/
|   |           |   ├── .gitkeep
|   |           |   ├── backup.json
|   |           |   └── register
|   |           |
|   |           ├── HelpScenes/
|   |           |   ├── HelpBudgeting.fxml
|   |           |   ├── HelpEdit.fxml
|   |           |   ├── HelpExpenses.fxml
|   |           |   ├── HelpHome.fxml
|   |           |   ├── HelpIncome.fxml
|   |           |   └── HelpRecTrans.fxml
|   |           |
|   |           ├── Icons/
|   |           |   ├── BudgetingIcon-png
|   |           |   ├── ExpenseOverviewIcon.png
|   |           |   ├── HelpIcon
|   |           |   ├── IncomeOverviewIcon.png
|   |           |   ├── RecurringTransactions.png
|   |           |   ├── Settings.png
|   |           |   └── SpendWiseIcon.png 
|   |           |
|   |           ├── Budgeting.fxml
|   |           ├── EditCategory.fxml
|   |           ├── EditExpense.fxml
|   |           ├── EditIncome.fxml
|   |           ├── Expense.fxml
|   |           ├── Income.fxml
|   |           ├── RecurringTransactions.fxml
|   |           └── SpendWiseHomePage.fxml        
|   |
|   └── test/
|   |   └── java/
|   |       └── no.ntnu.idatt1002.spendwise.data
|   |           ├── RegisterControllerTest
|   |           └── RegisterTest     
|   | 
|   └── .gitkeep
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

