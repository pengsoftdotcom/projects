import { Injectable } from '@angular/core';
import { HttpService } from './commons/http.service';
import { BeanService } from './commons/bean.service';

@Injectable({
    providedIn: 'root'
})
export class MessageService extends BeanService {

    constructor(protected http: HttpService) { super(http); }

    get modulePath(): string {
        return 'system';
    }
    get entityPath(): string {
        return 'message';
    }

}
