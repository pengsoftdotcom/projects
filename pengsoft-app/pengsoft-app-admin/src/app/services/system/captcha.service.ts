import { Injectable } from '@angular/core';
import { HttpService } from '../commons/http.service';
import { EntityService } from '../commons/entity.service';

@Injectable({
    providedIn: 'root'
})
export class CaptchaService extends EntityService {

    constructor(protected http: HttpService) { super(http); }

    get modulePath(): string {
        return 'system';
    }

    get entityPath(): string {
        return 'captcha';
    }

}
