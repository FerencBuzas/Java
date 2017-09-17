//
// Holds the data of one book.
//

import { Composer } from '../composer/composer';
import { Publisher } from '../publisher/publisher';

export class Book {
  id: number;
  title: string;
  composer: Composer;
  publisher: Publisher;
  pubYear: number;
}