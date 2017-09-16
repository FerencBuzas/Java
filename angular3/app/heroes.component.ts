import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Hero } from './hero';
import { HeroService } from './hero.service';

@Component({
  moduleId: module.id,
  selector: 'my-heroes',
  templateUrl: 'heroes.component.html', 
  styleUrls: [ 'heroes.component.css' ]
})

export class HeroesComponent implements OnInit {

  heroes = []
  selectedHero: Hero;

  constructor(private heroService: HeroService,
              private router: Router ) { 
  }

  getHeroes(): void {   // a future is involved
    this.heroService.getHeroes().then(h => this.heroes = h);
  }

  ngOnInit(): void {    // The constructor must be short and fast
    this.getHeroes();
  }

  onSelect(hero: Hero): void {
    this.selectedHero = hero;
  }

  gotoDetail(): void {
    this.router.navigate(['/detail', this.selectedHero.id]);
  }
}
