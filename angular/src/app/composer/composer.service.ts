'use strict';

import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import 'rxjs/add/operator/toPromise';

import { MusicConfig } from '../util/music-config';
import { MusicLogger } from '../util/music-logger';
import { Composer } from './composer';

@Injectable()
export class ComposerService {

    constructor(private http: Http,
                private logger: MusicLogger) {
    }

    getComposers(): Promise<Composer[]> {
        let url = MusicConfig.URL_BASE + '/composer';
        this.logger.info('getComposers() url=' + url);

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
        console.log(error.message || error);
        return Promise.reject(error.message || error);
    }

    getComposer(id: number): Promise<Composer> {
        return this.getComposers()
            .then(composers => composers.find(composer => composer.id === id));
    }

    deleteComposer(id: number): Promise<String> {
        let url = MusicConfig.URL_BASE + '/composer/delete?id=' + id;
        // NO logger here
        return this.http.get(url).toPromise()
                .then(this.extractData)
                .catch(this.handleErrorPromise);
    }

    addComposer(name: String, birthYear: number): Promise<String> {

        let url = MusicConfig.URL_BASE + '/composer/add?'
                   + 'name=' + name
                   + '&birthYear=' + birthYear;
        console.log('## addComposer() url=' + url + ' ##');  // NO logger here
        return this.http.get(url).toPromise()
                .then(this.extractData)
                .catch(this.handleErrorPromise);
    }
}
