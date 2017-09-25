# JpaRestAngular v1.1

A simple demo program which uses JPA, RESTful service and Angular client.

Author: Ferenc Buzas  
Starting date: 13 Sep 2017  
v1.1:          21 Sep 2017

Server side: Spring REST service, with either JPA or hardcoded data.    
Clients:     Angular / or just browse URLs / or a Java app. 

# Usage:
After downloading and building the components:

  - start the service: run service/.../music/app/MusicServiceApp  
    (it uses embedded Tomcat, needs port 8080)  
  - use with angular: cd angular && npm start
      (NOTE: node_modules/ is not included, so there may be problems with running it,
        due to version conflicts between node, npm, and the libraries)
  - use by URLs: http://localhost:8080/music - gives a simple page with links

# About the Project
## The Topic
I have a collection of sheet music (since I like playing the piano as an amateur).

This system is an inventory of books, composers, publishers.

## The Aim
To demonstrate the cooperation of JPA, RESTful service, Angular.

## Technical details
### Modules

#### common
  classes, which are used in the backend and also in the Java client.

    Composer:   id, title,  birth  
    Publisher:  id, title  
    Book:       id, title, composer, publisher, pubDate

#### angular

    The client runs in a browser, with Angular-js.

    git clone https://github.com/angular/quickstar.git angular    
    cd angular  
    npm install  
    npm start   # should start a browser tab with "Hello Angular!"  
    Then used as a guide: https://angular.io/tutorial/toh-pt1  
    Started with copying ~/develop/js/angular-tour-of-heroes.

#### client-java

    The client is a standalone application.  
    It writes the contents of the containers (Composers, Publishers, Books) to System.out.

#### service

    Contains a RESTful service, which retrieves Composers, Publishers, Books.  
    It uses JPA w/ Hibernate and H2, or it can return hardcoded values.

# TODO

  - Do something with node_modules/ (either put in repo or solve peer problem)
  - Create a correct way of selecting either service or mock data in angular/
  - Create a correct way of selecting either JPA or mock data in service/.
  - Add error handling
  - Add component tests to the Angular client
  - Add an end-to-end test to the Angular client   
  - Add Continuous Integration
  - Add new functionality to add/modify/delete elements
  - Add scrolling in tables
  - Add searching/filtering by composer, publisher
  - Deployment
