import { Component, OnInit } from '@angular/core';
import { Router }            from '@angular/router';

import { Observable }        from 'rxjs/Observable';
import { Subject }           from 'rxjs/Subject';

// Observable class extensions
import 'rxjs/add/observable/of';

// Observable operators
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import 'rxjs/add/operator/switchMap';

import { ComposerSearchService } from './composer-search.service';
import { Composer } from './composer';

@Component({
  selector: 'composer-search',
  templateUrl: './composer-search.component.html',
  styleUrls: [ './composer-search.component.css' ],
  providers: [ComposerSearchService]
})
export class ComposerSearchComponent implements OnInit {
  composers: Observable<Composer[]>;
  private searchTerms = new Subject<string>();

  constructor(
    private composerSearchService: ComposerSearchService,
    private router: Router) {}

  // Push a search term into the observable stream.
  search(term: string): void {
    this.searchTerms.next(term);
  }

  ngOnInit(): void {
    console.log("ngOnInit() ##");
    this.composers = this.searchTerms
      .debounceTime(300)        // wait 300ms after each keystroke before considering the term
      .distinctUntilChanged()   // ignore if next search term is same as previous
      .switchMap(term => term   // switch to new observable each time the term changes
        ? this.composerSearchService.search(term)    // return the http search observable
        : Observable.of<Composer[]>([]))             // or the obs. of empty composers
      .catch(error => {
        console.log(error);              // TODO: add real error handling
        return Observable.of<Composer[]>([]);
      });
  }

  gotoDetail(composer: Composer): void {
    let link = ['/detail', composer.id];
    this.router.navigate(link);
  }
}
