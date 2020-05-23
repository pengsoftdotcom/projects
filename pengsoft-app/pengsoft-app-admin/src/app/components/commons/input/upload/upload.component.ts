import { Component, OnInit } from '@angular/core';
import { UploadFile } from 'ng-zorro-antd';
import { Observable } from 'rxjs';
import { SecurityService } from 'src/app/services/commons/security.service';
import { AssetService } from 'src/app/services/system/asset.service';
import { environment } from 'src/environments/environment';
import { InputComponent } from '../input.component';

@Component({
    selector: 'app-input-upload',
    templateUrl: './upload.component.html',
    styleUrls: ['./upload.component.scss']
})
export class UploadComponent extends InputComponent {

    files = [];

    constructor(private security: SecurityService, private asset: AssetService) { super(); }

    get path(): string {
        return environment.gateway.path + '/api/asset/upload';
    }

    get headers(): any {
        return this.security.getBearerAuthorizationHeaders();
    }

    download(file: UploadFile): void {
        const asset = file.response[0];
        window.open(asset.accessPath);
    }

    remove(file: UploadFile): boolean | Observable<boolean> {
        return new Observable(observer => this.asset.delete([file.response[0].id], {
            success: () => observer.next(true),
            failure: () => observer.next(false)
        }));
    }

}
