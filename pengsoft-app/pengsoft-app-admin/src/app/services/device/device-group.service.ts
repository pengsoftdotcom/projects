import { Injectable } from '@angular/core';
import { TreeEntityService } from '../commons/tree-entity.service';
import { HttpService } from '../commons/http.service';

@Injectable({
    providedIn: 'root'
})
export class DeviceGroupService extends TreeEntityService {

    constructor(protected http: HttpService) { super(http); }

    get modulePath(): string {
        return 'device';
    }

    get entityPath(): string {
        return 'device-group';
    }

}
