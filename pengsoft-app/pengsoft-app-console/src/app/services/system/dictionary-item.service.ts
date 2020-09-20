import { Injectable } from '@angular/core';
import { TreeEntityService } from '../commons/tree-entity.service';
import { HttpService } from '../commons/http.service';
import { HttpOptions } from '../commons/http-options';

@Injectable({
    providedIn: 'root'
})
export class DictionaryItemService extends TreeEntityService {

    constructor(protected http: HttpService) { super(http); }

    get modulePath(): string {
        return 'system';
    }

    get entityPath(): string {
        return 'dictionary-item';
    }

    findAllByTypeCode(code: string, params: any, options: HttpOptions): void {
        const url = this.getApiPath('find-all-by-type-code');
        options.params = Object.assign({ code }, params);
        this.http.request('GET', url, options);
    }

}
