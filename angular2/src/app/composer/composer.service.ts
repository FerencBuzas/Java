import { Injectable } from '@angular/core';

import { Composer } from './composer';
import { COMPOSERS } from './mock-composers';

@Injectable()
export class ComposerService {
    getComposers(): Promise<Composer[]> {
        return Promise.resolve(COMPOSERS);
    }

    // Simulate a slow connecton
    getComposersSlowly(): Promise<Composer[]> {
        return new Promise<Composer[]>(
            resolve => setTimeout(resolve, 2000)) // delay 2 seconds
            .then(() => this.getComposers());
    }

    getComposer(id: number): Promise<Composer> {
      return this.getComposers()
        .then(composers => composers.find(composer => composer.id === id));
    }
}
