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
    //@Input()
    publisher: Publisher;
  constructor(
    private publisherService: PublisherService,
    private route: ActivatedRoute,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.route.params.forEach((params: Params) => {
      let id = +params['id'];       // '+' operator: converts string to number
      this.publisherService.getPublisher(id)
        .then(publisher => this.publisher = publisher);
    });
  }

  goBack(): void {
    this.location.back();
  }
}
