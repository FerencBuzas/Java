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

  composers = []
  selectedComposer: Composer;

  constructor(private composerService: ComposerService,
              private router: Router ) { 
  }

  getComposers(): void {   // a future is involved
    this.composerService.getComposers().then(h => this.composers = h);
  }

  ngOnInit(): void {    // The constructor must be short and fast
    this.getComposers();
  }

  onSelect(composer: Composer): void {
    this.selectedComposer = composer;
  }

  gotoDetail(): void {
    this.router.navigate(['/composerDetail', this.selectedComposer.id]);
  }
}
