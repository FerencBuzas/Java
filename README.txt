JpaRestAngular

Server side: REST service, persistence to Hibernate / H2.
Client: Angular.

Usage:
  Run service/.../music/app/MusicServiceApp
  browser: http://localhost:8080/music - this gives help.

Modules

  common: classes, which are used in the backend and also in the Java client.

    Composer:   id, title,  birth
    Publisher:  id, title
    Book:       id, title, composer, publisher, pubDate

  client-angularjs

    TODO: The client runs in a browser, with Angular-js

  client-java

    The client is a standalone application.
    It writes the contents of the containers (Composers, Publishers, Books) to System.out.

  service

    Contains a REST service, which retrieves Composers, Publishers, Books.
    It can return hardcoded values, or TODO: use JPA or Spring Data.
