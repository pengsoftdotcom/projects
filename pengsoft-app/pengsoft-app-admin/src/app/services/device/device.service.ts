import { Injectable } from '@angular/core';
import { EntityService } from '../commons/entity.service';
import { HttpService } from '../commons/http.service';
import { HttpOptions } from '../commons/http-options';

@Injectable({
    providedIn: 'root'
})
export class DeviceService extends EntityService {

    constructor(protected http: HttpService) { super(http); }

    get modulePath(): string {
        return 'device';
    }

    get entityPath(): string {
        return 'device';
    }


    pushConfig(device: any, options: HttpOptions): void {
        const url = this.getApiPath('push-config');
        options.params = { id: device.id };
        this.http.request('POST', url, options);
    }

    pushTime(device: any, options: HttpOptions): void {
        const url = this.getApiPath('push-time');
        options.params = { id: device.id };
        this.http.request('POST', url, options);
    }

}
