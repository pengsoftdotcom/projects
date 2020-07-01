import { Injectable } from '@angular/core';
import { TreeBeanService } from '../commons/tree-bean.service';
import { HttpService } from '../commons/http.service';

@Injectable({
    providedIn: 'root'
})
export class BannerService extends TreeBeanService {

    constructor(protected http: HttpService) { super(http); }

    get modulePath(): string {
        return 'portal';
    }

    get entityPath(): string {
        return 'banner';
    }

}
