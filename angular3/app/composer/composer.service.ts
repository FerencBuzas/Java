'use strict';

import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';

import { MusicConfig } from '../util/music-config';
import { MusicLogger } from '../util/music-logger';
import { Composer } from './composer';
import { COMPOSERS } from './mock-composers';

@Injectable()
export class ComposerService {

    constructor(private http: Http,
                private logger: MusicLogger) {
        this.logger.info("ComposerService constructor http: " + http + " logger: " + logger);
    }

    // From: http://www.concretepage.com/angular-2/angular-2-http-get-example
    // Also good: https://medium.com/google-developer-experts/angular-2-introduction-to-new-http-module-1278499db2a0
    //
    getComposers(): Promise<Composer[]> {
        var url = MusicConfig.URL_BASE + '/composer';
        this.logger.info("getComposers() url=" + url);

        return this.http.get(url).toPromise() // Observable<Response> --> Promise<Response>
    	   .then(this.extractData)            // --> Promise<Composer[]>  (as map() with Obs.)
    	   .catch(this.handleErrorPromise);
    }

    private extractData(res: Response) {
        // NOTE: 'this.logger' can not be used here!!
	    let body = res.json();
        return body;
    }

    private handleErrorPromise(error: Response | any) {
        this.logger.error("handleErrorPromise() ## " + (error.message || error));
    	return Promise.reject(error.message || error);
    }

    getComposer(id: number): Promise<Composer> {
        return this.getComposers()
            .then(composers => composers.find(composer => composer.id === id));
    }
}
