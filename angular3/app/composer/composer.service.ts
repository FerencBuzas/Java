'use strict';

import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';

import { Constants } from '../constants';
import { Composer } from './composer';
import { COMPOSERS } from './mock-composers';

@Injectable()
export class ComposerService {

    constructor(private http: Http) {
        console.info("ComposerService constructor ## http: " + http);
    }

    // From: http://www.concretepage.com/angular-2/angular-2-http-get-example
    // Also good: https://medium.com/google-developer-experts/angular-2-introduction-to-new-http-module-1278499db2a0
    //
    getComposers(): Promise<Composer[]> {
        var url = Constants.URL_BASE + '/composer';
        console.info("## getComposers() url=" + url + "##");

        return this.http.get(url).toPromise() // Observable<Response> --> Promise<Response>
    	   .then(this.extractData)                 // --> Promise<Composer[]>  (as map() with Obs.)
    	   .catch(this.handleErrorPromise);
    }

    private extractData(res: Response) {
        console.info("## extractData ##");
	    let body = res.json();
        return body;
    }

    private handleErrorPromise(error: Response | any) {
        console.error("## handleErrorPromise() ##");
    	console.error(error.message || error);
    	return Promise.reject(error.message || error);
    }

    getComposer(id: number): Promise<Composer> {
      return this.getComposers()
        .then(composers => composers.find(composer => composer.id === id));
    }
}
