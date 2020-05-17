import { Injectable } from '@angular/core';
import { BeanService } from '../commons/bean.service';
import { HttpService } from '../commons/http.service';
import { NzMessageService } from 'ng-zorro-antd';

@Injectable({
    providedIn: 'root'
})
export class ClientService extends BeanService {

    constructor(protected http: HttpService) { super(http); }

    getModulePath(): string {
        return 'security-oauth';
    }

    getEntityPath(): string {
        return 'client';
    }

}
