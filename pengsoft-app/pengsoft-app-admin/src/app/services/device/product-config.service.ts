import { Injectable } from '@angular/core';
import { BeanService } from '../commons/bean.service';
import { HttpService } from '../commons/http.service';

@Injectable({
    providedIn: 'root'
})
export class ProductConfigService extends BeanService {

    constructor(protected http: HttpService) { super(http); }

    get modulePath(): string {
        return 'device';
    }

    get entityPath(): string {
        return 'product-config';
    }

}
