import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Composer } from './composer';
import { ComposerService } from './composer.service';

@Component({
  moduleId: module.id,
  selector: 'my-composers',
  templateUrl: 'composers.component.html',
  styleUrls: [ 'composers.component.css' ]
})

export class ComposersComponent implements OnInit {

  composers = [];
  selectedComposer: Composer;

  constructor(private composerService: ComposerService,
              private router: Router ) {
  }

  // When the service sends the composers, store them to member.
//  getComposers(): void {   // a future is involved
//    this.composerService.getComposers().then(h => this.composers = h);
//  }
  getComposers(): void {   // a future is involved
    console.log("## CompComp.getComposers()");
    this.composerService.getComposers().then(
      h => { this.composers = h;
             console.log("  CompComp.getComposers: h=" + h);
      });
  }

  // The constructor must be short and fast, so loading is here.
  ngOnInit(): void {
    this.getComposers();
  }

  // When the user selects a composer, store its references to member.
  onSelect(composer: Composer): void {
    this.selectedComposer = composer;
    this.router.navigate(['/composerDetail', this.selectedComposer.id]);
  }

  newComp(): void {
    console.log('newComp()');
    this.selectedComposer = null;
    this.router.navigate(['/composerDetail', 0]);
  }
}
