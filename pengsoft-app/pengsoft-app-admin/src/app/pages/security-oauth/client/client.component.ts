import { Component } from '@angular/core';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { BeanComponent } from 'src/app/components/commons/bean.component';
import { Field } from 'src/app/components/commons/form-item/field';
import { ClientService } from 'src/app/services/security-oauth/client.service';
import { FieldUtils } from 'src/app/utils/field-utils';

@Component({
    selector: 'app-client',
    templateUrl: './client.component.html',
    styleUrls: ['./client.component.scss']
})
export class ClientComponent extends BeanComponent<ClientService> {

    constructor(
        protected bean: ClientService,
        protected modal: NzModalService,
        protected message: NzMessageService) {
        super(bean, modal, message);
    }

    get fields(): Array<Field> {
        return [
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
        this.bean.findOne(id, {
            success: (res: any) => {
                this.editForm = res;
                this.editForm.secret = null;
                this.editComponent.show();
            }
        });
    }

}
