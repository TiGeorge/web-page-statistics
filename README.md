## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)

## General info
This project is a simple Java application for counting words in a web page.
	
## Technologies
Project created with:
* Jsoup
* Hibernate
* H2 database
* Junit 4
* Java Logger
* MVC and DAO patterns
	
## Setup
To run this project use command line:

```
$  java -jar web-page-statistics-1.0-SNAPSHOT-jar-with-dependencies.jar
```

## User manual
After starting the application, the following message will be displayed in the console:
"Enter web page URL or "exit" for exit:". The user can enter the correct URL.
If the URL is incorrect, the program will make another request. 
If the URL is correct, the program will display statistics on the number of words on the web page.