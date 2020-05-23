import { Injectable } from '@angular/core';
import { HttpService } from './commons/http.service';
import { MessageService } from './message.service';

@Injectable({
    providedIn: 'root'
})
export class MessageTemplateService extends MessageService {

    constructor(protected http: HttpService) { super(http); }

    get modulePath(): string {
        return 'system';
    }
    get entityPath(): string {
        return 'message-template';
    }


}
