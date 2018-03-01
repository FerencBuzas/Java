import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { BooksComponent }        from './book/books.component';
import { BookDetailComponent }        from './book/book-detail.component';
import { ComposersComponent }      from './composer/composers.component';
import { ComposerDetailComponent }  from './composer/composer-detail.component';
import { HomeComponent }        from './home/home.component';
import { PublishersComponent }      from './publisher/publishers.component';
import { PublisherDetailComponent } from './publisher/publisher-detail.component';


const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'books',     component: BooksComponent },
  { path: 'bookDetail/:id', component: BookDetailComponent },
  { path: 'composers',  component: ComposersComponent },
  { path: 'composerDetail/:id', component: ComposerDetailComponent },
  { path: 'home', component: HomeComponent },
  { path: 'publishers', component: PublishersComponent },
  { path: 'publisherDetail/:id', component: PublisherDetailComponent },
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
