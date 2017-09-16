import { Injectable } from '@angular/core';

import { Book } from './book';
import { BOOKS } from './mock-books';

@Injectable()
export class BookService {
    getBooks(): Promise<Book[]> {
        return Promise.resolve(BOOKS);
    }

    // Simulate a slow connecton
    getBooksSlowly(): Promise<Book[]> {
        return new Promise<Book[]>(
            resolve => setTimeout(resolve, 2000)) // delay 2 seconds
            .then(() => this.getBooks());
    }

    getBook(id: number): Promise<Book> {
      return this.getBooks()
        .then(books => books.find(book => book.id === id));
    }
}
