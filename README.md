<h1>Spendwise</h1>
A personal finance application made for the course IDATT1002 Software Engineering, with Java for the backend and JavaFX for the frontend.
My first ever Software Engineering project.


## VideoDemo
https://github.com/user-attachments/assets/629d380f-f552-4245-95f7-b98569288d37



## Contents
- [How to run the application locally]
- [Maven] 
- [Information about the client]
- [Information about the tests]
- [Package Structure]


## How to run the application locally
To run the application locally, you will need Maven on your computer. First, check to see if you have already installed it. If you do not have Maven on your computer, download it [here](https://maven.apache.org/download.cgi). Unpack Maven and read the README file within Maven.

After installing Maven, you will need to download the SpendWise application zip folder from git. To do this, go to the GitLab project's home page, press the download button, and select zip.
Once it is done downloading, right-click the application folder and then click on "copy as path", or by pressing ctrl+shift+c with the folder selected.
Then open a command prompt and type: "cd {your_path_goes_here}". (no brackets).
To then run the program you will have to type: 
```text
mvn javafx:run
```
in the terminal.


## Maven
This system uses Maven. Maven is a tool that handles dependencies within a system. By using Maven, one can ensure that every team member uses the same version of dependencies. These dependencies are declared in the pom.xml file. Not only can Maven be used for dependencies, but it can also used to compile and run tests.


## Information about the client
The client consists of two different types of files:
- Java.fxml
- Java

The Java.fxml files are used for user experience and the universal design. Java is used to give the elements within the application functionality; such as user interaction and changes within the application.


## Information about the tests
To test the application, JUnit was used. These tests can be compiled by writing
```text
mvn clean test
```
in the terminal. It is important to have Maven installed on the computer before doing this. An alternative option is to run the test in IDE.





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
|   |               |    └── MyApp.html
|   |               |
|   |               ├── data/
|   |               |   ├── class-use/
|   |               |   |   └── MyEntity.html
|   |               |   ├── MyEntity.html
|   |               |   ├── package-summary.html
|   |               |   ├── package-tre.html
|   |               |   └── package-use.html
|   |               |   
|   |               ├── repo/
|   |               |   ├── class-use/
|   |               |   |   └── MyEntityRepo.html
|   |               |   ├── MyEntityRepo.html
|   |               |   ├── package-summary.html
|   |               |   ├── package-tre.html
|   |               |   └── package-use.html
|   |               |
|   |               ├── view/
|   |               |   ├── MyApp.html
|   |               |   ├── package-summary.html
|   |               |   ├── package-tre.html
|   |               |   └── package-use.html
|   |               |  
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
|   |   ├── database/
|   |   |   ├──.getkeep
|   |   |   ├── backup.json
|   |   |   └── register.json
|   |   |
|   |   ├── HelpScenes/
|   |   |   ├── HelpBudgeting.fxml
|   |   |   ├── HelpEdit.fxml
|   |   |   ├── HelpExpenses.fxml
|   |   |   ├── HelpHome.fxml
|   |   |   ├── HelpIncome.fxml
|   |   |   └── HelpRecTrans.fxml
|   |   |
|   |   ├── Icons/
|   |   |   ├── BudgetingIcon-png
|   |   |   ├── ExpenseOverviewIcon.png
|   |   |   ├── HelpIcon
|   |   |   ├── IncomeOverviewIcon.png
|   |   |   ├── RecurringTransactions.png
|   |   |   ├── Settings.png
|   |   |   └── SpendWiseIcon.png
|   |   |
|   |   ├── no.
|   |   |   └── ntnu
|   |   |       └── idatt1002
|   |   |           └── spendwise/
|   |   |              ├── budgeting/
|   |   |              |    ├── Budgeting
|   |   |              |    ├── BudgetingCell
|   |   |              |    └── BudgetingController
|   |   |              |
|   |   |              ├── category/
|   |   |              |   ├── EditCategory
|   |   |              |   └── EditCategoryController
|   |   |              |
|   |   |              ├── data/
|   |   |              |   ├── Category
|   |   |              |   ├── Expense
|   |   |              |   ├── Income
|   |   |              |   ├── RecurringExpense
|   |   |              |   ├── RecurringIncome
|   |   |              |   ├── Register
|   |   |              |   ├── RegisterController
|   |   |              |   └── Transactions
|   |   |              |
|   |   |              ├── expeptions/
|   |   |              |   ├── ConformityExpeption
|   |   |              |   └── DuplicateNameException
|   |   |              |
|   |   |              ├── expenses/
|   |   |              |   ├── EditExpenses
|   |   |              |   ├── EditExpensesController
|   |   |              |   ├── Expenses
|   |   |              |   └── ExpensesController
|   |   |              |
|   |   |              ├── help/
|   |   |              |   └── Help
|   |   |              |
|   |   |              ├── home/
|   |   |              |   ├── ConfirmBox
|   |   |              |   ├── HomePage
|   |   |              |   └── HomePageController
|   |   |              |
|   |   |              ├── income/
|   |   |              |   ├── EditIncome
|   |   |              |   ├── EditIncomeController
|   |   |              |   ├── Income
|   |   |              |   └── IncomeController
|   |   |              |
|   |   |              └── recurring/
|   |   |                  ├── RecurringTransactions
|   |   |                  └── RecurringTransactionsController
|   |   |
|   |   ├── Budgeting.fxml
|   |   ├── EditCategory.fxml
|   |   ├── EditExpense.fxml
|   |   ├── EditIncome.fxml
|   |   ├── Expense.fxml
|   |   ├── Income.fxml
|   |   ├── RecurringTransactions.fxml
|   |   └── SpendWiseHomePage.fxml 
|   |  
|   ├── generated-sources/
|   |   └── annotations/
|   | 
|   └── maven-status/
|       └── maven-compiler-plugin/
|           └── compile/
|               └── default-compile/
|                   └── createdFiles.lst
|                   └── inputFiles.lst
|
├── .gitignore
├── .gitlab-ci.yml
├── pom.xml
├── README.md
└── run-bat
```
