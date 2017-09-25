import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Book } from './book';
import { BookService } from './book.service';

@Component({
  moduleId: module.id,
  selector: 'my-books',
  templateUrl: 'books.component.html',
  styleUrls: [ 'books.component.css' ]
})

export class BooksComponent implements OnInit {

  books = []
  selectedBook: Book;

  constructor(private bookService: BookService,
              private router: Router ) { 
  }

  getBooks(): void {   // a future is involved
    this.bookService.getBooks().then(h => this.books = h);
  }

  ngOnInit(): void {    // The constructor must be short and fast
    this.getBooks();
  }

  onSelect(book: Book): void {
    this.selectedBook = book;
  }

  gotoDetail(): void {
    this.router.navigate(['/bookDetail', this.selectedBook.id]);
  }
}
