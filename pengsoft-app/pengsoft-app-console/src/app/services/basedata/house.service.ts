import { Injectable } from '@angular/core';
import { EntityService } from '../commons/entity.service';
import { HttpService } from '../commons/http.service';
import { HttpOptions } from '../commons/http-options';

@Injectable({
    providedIn: 'root'
})
export class HouseService extends EntityService {

    constructor(protected http: HttpService) { super(http); }

    get modulePath(): string {
        return 'basedata';
    }

    get entityPath(): string {
        return 'house';
    }

    save(form: any, options: HttpOptions): void {
        if (JSON.stringify(form.owner) === '{}') {
            delete form.owner;
        }
        super.save(form, options);
    }

}
