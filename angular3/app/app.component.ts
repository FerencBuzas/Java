import { Component } from '@angular/core';

@Component({
  selector: 'my-app',
  template: `
   <h1>{{title}}</h1>
   <nav>
     <a routerLink="/dashboard" routerLinkActive="active">Dashboard</a>
     <a routerLink="/books" routerLinkActive="active">Books</a>
     <a routerLink="/composers" routerLinkActive="active">Composers</a>
     <a routerLink="/publishers" routerLinkActive="active">Publishers</a>
   </nav>
   <router-outlet></router-outlet>
  `,
  styleUrls: [ 'app/app.component.css' ]
})

export class AppComponent {
  title = 'Sheet Music';
}