import { Component, OnInit } from '@angular/core';

import { Composer } from './composer/composer';
import { ComposerService } from './composer/composer.service';

@Component({
  moduleId: module.id,
  selector: 'my-dashboard',
  templateUrl: 'dashboard.component.html',
  styleUrls: [ 'dashboard.component.css' ]
})
export class DashboardComponent implements OnInit {

  composers: Composer[] = [];

  constructor(private composerService: ComposerService) {   // inject to private member
  }

  ngOnInit(): void {
    this.composerService.getComposers()
      .then(composers => this.composers = composers.slice(1, 5));
  }
}
