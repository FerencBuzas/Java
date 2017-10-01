import { Injectable } from '@angular/core';

@Injectable()
export class MusicUtil {

    /**
     * Starts a modal dialog, returns if Yes was selected.
     */
    public confirm(msg: string) : boolean {
        return window.confirm(msg);
    }
}
