import { Component,  OnInit }       from '@angular/core';
import { ActivatedRoute, Params }   from '@angular/router';
import { Location }                 from '@angular/common';

import { Book } from './book';
import { BookService } from './book.service';
import { Composer } from '../composer/composer';
import { ComposerService } from '../composer/composer.service';
import { Publisher } from '../publisher/publisher';
import { PublisherService } from '../publisher/publisher.service';
import { MusicLogger } from '../util/music-logger';
import { ComboBoxComponent } from 'ng2-combobox';


@Component({
  moduleId: module.id,
  selector: 'my-book-detail',
  templateUrl: 'book-detail.component.html',
  styleUrls: [ 'book-detail.component.css' ]
})
export class BookDetailComponent implements OnInit{

    book: Book;
    oriBook: Book;
    statusLine = '';
    composers: Composer[];
//     = [
//      new Composer(1, 'Bach', 1685 ),
//      new Composer(2, 'Haydn', 1732 ),
//      new Composer(3, 'Mozart', 1756),
//      new Composer(4, 'Beethoven', 1770),
//      new Composer(5, 'Schubert', 1797)];
    selectedComposer: Composer;
    publishers: Publisher[] = [];
    selectedPublisher: Publisher;

  constructor(
    private bookService: BookService,
    private route: ActivatedRoute,
    private location: Location,
    private logger: MusicLogger,
    private composerService: ComposerService,
    private publisherService: PublisherService
  ) {}

  ngOnInit(): void {
    this.composerService.getComposers().then(c => this.composers = c);
    this.publisherService.getPublishers().then(p => this.publishers = p);

    this.route.params.forEach((params: Params) => {
      let id = +params['id'];       // '+' operator: converts string to number
      if (id !== 0) {
          this.bookService.getBook(id)
            .then(book => {
              this.book = book;
              this.oriBook = Book.deepCopy(this.book);
            });
      }
      else {
          this.book = new Book();
          this.oriBook = Book.deepCopy(this.book);
      }
    });
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    this.bookService.storeBook(this.book.id.valueOf(), this.book.title,
        this.book.composer,
        this.book.publisher,
        this.book.pubYear.valueOf())
    this.location.back();
  }

  delete(): void {
    this.logger.info('delete() book, title="' + this.book.title + '" id=' + this.book.id);
    this.bookService.deleteBook(this.book.id.valueOf());
    this.location.back();
  }

  isDirty(): boolean {
    return ! this.book.equals(this.oriBook);
  }

  isNew(): boolean {
    return this.book.id === 0;
  }

  canDelete(): Boolean {
    return !this.isNew() && !this.isDirty();
  }

  devInfo(): void {
    this.statusLine = "composers: " + this.composers.length + ", c[0]:" + this.composers[0].name;
 }
}
