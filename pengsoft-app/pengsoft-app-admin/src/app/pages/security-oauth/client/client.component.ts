import { Component } from '@angular/core';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { EntityComponent } from 'src/app/components/commons/entity.component';
import { Field } from 'src/app/components/commons/form-item/field';
import { ClientService } from 'src/app/services/security-oauth/client.service';
import { FieldUtils } from 'src/app/utils/field-utils';

@Component({
    selector: 'app-client',
    templateUrl: './client.component.html',
    styleUrls: ['./client.component.scss']
})
export class ClientComponent extends EntityComponent<ClientService> {

    constructor(
        protected entity: ClientService,
        protected modal: NzModalService,
        protected message: NzMessageService) {
        super(entity, modal, message);
    }

    initFields(): void {
        this.fields = [
            FieldUtils.buildTextForCode(),
            FieldUtils.buildTextForName(),
            FieldUtils.buildText({ code: 'secret', name: '密码', list: { visible: false }, edit: { input: { placeholder: '如不需修改，请勿填写' } } }),
            FieldUtils.buildText({ code: 'grantTypes', name: '授权类型' }),
            FieldUtils.buildNumber({ code: 'validitySeconds', name: '有效秒数' })
        ];
    }

    edit(row?: any): void {
        this.errors = {};
        const id = row ? row.id : null;
        this.entity.findOne(id, {
            success: (res: any) => {
                this.editForm = res;
                this.editForm.secret = null;
                this.editComponent.show();
            }
        });
    }

}
