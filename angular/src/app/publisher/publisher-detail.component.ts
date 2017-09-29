import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Params }   from '@angular/router';
import { Location }                 from '@angular/common';

import { Publisher } from './publisher';
import { PublisherService } from './publisher.service';

@Component({
  moduleId: module.id,
  selector: 'my-publisher-detail',
  templateUrl: 'publisher-detail.component.html',
  styleUrls: [ 'publisher-detail.component.css' ]
})
export class PublisherDetailComponent implements OnInit{

    publisher: Publisher;
    oriPublisher: Publisher;

  constructor(
    private publisherService: PublisherService,
    private route: ActivatedRoute,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.route.params.forEach((params: Params) => {
      let id = +params['id'];       // '+' operator: converts string to number
      if (id !== 0) {
        this.publisherService.getPublisher(id)
        .then(publisher => {
            this.publisher = publisher;
            this.oriPublisher = { id: this.publisher.id, name: this.publisher.name }
        });
      }
      else {
        this.publisher = { id: 0, name: '' };
        this.oriPublisher = { id: 0, name: '' };
      }
    });
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    console.log('TODO: save ##');
    this.location.back();
  }

  delete(): void {
    console.log('TODO: delete ##');
    this.location.back();
  }

  isDirty(): Boolean {
    return this.publisher.name !== this.oriPublisher.name;
  }

  canDelete(): Boolean {
    return this.publisher.id !== 0 && this.publisher.name === this.oriPublisher.name;
  }
}
