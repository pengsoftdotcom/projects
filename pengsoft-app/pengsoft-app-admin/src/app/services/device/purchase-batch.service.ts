import { Injectable } from '@angular/core';
import { HttpService } from '../commons/http.service';
import { BeanService } from '../commons/bean.service';

@Injectable({
    providedIn: 'root'
})
export class PurchaseBatchService extends BeanService {

    constructor(protected http: HttpService) { super(http); }

    get modulePath(): string {
        return 'device';
    }

    get entityPath(): string {
        return 'purchase-batch';
    }

}
