import { Injectable } from '@angular/core';
import { BeanService } from '../commons/bean.service';
import { HttpService } from '../commons/http.service';
import { HttpOptions } from '../commons/http-options';

@Injectable({
    providedIn: 'root'
})
export class DeviceConfigService extends BeanService {

    constructor(protected http: HttpService) { super(http); }

    get modulePath(): string {
        return 'device';
    }

    get entityPath(): string {
        return 'device-config';
    }

}
