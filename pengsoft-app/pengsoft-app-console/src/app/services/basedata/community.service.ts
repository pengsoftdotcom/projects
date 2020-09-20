import { Injectable } from '@angular/core';
import { HttpService } from '../commons/http.service';
import { TreeEntityService } from '../commons/tree-entity.service';

@Injectable({
    providedIn: 'root'
})
export class CommunityService extends TreeEntityService {

    constructor(protected http: HttpService) { super(http); }

    get modulePath(): string {
        return 'basedata';
    }

    get entityPath(): string {
        return 'community';
    }

}
