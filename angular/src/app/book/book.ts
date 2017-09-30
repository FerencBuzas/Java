//
// Holds the data of one from.
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

    // Multiple small blocks, to be able to set breakponits
    if (this.id !== other.id) {
        return false;
    }
    if (this.title !== other.title) {
        return false;
    }
    if ( ! this.composer.equals(other.composer)) {
        return false;
    }
    if ( ! this.publisher.equals(other.publisher)) {
        return false;
    }
    if (this.pubYear !== other.pubYear) {
        return false;
    }

    return true;
  }

  // Deep copy
  static deepCopy(from: Book) : Book {
    
          let comp = from.composer;
          let pub = from.publisher;
          return new Book(from.id, from.title,
              new Composer(comp.id, comp.name, comp.birthYear),
              new Publisher(pub.id, pub.name),
              from.pubYear);
      }
    
    }
