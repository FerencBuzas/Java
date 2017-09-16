import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Publisher } from './publisher';
import { PublisherService } from './publisher.service';

@Component({
  moduleId: module.id,
  selector: 'my-publishers',
  templateUrl: 'publishers.component.html',
  styleUrls: [ 'publishers.component.css' ]
})

export class PublishersComponent implements OnInit {

  publishers = []
  selectedPublisher: Publisher;

  constructor(private publisherService: PublisherService,
              private router: Router ) { 
  }

  getPublishers(): void {   // a future is involved
    this.publisherService.getPublishers().then(h => this.publishers = h);
  }

  ngOnInit(): void {    // The constructor must be short and fast
    this.getPublishers();
  }

  onSelect(publisher: Publisher): void {
    this.selectedPublisher = publisher;
  }

  gotoDetail(): void {
    this.router.navigate(['/detail', this.selectedPublisher.id]);
  }
}
