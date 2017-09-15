import { Component } from '@angular/core';

export class Hero {
  id: number;
  name: string;
}

const HEROES: Hero[] = [
    { id: 11, name: 'Mr. Nice' },
    { id: 12, name: 'Narco' },
    { id: 13, name: 'Bombasto' }
];


@Component({
  selector: 'my-app',
  template: `<h1>{{title}}</h1>
             Written by Ferenc Buz?s, 14 Sep 2017

             <h2>My Heroes</h2>
             <ul class="heroes">
               <li *ngFor="let hero of heroes">
                 <span class="badge">{{hero.id}}</span> {{hero.name}}
               </li>
             </ul>
  `
})


export class AppComponent  {
  title = 'Sheet Music Store';

    heroes = HEROES;
}
