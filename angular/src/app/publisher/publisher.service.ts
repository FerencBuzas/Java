'use strict';

import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import 'rxjs/add/operator/toPromise';

import { MusicConfig } from '../util/music-config';
import { MusicLogger } from '../util/music-logger';
import { Publisher } from './publisher';

@Injectable()
export class PublisherService {

    constructor(
            private http: Http,
            private logger: MusicLogger) {
        this.logger.info("PublisherService constructor http: " + http + " logger: " + logger);
    }

    getPublishers(): Promise<Publisher[]> {
        let url = MusicConfig.URL_BASE + '/Publisher';
        this.logger.info("getPublishers() url=" + url);

        return this.http.get(url).toPromise() // Observable<Response> --> Promise<Response>
            .then(this.extractData)            // --> Promise<Publisher[]>  (as map() with Obs.)
            .catch(this.handleErrorPromise);
    }

    private extractData(res: Response) {
        // this.logger... NO!
        let body = res.json();
        return body;
    }

    private handleErrorPromise(error: Response | any) {
        console.log(error.message || error);
        return Promise.reject(error.message || error);
    }

    getPublisher(id: number): Promise<Publisher> {
        return this.getPublishers()
            .then(publishers => publishers.find(publisher => publisher.id === id));
    }

    deletePublisher(id: number): Promise<String> {
        let url = MusicConfig.URL_BASE + '/publisher/delete?id=' + id;
        // NO logger here
        return this.http.get(url).toPromise()
                .then(this.extractData)
                .catch(this.handleErrorPromise);
    }

    addPublisher(name: String): Promise<String> {

        let url = MusicConfig.URL_BASE + '/publisher/add?'
                   + 'name=' + name;
        console.log('## url=' + url + ' ##');  // NO logger here
        return this.http.get(url).toPromise()
                .then(this.extractData)
                .catch(this.handleErrorPromise);
    }
}
