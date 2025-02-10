# Website-Testing-Automation-Projects
Using Selenium as a testing tool and Java as the Programing language 

## 1. Find Hotel Availability

Case Study: Find Hotel Availability

Problem Statement:  

Hotel Availability Automation

1) Availability of hotel rooms in next week (e.g. 27 July Check in and 28 July Check out) for a specific city. Ex: Mumbai

2) Get the Price of All the Hotels

3) Sort the hotels by rating

4) Check if the first 5 hotels belongs to the specific city searched for. Ex: Mumbai

Used website: https://www.trivago.in/

Detailed Description:

Open the application https://www.trivago.in/ in chrome or fire fox browser.
In search field, enter City “Mumbai” as destination.
Select Check-In Date for the next week (e.g. 27 July)
Select Check-Out Date for the next week (e.g.28 July)
Select Adults 1
Select Rooms 1
Click on Apply.
Click on Search.
Select Sort By “Rating only”
Get the list of Rent of the hotels displayed.
Check that the all the ratings of the hotels is in descending order (e.g. 10.0>9.8>9.6..)
Close the browser
Key Automation Scope:

Multiple browser handling
Assign synchronization technique
Use Calendar or Date-Picker
Navigation to Home page
Exception Handling
Locating Elements

## 2. Identify Courses
Problem Statement : Identify Courses

Search and display all web development courses 
1. Should be for beginners level.
2. Courses offered in English language
3. Display first two courses with name, total learning hours and rating.
(Suggested Site: coursera.org however  you are free to choose any other legitimate  site)
Detailed Description: Hackath Ideas

1. Search for web development courses for Beginners level & English Language and extract the course names, total learning hours & rating for first 2 courses
2. Look for Language Learning; Extract all the languages and different levels with its total count & display them
3. In Home page, go to "For Enterprise"; Look into Courses for Campus under Product; Fill the  "Ready to transform" form with any one input invalid (example: email); Capture the error message & display
(Suggested Site: coursera.org however  you are free to choose any other legitimate  site)
Key Automation Scope

Handling different browser windows, search option
Extract multiple drop down list items & store in collections
Navigating back to home page
Filling form (in different objects in web page)
Capture warning message
Scrolling down in web page
