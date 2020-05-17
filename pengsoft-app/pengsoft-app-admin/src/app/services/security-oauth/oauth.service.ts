import { Injectable } from '@angular/core';
import { BaseService } from '../commons/base.service';
import { HttpOptions } from '../commons/http-options';
import { HttpService } from '../commons/http.service';
import { SecurityService } from '../commons/security.service';

@Injectable({
    providedIn: 'root'
})
export class OAuthService extends BaseService {

    constructor(private http: HttpService, private security: SecurityService) { super(); }

    requestTokenByPassword(username: string, password: string, options: HttpOptions): void {
        const url = this.getBasePath() + '/oauth/token';

        options.headers = this.security.getBasicAuthorizationHeaders();

        const body = new FormData();
        body.append('grant_type', 'password');
        body.append('username', username);
        body.append('password', password);
        options.body = body;

        this.http.request('POST', url, options);
    }

}
