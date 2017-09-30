'use strict';

import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import 'rxjs/add/operator/toPromise';

import { MusicConfig } from '../util/music-config';
import { MusicLogger } from '../util/music-logger';
import { Book } from './book';
import { Composer } from '../composer/composer';
import { Publisher } from '../publisher/publisher';

@Injectable()
export class BookService {

    constructor(
            private http: Http,
            private logger: MusicLogger) {
        this.logger.info("BookService constructor");
    }

    // From: http://www.concretepage.com/angular-2/angular-2-http-get-example
    // Also good: https://medium.com/google-developer-experts/angular-2-introduction-to-new-http-module-1278499db2a0
    //
    getBooks(): Promise<Book[]> {
        let url = MusicConfig.URL_BASE + '/book';
        this.logger.info("getBooks() url=" + url);

        return this.http.get(url).toPromise() // Observable<Response> --> Promise<Response>
            .then(this.extractData)            // --> Promise<Book[]>  (as map() with Obs.)
            .catch(this.handleErrorPromise);
    }

    // A good answer to a similar question:
    //   https://stackoverflow.com/questions/40256658/getting-an-object-array-from-an-angularjs-2-service
    //
    private extractData(res: Response) {
        let arr: Book[] = res.json() as Book[];

        // Convert the JS array to an array of TS Book's
        let result: Book[] = [];
        for (let p of arr) {           // NOTE: 'p in arr' would return the indices
            let comp = new Composer(p.composer.id, p.composer.name, p.composer.birthYear);
            let pub = new Publisher(p.publisher.id, p.publisher.name);
            result.push(new Book(p.id, p.title, comp, pub, p.pubYear));
        }
        return result;
    }

    private handleErrorPromise(error: Response | any) {
        console.log(error.message || error);
        return Promise.reject(error.message || error);
    }

    getBook(id: number): Promise<Book> {
        return this.getBooks()
            .then(books => books.find(book => book.id === id));
    }

    deleteBook(id: number): Promise<String> {
        let url = MusicConfig.URL_BASE + '/book/delete?id=' + id;
        // NO logger here
        return this.http.get(url).toPromise()
                .then(response => response.json() as String)
                .catch(this.handleErrorPromise);
    }

    storeBook(id: number,
            title: String,
            composer: Composer,
            publisher: Publisher,
            pubYear: number): Promise<String> {

        let url = MusicConfig.URL_BASE + '/book/store?'
                   + 'id=' + id
                   + '&title=' + title
                   + '&composer=' + composer.name
                   + '&publisher=' + publisher.name
                   + '&pubYear=' + pubYear;
        console.log('## url=' + url + ' ##');  // NO logger here
        return this.http.get(url).toPromise()
                .then(response => response.json() as String)
                .catch(this.handleErrorPromise);
    }
}
