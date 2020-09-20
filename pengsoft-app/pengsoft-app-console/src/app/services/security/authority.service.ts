import { Injectable } from '@angular/core';
import { EntityService } from '../commons/entity.service';
import { HttpService } from '../commons/http.service';

@Injectable({
    providedIn: 'root'
})
export class AuthorityService extends EntityService {

    constructor(protected http: HttpService) { super(http); }

    get modulePath(): string {
        return 'security';
    }

    get entityPath(): string {
        return 'authority';
    }

}
