import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { DashboardComponent }   from './dashboard.component';
import { BooksComponent }        from './book/books.component';
import { BookDetailComponent }        from './book/book-detail.component';
import { ComposersComponent }      from './composer/composers.component';
import { ComposerDetailComponent }  from './composer/composer-detail.component';
import { PublishersComponent }      from './publisher/publishers.component';
import { PublisherDetailComponent } from './publisher/publisher-detail.component';


const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'dashboard',  component: DashboardComponent },
  { path: 'books',     component: BooksComponent },
  { path: 'bookDetail/:id', component: BookDetailComponent },
  { path: 'composers',  component: ComposersComponent },
  { path: 'composerDetail/:id', component: ComposerDetailComponent },
  { path: 'publishers', component: PublishersComponent }
  { path: 'publisherDetail/:id', component: PublisherDetailComponent },
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
