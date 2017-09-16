import { Component }          from '@angular/core';

@Component({

  selector: 'my-app',  // Must match index.html!

  template: `
    <h1>{{title}}</h1>
    <nav>
      <a routerLink="/dashboard" routerLinkActive="active">Dashboard</a>
      <a routerLink="/composers" routerLinkActive="active">Composers</a>
    </nav>
    <router-outlet></router-outlet>
  `,
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Sheet Music';
}
