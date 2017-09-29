import { Component,  OnInit }       from '@angular/core';
import { ActivatedRoute, Params }   from '@angular/router';
import { Location }                 from '@angular/common';

import { Book } from './book';
import { BookService } from './book.service';
import { MusicLogger } from '../util/music-logger';
import { MusicUtil } from '../util/music-util';

@Component({
  moduleId: module.id,
  selector: 'my-book-detail',
  templateUrl: 'book-detail.component.html',
  styleUrls: [ 'book-detail.component.css' ]
})
export class BookDetailComponent implements OnInit{

    book: Book;
    oriBook: Book;

  constructor(
    private bookService: BookService,
    private route: ActivatedRoute,
    private location: Location,
    private logger: MusicLogger
  ) {}

  ngOnInit(): void {
    this.route.params.forEach((params: Params) => {
      let id = +params['id'];       // '+' operator: converts string to number
      if (id !== 0) {
          this.bookService.getBook(id)
            .then(book => {
              this.book = book;
              this.oriBook = MusicUtil.deepCopy(this.book);
            });
      }
      else {
          this.book = new Book();
          this.oriBook = MusicUtil.deepCopy(this.book);
      }
    });
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    console.log('TODO: save ##');
    this.location.back();
  }

  delete(): void {
    this.logger.info('delete() book, title="' +this.book.title+ '" id=' +this.book.id);
    this.bookService.deleteBook(this.book.id.valueOf());
  }

  addChopin(): void {
    this.logger.info('addChopin()');
    this.bookService.addBook('Ballada', 'Chopin', 'Peters', 2017);
  }

  isDirty(): Boolean {
    return ! this.book.equals(this.oriBook);
  }

  canDelete(): Boolean {
    return this.book.id !== 0 && !this.isDirty();
  }
}
