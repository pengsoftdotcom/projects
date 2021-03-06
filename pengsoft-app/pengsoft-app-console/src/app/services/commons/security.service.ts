import { Injectable } from '@angular/core';
import { StoreService } from './store.service';

@Injectable({
    providedIn: 'root'
})
export class SecurityService {

    private readonly ACCESS_TOKEN = 'accessToken';

    private readonly USER_DETAILS = 'userDetails';

    constructor(private store: StoreService) { }

    getBearerAuthorizationHeaders(): any {
        return { Authorization: 'Bearer ' + this.accessToken.value };
    }

    getBasicAuthorizationHeaders(): any {
        return { Authorization: 'Basic YWRtaW46YWRtaW4=' };
    }

    isAuthenticated(): boolean {
        const accessToken = this.accessToken;
        return accessToken.expiredAt && accessToken.expiredAt - new Date().getTime() + 1000 * 60 * 30 > 0;
    }

    isNotAuthenticated(): boolean {
        return !this.isAuthenticated();
    }

    clear(): void {
        this.store.clear();
    }

    get accessToken(): any {
        return Object.assign({}, this.store.get(this.ACCESS_TOKEN));
    }

    set accessToken(accessToken: any) {
        this.store.set(this.ACCESS_TOKEN, accessToken);
    }

    get userDetails(): any {
        return Object.assign({ user: {} }, this.store.get(this.USER_DETAILS));
    }

    set userDetails(userDetails: any) {
        this.store.set(this.USER_DETAILS, userDetails);
    }

    get urlsPermitted(): Array<string> {
        return ['/oauth/token'];
    }

    hasAnyAuthority(authorityCodes: string): boolean {
        let codes: Array<string>;
        if (authorityCodes.indexOf(',') === -1) {
            codes = [authorityCodes];
        } else {
            codes = authorityCodes.split(',').map(code => code.trim());
        }
        if (this.userDetails && this.userDetails.authorities) {
            return codes.some(code => this.userDetails.authorities.some(authority => authority === code));
        }
        return false;
    }

}
