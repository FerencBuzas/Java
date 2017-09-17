import { Book } from './book';

export const BOOKS: Book[] = [
  { id: 0, title: 'WTC I',
     composer: {id:	1,
                name:	"Bach",
                birthYear:	1685},
     publisher: { id:	3,
                  name:	"Peters",
                  pubYear:	1998},
  },
  { id: 1, title: 'WTC II',
     composer: {id:	1,
                  name:	"Bach",
                  birthYear:	1685},
     publisher: { id:	3,
                  name:	"Peters",
                  pubYear:	1998},
  },
  { id: 2, title: 'Son I',
     composer: {id:	4,
                name:	"Beethoven",
                birthYear:	1770 },
     publisher: { id:	2,
                  name:	"EMB",
                  pubYear:	1992 }
  }
];
