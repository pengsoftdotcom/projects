import { Injectable } from '@angular/core';
import { HttpService } from '../commons/http.service';
import { TreeBeanService } from '../commons/tree-bean.service';

@Injectable({
    providedIn: 'root'
})
export class OrganizationService extends TreeBeanService {

    constructor(protected http: HttpService) { super(http); }

    get modulePath(): string {
        return 'basedata';
    }

    get entityPath(): string {
        return 'organization';
    }

}
