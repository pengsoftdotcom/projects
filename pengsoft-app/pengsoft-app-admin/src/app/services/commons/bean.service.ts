import { Page } from 'src/app/components/commons/list/page';
import { BaseService } from './base.service';
import { HttpOptions } from './http-options';
import { HttpService } from './http.service';

export abstract class BeanService extends BaseService {

    constructor(protected http: HttpService) { super(); }

    abstract getModulePath(): string;

    abstract getEntityPath(): string;

    getApiPath(path: string): string {
        return super.getApiPath('/' + this.getEntityPath() + '/' + path);
    }

    save(data: any, options: HttpOptions): void {
        const url = this.getApiPath('save');
        options.body = data;
        this.http.request('POST', url, options);
    }

    delete(ids: Array<string>, options: HttpOptions): void {
        const url = this.getApiPath('delete');
        options.params = { id: ids };
        this.http.request('DELETE', url, options);
    }


    enable(ids: Array<string>, options: HttpOptions): void {
        const url = this.getApiPath('enable');
        options.params = { id: ids };
        this.http.request('PUT', url, options);
    }

    disable(ids: Array<string>, options: HttpOptions): void {
        const url = this.getApiPath('enable');
        options.params = { id: ids };
        this.http.request('PUT', url, options);
    }

    sort(params: any, options: HttpOptions): void {
        const url = this.getApiPath('sort');
        options.params = params;
        this.http.request('PUT', url, options);
    }

    findOne(id: string, options: HttpOptions): void {
        const url = this.getApiPath('find-one');
        options.params = id !== undefined && id !== null ? { id } : null;
        this.http.request('GET', url, options);
    }

    findPage(params: any, page: Page, options: HttpOptions): void {
        const url = this.getApiPath('find-page');
        params = Object.assign({}, params);
        params = Object.assign(params, { page: page.page - 1, size: page.size, sort: page.sort.map(s => s.code + ',' + s.direction) });
        options.params = params;
        this.http.request('GET', url, options);
    }

    findAll(params: any, options: HttpOptions): void {
        const url = this.getApiPath('find-all');
        options.params = params;
        this.http.request('GET', url, options);
    }

}
