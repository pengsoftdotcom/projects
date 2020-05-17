import { Injectable } from '@angular/core';
import { BeanService } from '../commons/bean.service';
import { HttpService } from '../commons/http.service';

@Injectable({
    providedIn: 'root'
})
export class AuthorityService extends BeanService {

    constructor(protected http: HttpService) { super(http); }

    getModulePath(): string {
        return 'security';
    }

    getEntityPath(): string {
        return 'authority';
    }

}
