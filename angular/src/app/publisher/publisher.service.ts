'use strict';

import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs';

import { MusicConfig } from '../util/music-config';
import { MusicLogger } from '../util/music-logger';
import { MusicUtil } from '../util/music-util';
import { Publisher } from './publisher';

@Injectable()
export class PublisherService {

    constructor(
            private http: Http,
            private logger: MusicLogger,
            private musicUtil: MusicUtil) {
        this.logger.info("PublisherService constructor");
    }

    getPublishers(): Promise<Publisher[]> {
        let url = MusicConfig.URL_BASE + '/publisher';
        this.logger.info('getPublishers() url=' + url);

        return this.http.get(url).toPromise()  // Observable<Resp..> --> Promise<Resp>
            .then(this.extractData)
            .catch(e => this.handleErrorPromise(this, e));
    }

    private extractData(res: Response) {
        let arr: Publisher[] = res.json() as Publisher[];

        // Convert the JS array to an array of TS Publisher's
        let result: Publisher[] = [];
        for (let p of arr) {           // NOTE: 'p in arr' would return the indices
            result.push(new Publisher(p.id, p.name));
        }
        return result;
    }

    private handleErrorPromise(that: PublisherService, error: Response | any) {
        that.musicUtil.alert("publisher/handleError: " + (error.message || error));
        return Promise.reject(error.message || error);
    }

    getPublisher(id: number): Promise<Publisher> {
        return this.getPublishers()
            .then(publishers => publishers.find(publisher => publisher.id === id));
    }

    deletePublisher(id: number): Promise<String> {
        let url = MusicConfig.URL_BASE + '/publisher/' + id;
        // NO logger here
        return this.http.delete(url).toPromise()
                .then(response => response.json().data as String)
                .catch(e => this.handleErrorPromise(this, e));
    }

    storePublisher(publisher: Publisher): Promise<String> {

        let url = MusicConfig.URL_BASE + '/publisher';
        console.log('storePublisher() url=' + url);  // NO logger here
        return this.http.post(url, publisher).toPromise()
                .then(response => response.json().data as String)
                .catch(e => this.handleErrorPromise(this, e));
    }
}
