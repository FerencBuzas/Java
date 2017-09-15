import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }   from '@angular/forms';
import { HttpModule }    from '@angular/http';

import { AppRoutingModule } from './app-routing.module';

// Imports for loading & configuring the in-memory web api
import { InMemoryWebApiModule } from 'angular-in-memory-web-api';
import { InMemoryDataService }  from './in-memory-data.service';

import { AppComponent }         from './app.component';
import { DashboardComponent }   from './dashboard.component';
import { ComposersComponent }      from './composer/composers.component';
import { ComposerDetailComponent }  from './composer/composer-detail.component';
import { ComposerService }          from './composer/composer.service';
import { ComposerSearchComponent }  from './composer/composer-search.component';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    InMemoryWebApiModule.forRoot(InMemoryDataService),
    AppRoutingModule
  ],
  declarations: [
    AppComponent,
    DashboardComponent,
    ComposerDetailComponent,
    ComposersComponent,
    ComposerSearchComponent
  ],
  providers: [ ComposerService ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
