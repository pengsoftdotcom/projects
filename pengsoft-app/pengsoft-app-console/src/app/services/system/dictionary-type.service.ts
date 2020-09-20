import { Injectable } from '@angular/core';
import { EntityService } from '../commons/entity.service';
import { HttpService } from '../commons/http.service';

@Injectable({
    providedIn: 'root'
})
export class DictionaryTypeService extends EntityService {

    constructor(protected http: HttpService) { super(http); }

    get modulePath(): string {
        return 'system';
    }

    get entityPath(): string {
        return 'dictionary-type';
    }

}
