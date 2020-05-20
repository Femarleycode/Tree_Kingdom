# Hobby Web Application - Tree_Kingdom

My second individual project with QA as an academy trainee.

This application is for tree enthusiasts who want to track which trees that they've spotted. It was built to demonstrate my understanding of Sprign and connecting to a working back-end along with front-end through API calls.

##Table of contents 1. 2. 3. 4. 5. 6.

## About this project

The minimum viable product: A working front end (with working API calls) which connect to a Java back-end with a relational database.

See project documents for more information.

## Project status

Current release: v.0.1.0 - in development

Test coverage:

## Getting started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Dependencies

What things you need to install the software and where to find them.

**To Run**

```
Java SE 8 (or later) to run the jar file.
Maven to create the jar-file and run.

If my GCP instance is no longer live then you will need;

A relational database to configure the application to.
(a GCP instance is recommended as it doesn't require much application configuration. MySQL and h2 can also work however.)

For the front end any modern browser can be used.

Command line or git bash to run the jar file.

```

**To Develop**

Most IDEs will suggest dependencies/libraries based on the pom.xml file.

```
I used IntelliJ was my main IDE for this project.
API calls were tested using Postman and then written in JavaScript
The CI pipeline for this project was Jenkins
```

**Links for Dependencies**

- [Java](https://www.oracle.com/java/technologies/javase-downloads.html#JDK14)
- [Maven](https://maven.apache.org/)
- [Git & Git Bash](https://git-scm.com/downloads)
- [mySQL](https://dev.mysql.com/downloads/installer/)
- [Jenkins](https://jenkins.io/download/)
- [Postman](https://www.postman.com/downloads/)
- [IntelliJ IDE](https://www.jetbrains.com/idea/download/#section=windows)

### Getting the Source

This project is [hosted on GitHub](https://github.com/https://github.com/Femarleycode/Tree_Kingdom). You can clone this project directly using this command:

```
git clone https://github.com/Femarleycode/Tree_Kingdom.git
```

### Prerequisites

A java runtime environment is required on your PC to run this along with a version of Maven to build the project. Having Eclipse would also be useful if you wish to edit or test the code, though other Java IDE's will work fine.

### Installing

Fork this repository to your own GitHub and then clone it to your PC. From there, the .jar file can be created using the command line.
Open your command line in the main directory of the project and run the following commands to get the program working, using Maven and Java. **Run each command one line at a time!**
'''
mvn clean package
cd target
java -jar FelixMarley-SoftwareMarch16-HobbyProject.jar
'''

## Running the tests

Running the tests can be done by opening the source code in an IDE and running the test codes as a JUnit application. Alternatively, opening your command line in the main directory and running the tests via maven will also work, with the command given below.
'''
mvn clean test
'''

### Coding style tests

Building this application and pushing it forward to a computer or virtual machine hosting Sonarqube will allow the user to anylise the code for coding style issues, bugs and security issues.

## Deployment

The project has been deployed via multiple stages of a CI pipeline. It is first pushed to GitHub, before being retrieved by Jenkins. Jenkins utilises maven to run the unit tests and build the project before pushing it further ahead to both Sonarqube (for more testing) and Nexus, for artifact repositing and hosting the fully built application.

## Built With

- [Maven](https://maven.apache.org/) - Dependency Management
- [Jenkins](https://jenkins.io/) - CI Pipeline scripter
- [Eclipse](https://www.eclipse.org/downloads/) - IDE
- [SonarQube](https://www.sonarqube.org/) - static analysis
- [Nexus](https://www.sonatype.com/product-nexus-repository/) - artifact repository
- [Trello](https://trello.com/) - planning, kanban
- [MySQL](https://www.mysql.com/) - database language
- [GCP](https://cloud.google.com/) - database host
- [JUnit](https://junit.org/junit5/) - Testing (see pom.xml)
- [Mockito](https://site.mockito.org/) - Testing (see pom.xml)

## Versioning

- [Git](https://git-scm.com/) - version control system
- [Github](http://github.com) - host of version control system

## Authors

**Felix Marley** - _Author_ - [Femarleycode](https://github.com/Femarleycode)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details

_For help in [Choosing a license](https://choosealicense.com/)_

## Acknowledgments

**Tadas Vaidotas** - _Spring and Solenium trainer, Provided a basis for the project_ - [tvaidotas](https://github.com/tvaidotas), the basis can be found [here](https://github.com/tvaidotas/SpringNotes)

**Jordan Harrison** - _Java trainer, support and also provided a basis for the project_ - [JHarry444](https://github.com/JHarry444), the basis can be found [here](https://github.com/JHarry444/SpringDucks)

**Nicholas Johnson** - _Software trainer and project management support_ - [nickstewarttds](https://github.com/nickrstewarttds)

**Christian Redfern** - _Support with Java, JS, HTML and APIs_ - [Chrisctr](https://github.com/Chrisctr)

**Caroline Strasenburgh** - _Java support, guidance and planning support_ - [CarolineS-QA](https://github.com/CarolineS-QA)

**David Williams** - _Java and Spring support_ - [DavidWilliamsQA](https://github.com/DavidWilliamsQA)

# Inventory Management System
