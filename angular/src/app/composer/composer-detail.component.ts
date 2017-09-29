import { Component,  OnInit }     from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Location }               from '@angular/common';

import { Composer } from './composer';
import { ComposerService } from './composer.service';
import { MusicLogger } from '../util/music-logger';
import { MusicUtil } from '../util/music-util';

@Component({
  moduleId: module.id,
  selector: 'my-composer-detail',
  templateUrl: 'composer-detail.component.html',
  styleUrls: [ 'composer-detail.component.css' ]
})

export class ComposerDetailComponent implements OnInit {

    composer: Composer;
    oriComposer: Composer;

  constructor(
    private composerService: ComposerService,
    private route: ActivatedRoute,
    private location: Location,
    private logger: MusicLogger) {}

  ngOnInit(): void {
    this.route.params.forEach((params: Params) => {
      let id = +params['id'];       // '+' operator: converts string to number
      if (id !== 0) {
        this.composerService.getComposer(id)
          .then(composer => {
            this.composer = composer;
            this.oriComposer = MusicUtil.deepCopy(this.composer);
          });
      }
      else {
        this.composer = new Composer();
        this.oriComposer = MusicUtil.deepCopy(this.composer);
      }
    });
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    this.logger.info('TODO: save ##');
    this.composerService.deleteComposer(this.composer.id.valueOf());
    this.location.back();
  }

  delete(): void {
    this.logger.info('TODO: delete ##');
    this.location.back();
  }

  isDirty(): Boolean {
    return ! this.composer.equals(this.oriComposer);
  }

  canDelete(): Boolean {
    return this.composer.id !== 0 && !this.isDirty();
  }
}
