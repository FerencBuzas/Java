import { Injectable } from '@angular/core';

@Injectable()
export class MusicUtil {

    /**
     * Returns a deep copy of the object
     */
    public static deepCopy(oldObj: any) {
        let newObj = oldObj;
        if (oldObj && typeof oldObj === 'object') {
            newObj = Object.prototype.toString.call(oldObj) === '[object Array]' ? [] : {};
            for (var i in oldObj) {
                newObj[i] = this.deepCopy(oldObj[i]);
            }
        }
        return newObj;
    }
}
