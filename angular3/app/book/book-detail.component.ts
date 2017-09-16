import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Params }   from '@angular/router';
import { Location }                 from '@angular/common';

import { Book } from './book';
import { BookService } from './book.service';

@Component({
  moduleId: module.id,
  selector: 'my-book-detail',
  templateUrl: 'book-detail.component.html',
  styleUrls: [ 'book-detail.component.css' ]
})

export class BookDetailComponent implements OnInit{
    //@Input()
    book: Book;
  constructor(
    private bookService: BookService,
    private route: ActivatedRoute,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.route.params.forEach((params: Params) => {
      let id = +params['id'];       // '+' operator: converts string to number
      this.bookService.getBook(id)
        .then(book => this.book = book);
    });
  }

  goBack(): void {
    this.location.back();
  }
}
