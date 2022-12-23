# Project_eWallet

Team members
Dung Do
Emilia Hoang

Description 
    Project overview: The BC eWallet Application was designed and developed on Android Studio and supported for Android OS. The application’s main objective and usage is to enable users to input, store and add the amount of funds into a system that is divided into categories mentioned below and make it easier for them to view, schedule, edit and control their budget in one single software.
Users will be able to input their desired income/budget into three categories such as salary, investment and saving. 
Let users to input expenses into 9 categories: bills, entertainment, food, health, housing, transport, toiletry, clothes and miscellaneous. This will help the users to monitor their expenses.
    Target audience: Users of any age (preferably 16 or above) who has ability and knowledge to use mobile application and who is interested in managing their finances. 

Project Management
    Git Hub: project is created and pulished in github for team members to contribute personal workload. When we complete a part of our work, we pull request to Git Hub to upload it. From there, the others can cross check by seeing how the program changes and decide whether to merge it or not.
    Estimated time spent: approx. 40 hours team work and meeting.
    Project allocation: Emilia is responsible for most of layout design and report. Dung Do is responsible for most of backbone idea and project debugging. We also do program testing together to adjust coding language, update the progress and seek effective solution to improve the project properly.
 
Deliverable features and describe the functionality per feature
    1. Navigation pane: includes Budget mode, Future recurring record mode, Set-up recurring expenses, Export CSV file.
    Dung Do
    Apply navigation drawer activity to manage 4 separate fragments: Budget fragment, Future fragment, Set up recurring fragment and Export fragment. The navigation drawer allows user to manage their activities whenever they open the app. 

    2. Future recurring record mode: User can add expenses and income freely.
    Dung Do
    A pie chart library is employed aesthetically to display user’s input after they complete their activity on 2 buttons: “Add Expense” and “Add Income”.
    A text view is also used to demonstrate the balance between Expense and Income. 
    When users click on Details button, a report in activity 5 is shown up.
    The future fragment includes activity 3,4,5,6.

    3. Add an expense: Let users add an expense from 9 categories: bills, entertainment, food, health, housing, transport, toiletry, clothes and miscellaneous. 
    Emilia
    After the users click on “Add expense” button, this activity is presented.
    An edit text is applied for user’s input.
    9 categories are allocated to 9 buttons, which allows users to insert their expense into project’s database. 

    4. Add an income: Let users add an income from 3 categories: salary, investment and saving. 
    Emilia
    The similar idea to activity 3.
    After the users click on “Add income” button, this activity is presented.
    An edit text is applied for user’s input.
    3 categories are allocated to 3 buttons, which allows users to insert their income into project’s database. 

    5. Generate a detail report: show the difference between your expenses and incomes.
    Emilia 
    In this activity, 2 arrays are used to hold expense and income from user’s input. 
    A recycler view is developed to display the expense and income list dynamically. In addition, a nested scroll view allows users to scroll to the end of the list.
    A SQLite database is also designed store all data. 
    Consists of 2 table: expenses and incomes, each table saves the record date, the amount of money spend or earn and what categories it belongs to.

    6. Modify record: Allow users to edit the latest record and delete no longer used record. 
    Emilia
    When users randomly click on category column, a new activity is invoked for them to update and delete data. 
    A date picker is employed in the activity so users can modify the date up to their wish. 
    An edit text below also allows them to update the amount of money they want. 
    After they complete update and delete buttons, the database is updated accordingly. 

    7. Budget mode: User set a budget for each month, when the expenses reaching near the specified budget, user get a warning. 
    Dung Do
    A pie chart library is employed aesthetically to display user’s input after they complete their activity on button: “Add Expense”.
    A text view is also used to demonstrate the Budget after users input their expense. If total expense is higher budget, a message “You have exceeded your budget by $1690” is displayed. Otherwise, it will be “You have $30 left to spend”.
    When users click on Details button, a report in activity 5 is shown up.
    The future fragment includes activity 3,5,6.
 
    8. Set recurring expenses: Users can set a recurring expense that happens weekly, monthly.
    Emilia
    Group of radio buttons allows users to choose their interval for recurring expense that repeats weekly or monthly. 
    After choosing interval, users can select their categories and input the amount of money. A list of recurring expense will be stored into database.

    9. Export their report into a csv file: User can export their expense and income into a csv file.
    Dung Do
    2 buttons are employed: “Export Expense” and “Expor Income”.
    When users click on those buttons, a csv file is exported to the “File” folder in android devices. 


