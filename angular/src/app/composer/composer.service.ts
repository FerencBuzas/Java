'use strict';

import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import 'rxjs/add/operator/toPromise';

import { MusicConfig } from '../util/music-config';
import { MusicLogger } from '../util/music-logger';
import { MusicUtil } from '../util/music-util';
import { Composer } from './composer';

@Injectable()
export class ComposerService {

    constructor(private http: Http,
                private logger: MusicLogger,
                private musicUtil: MusicUtil) {
    }

    getComposers(): Promise<Composer[]> {
        let url = MusicConfig.URL_BASE + '/composer';
        this.logger.info('getComposers() url=' + url);

        return this.http.get(url).toPromise() // Observable<Response> --> Promise<Response>
            .then(this.extractData)            // --> Promise<Composer[]>  (as map() with Obs.)
            .catch(e => this.handleErrorPromise(this, e));
    }

    private extractData(res: Response) {
        let arr: Composer[] = res.json() as Composer[];

        // Convert the JS array to an array of TS Composer's
        let result: Composer[] = [];
        for (let p of arr) {           // NOTE: 'p in arr' would return the indices
            result.push(new Composer(p.id, p.name, p.birthYear));
        }
        return result;
    }

    private handleErrorPromise(that: ComposerService, error: Response | any) {
        that.musicUtil.alert("CompSe.handleErrorPromise " + (error.message || error));
        return Promise.reject(error.message || error);
    }

    getComposer(id: number): Promise<Composer> {
        return this.getComposers()
            .then(composers => composers.find(composer => composer.id === id));
    }

    deleteComposer(id: number): Promise<String> {
        let url = MusicConfig.URL_BASE + '/composer/' + id;
        // NO logger here
        return this.http.delete(url).toPromise()
            .then(response => response.json() as String)
            .catch(e => this.handleErrorPromise(this, e));
    }

    storeComposer(composer: Composer): Promise<String> {

        let url = MusicConfig.URL_BASE + '/composer';
        console.log('storeComposer() url=' + url);
        return this.http.post(url, composer).toPromise()
            .then(response => response.json() as String)
            .catch(e => this.handleErrorPromise(this, e));
    }
}
