//
// Logger class by Ferenc Buzas
//

import { Injectable } from '@angular/core';

@Injectable()
export class MusicLogger {

    error(msg: string) {
        console.log("ERROR " + msg);
    }

    info(msg: string) {
        console.log("INFO  " + msg);
    }

    debug(msg: string) {
        console.log("DEBUG " + msg);
    }

}