//
// Hardcoded books instead of a service.
//

import { Book } from './book';
import { Composer } from '../composer/composer';
import { Publisher } from '../publisher/publisher';

export const Bach: Composer = {
    id:	1,
    name:	"Bach",
    birthYear:	1685
};

export const Beethoven: Composer = {
    id:	4,
    name:	"Beethoven",
    birthYear:	1770
};

export const Peters: Publisher = {
    id:	3,
    name:	"Peters"
};

export const EMB: Publisher = {
    id:	2,
    name:	"EMB"
};

export const BOOKS: Book[] = [
  { id: 0, title: 'WTC I',
     composer: Bach,
     publisher: Peters,
     pubYear:	1998
  },
  { id: 1, title: 'WTC II',
     composer: Bach,
     publisher: Peters,
     pubYear:	1998
  },
  { id: 2, title: 'Son I',
     composer: Beethoven,
     publisher: EMB,
     pubYear:	1992
  }
];
