JpaRestAngular

Simple demo program which uses REST service and several clients.

Author: Ferenc Buzas
Starting date: 13 Sep 2017

Server side: REST service
Client:      Java app / or Angular / or just browse URLs.

Usage:
  Run service/.../music/app/MusicServiceApp
  browser: http://localhost:8080/music - this gives help.

------------------------------------------------------------------
Modules

  common: classes, which are used in the backend and also in the Java client.

    Composer:   id, title,  birth
    Publisher:  id, title
    Book:       id, title, composer, publisher, pubDate

  angular-client
    The client runs in a browser, with Angular-js.
    ** Actually, the working version is not here, but in angular3/. **

    git clone https://github.com/angular/quickstart.git angular-client
    cd angular-client
    npm install
    npm start   # should start a browser tab with "Hello Angular!"
    # deleted non-essential files (listed in .txt)
    Then used as a guide: https://angular.io/tutorial/toh-pt1

  angular2
    A failure, to be deleted.

  angular3
    This is the actully working version.
    Started with copying ~/develop/js/angular-tour-of-heroes.

  client-java

    The client is a standalone application.
    It writes the contents of the containers (Composers, Publishers, Books) to System.out.

  service

    Contains a REST service, which retrieves Composers, Publishers, Books.
    It can return hardcoded values, or TODO: use JPA or Spring Data.
