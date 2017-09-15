import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }   from '@angular/forms';
import { RouterModule }   from '@angular/router';

import { AppComponent }        from './app.component';
import { DashboardComponent }  from './dashboard.component';
import { ComposerDetailComponent } from './composer/composer-detail.component';
import { ComposersComponent }     from './composer/composers.component';
import { ComposerService }         from './composer/composer.service';

import { AppRoutingModule }     from './app-routing.module';

@NgModule({
  imports:      [
    BrowserModule,
    FormsModule,
    AppRoutingModule
  ],
  declarations: [
    AppComponent,
    DashboardComponent,
    ComposerDetailComponent,
    ComposersComponent
  ],
  providers: [ ComposerService ],
  bootstrap: [ AppComponent ]
})
export class AppModule { 
}
