//
// Hardcoded books instead of a service.
//

import { Book } from './book';
import { Composer } from '../composer/composer';
import { Publisher } from '../publisher/publisher';

export const Bach = new Composer(1, 'Bach', 1685);
export const Beethoven = new Composer(4, 'Beethoveh', 1770);

export const Peters = new Publisher(3, 'Peters');
export const EMB = new Publisher(2,	'EMB');

export const BOOKS: Book[] = [
    new Book(0, 'WTC I', Bach, Peters, 1998),
    new Book(0, 'WTC II', Bach, Peters, 1998),
    new Book(0, 'Son I', Beethoven, EMB, 1992) ];
