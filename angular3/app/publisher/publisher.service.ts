import { Injectable } from '@angular/core';

import { Publisher } from './publisher';
import { PUBLISHERS } from './mock-publishers';

@Injectable()
export class PublisherService {
    getPublishers(): Promise<Publisher[]> {
        return Promise.resolve(PUBLISHERS);
    }

    // Simulate a slow connecton
    getPublishersSlowly(): Promise<Publisher[]> {
        return new Promise<Publisher[]>(
            resolve => setTimeout(resolve, 2000)) // delay 2 seconds
            .then(() => this.getPublishers());
    }

    getPublisher(id: number): Promise<Publisher> {
      return this.getPublishers()
        .then(publishers => publishers.find(publisher => publisher.id === id));
    }
}
