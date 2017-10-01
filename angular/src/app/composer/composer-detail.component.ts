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
    errorMessage: string;

  constructor(
    private composerService: ComposerService,
    private route: ActivatedRoute,
    private location: Location,
    private logger: MusicLogger,
    private musicUtil: MusicUtil ) {}

  ngOnInit(): void {
    this.route.params.forEach((params: Params) => {
      let id = +params['id'];       // '+' operator: converts string to number
      if (id !== 0) {
        this.composerService.getComposer(id)
          .then(composer => {
            this.composer = composer;
            this.oriComposer = Composer.deepCopy(this.composer);
          });
      }
      else {
        this.composer = new Composer();
        this.oriComposer = Composer.deepCopy(this.composer);
      }
    });
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    this.logger.info("CompDet.save()");
    this.composerService.storeComposer(
        this.composer.id,
        this.composer.name,
        this.composer.birthYear.valueOf());
    this.location.back();
  }

  delete(): void {
    this.logger.info("CompDet.delete()");
    if ( ! this.musicUtil.confirm("Sure you want to delete " + this.composer.name + "?")) {
        this.errorMessage = "Not deleted.";
        return;
    }
    this.composerService.deleteComposer(this.composer.id.valueOf());
    this.location.back();
  }

  isDirty(): Boolean {
    return ! this.composer.equals(this.oriComposer);
  }

  canDelete(): Boolean {
    return this.composer.id !== 0 && !this.isDirty();
  }
}
