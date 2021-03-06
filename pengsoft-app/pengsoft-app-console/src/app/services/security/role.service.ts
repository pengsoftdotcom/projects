import { Injectable } from '@angular/core';
import { HttpService } from '../commons/http.service';
import { TreeEntityService } from '../commons/tree-entity.service';
import { HttpOptions } from '../commons/http-options';

@Injectable({
    providedIn: 'root'
})
export class RoleService extends TreeEntityService {

    constructor(protected http: HttpService) { super(http); }

    get modulePath(): string {
        return 'security';
    }

    get entityPath(): string {
        return 'role';
    }

    grantAuthorities(role: any, authorities: Array<any>, options: HttpOptions): void {
        const url = this.getApiPath('grant-authorities');
        options.params = { 'role.id': role.id, 'authority.id': authorities.map(authority => authority.id) };
        this.http.request('POST', url, options);
    }

    findAllRoleAuthoritiesByRole(role: any, options: HttpOptions) {
        const url = this.getApiPath('find-all-role-authorities-by-role');
        options.params = { id: role.id };
        this.http.request('GET', url, options);
    }

}
