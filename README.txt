JpaRestAngular v1.1

Simple demo program which uses REST service and several clients.

Author: Ferenc Buzas
Starting date: 13 Sep 2017
v1.1:          21 Sep 2017

Server side: Spring REST service,  with JPA / or hardcoded data.
Client:      Spring Java app / or Angular / or just browse URLs.

Usage:
  Run service/.../music/app/MusicServiceApp
  browser: http://localhost:8080/music - this gives help about calling

------------------------------------------------------------------
Modules

  common: classes, which are used in the backend and also in the Java client.

    Composer:   id, title,  birth
    Publisher:  id, title
    Book:       id, title, composer, publisher, pubDate

  angular3
    The client runs in a browser, with Angular-js.

    git clone https://github.com/angular/quickstart.git angular-client
    cd angular-client
    npm install
    npm start   # should start a browser tab with "Hello Angular!"
    Then used as a guide: https://angular.io/tutorial/toh-pt1
    Started with copying ~/develop/js/angular-tour-of-heroes.

  client-java

    The client is a standalone application.
    It writes the contents of the containers (Composers, Publishers, Books) to System.out.

  service

    Contains a REST service, which retrieves Composers, Publishers, Books.
    It uses JPA, or it can return hardcoded values.
