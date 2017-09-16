import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Params }   from '@angular/router';
import { Location }                 from '@angular/common';

import { Composer } from './composer';
import { ComposerService } from './composer.service';

@Component({
  moduleId: module.id,
  selector: 'my-composer-detail',
  templateUrl: 'composer-detail.component.html',
  styleUrls: [ 'composer-detail.component.css' ]
})

export class ComposerDetailComponent implements OnInit{
    //@Input()
    composer: Composer;
  constructor(
    private composerService: ComposerService,
    private route: ActivatedRoute,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.route.params.forEach((params: Params) => {
      let id = +params['id'];       // '+' operator: converts string to number
      this.composerService.getComposer(id)
        .then(composer => this.composer = composer);
    });
  }

  goBack(): void {
    this.location.back();
  }
}
