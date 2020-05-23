import { Component } from '@angular/core';
import { SecurityService } from 'src/app/services/commons/security.service';
import { AssetService } from 'src/app/services/system/asset.service';
import { environment } from 'src/environments/environment';
import { InputComponent } from '../input.component';
import { UploadChangeParam, NzMessageService } from 'ng-zorro-antd';


@Component({
    selector: 'app-input-avatar',
    templateUrl: './avatar.component.html',
    styleUrls: ['./avatar.component.scss']
})
export class AvatarComponent extends InputComponent {

    constructor(private security: SecurityService, private message: NzMessageService) { super(); }

    get path(): string {
        return environment.gateway.path + '/api/asset/upload';
    }

    get headers(): any {
        return this.security.getBearerAuthorizationHeaders();
    }

    get thumbnail(): string {
        return this.form[this.field.code].accessPath + '?x-oss-process=image/resize,w_'
            + this.field.edit.input.width + ',h_' + this.field.edit.input.height;
    }

    modelChange(event: UploadChangeParam) {
        switch (event.file.status) {
            case 'uploading':
                this.loading = true;
                break;
            case 'done':
                this.loading = false;
                const res = event.file.response;
                if (res && res.length > 0) {
                    this.form[this.field.code] = res[0];
                }
                break;
            case 'error':
                this.message.error('上传失败：' + event.file.error.error.error_description);
                this.loading = false;
                break;
        }
    }

}
