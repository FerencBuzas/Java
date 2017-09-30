import { Component, OnInit }      from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Location }               from '@angular/common';

import { Publisher } from './publisher';
import { PublisherService } from './publisher.service';
import { MusicLogger } from '../util/music-logger';

@Component({
  moduleId: module.id,
  selector: 'my-publisher-detail',
  templateUrl: 'publisher-detail.component.html',
  styleUrls: [ 'publisher-detail.component.css' ]
})
export class PublisherDetailComponent implements OnInit {

    publisher: Publisher;
    oriPublisher: Publisher;

  constructor(
    private publisherService: PublisherService,
    private route: ActivatedRoute,
    private location: Location,
    private logger: MusicLogger) {}

  ngOnInit(): void {
    this.route.params.forEach((params: Params) => {
      let id = +params['id'];       // '+' operator: converts string to number
      if (id !== 0) {
        this.publisherService.getPublisher(id)
          .then(publisher => {
            this.publisher = publisher;
            this.oriPublisher = Publisher.deepCopy(this.publisher);
        });
      }
      else {
        this.publisher = new Publisher();
        this.oriPublisher = Publisher.deepCopy(this.publisher);
      }
    });
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    this.logger.info('PublisherDetailC..save()');
    this.publisherService.storePublisher(this.publisher.id, this.publisher.name);
    this.location.back();
  }

  delete(): void {
    this.logger.info('PublisherDetailC..delete()');
    this.publisherService.deletePublisher(this.publisher.id.valueOf());
    this.location.back();
  }

  isDirty(): Boolean {
    return this.publisher && ! this.publisher.equals(this.oriPublisher);
  }

  canDelete(): Boolean {
    return this.publisher.id !== 0 && !this.isDirty();
  }
}
