import { Injectable } from '@angular/core';

@Injectable()
export class MusicUtil {

    /**
     * Starts a modal dialog, returns if Yes was selected.
     */
    public alert(msg: string) : void {
        console.log('ALERT: ' + msg);
        window.alert(msg);
    }
    /**
     * Starts a modal dialog, returns if Yes was selected.
     */
    public confirm(msg: string) : boolean {
        let result = window.confirm(msg);
        console.log("CONFIRM: " + msg + " answered=" + result);
        return result;
    }
}
