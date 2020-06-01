import { Injectable } from '@angular/core';
import { TreeBeanService } from '../commons/tree-bean.service';
import { HttpService } from '../commons/http.service';
import { HttpOptions } from '../commons/http-options';

@Injectable({
    providedIn: 'root'
})
export class JobService extends TreeBeanService {

    constructor(protected http: HttpService) { super(http); }

    get modulePath(): string {
        return 'basedata';
    }

    get entityPath(): string {
        return 'job';
    }

    grantRoles(job: any, roles: Array<any>, options: HttpOptions): void {
        const url = this.getApiPath('grant-roles');
        options.params = { 'job.id': job.id, 'role.id': roles.map(role => role.id) };
        this.http.request('POST', url, options);
    }

    findAllUserRolesByUser(job: any, options: HttpOptions): void {
        const url = this.getApiPath('find-all-job-roles-by-job');
        options.params = { id: job.id };
        this.http.request('GET', url, options);
    }

}
