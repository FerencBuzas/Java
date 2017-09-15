import { Injectable } from '@angular/core';
import { Http }       from '@angular/http';

import { Observable }     from 'rxjs/Observable';
import 'rxjs/add/operator/map';

import { Composer }           from './composer';

@Injectable()
export class ComposerSearchService {

  constructor(private http: Http) {}

  search(term: string): Observable<Composer[]> {
    return this.http
               .get(`api/composers/?name=${term}`)
               .map(response => response.json().data as Composer[]);
  }
}
