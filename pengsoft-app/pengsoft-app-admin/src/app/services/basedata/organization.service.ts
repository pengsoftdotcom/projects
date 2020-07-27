import { Injectable } from '@angular/core';
import { HttpService } from '../commons/http.service';
import { TreeEntityService } from '../commons/tree-entity.service';
import { HttpOptions } from '../commons/http-options';

@Injectable({
    providedIn: 'root'
})
export class OrganizationService extends TreeEntityService {

    constructor(protected http: HttpService) { super(http); }

    get modulePath(): string {
        return 'basedata';
    }

    get entityPath(): string {
        return 'organization';
    }

}
