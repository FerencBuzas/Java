//
// Holds the data of one book.
//

import { Composer } from '../composer/composer';
import { Publisher } from '../publisher/publisher';

export class Book {

  constructor(
    public id: Number = 0,
    public title: String = '',
    public composer: Composer = null,
    public publisher: Publisher = null,
    public pubYear: Number = 1970
  ) {}

  equals(other: Book): boolean {

    return this.id === other.id
      && this.title === other.title
      && this.composer === other.composer
      && this.publisher === other.publisher
      && this.pubYear === other.pubYear;
  }
}
