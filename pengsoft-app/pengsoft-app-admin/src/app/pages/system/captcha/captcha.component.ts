import { Component } from '@angular/core';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { EntityComponent } from 'src/app/components/commons/entity.component';
import { Button } from 'src/app/components/commons/button/button';
import { Field } from 'src/app/components/commons/form-item/field';
import { CaptchaService } from 'src/app/services/system/captcha.service';
import { FieldUtils } from 'src/app/utils/field-utils';
import { UserComponent } from '../../security/user/user.component';

@Component({
    selector: 'app-captcha',
    templateUrl: './captcha.component.html',
    styleUrls: ['./captcha.component.scss']
})
export class CaptchaComponent extends EntityComponent<CaptchaService> {

    constructor(
        protected entity: CaptchaService,
        protected modal: NzModalService,
        protected message: NzMessageService
    ) {
        super(entity, modal, message);
    }

    initFields(): void {
        UserComponent.prototype.initFields();
        this.fields = [
            FieldUtils.buildTextForCode({ width: 120, align: 'center', sortable: false }),
            FieldUtils.buildDatetimeForExpiredAt(),
            FieldUtils.buildText({
                code: 'user', name: '用户',
                children: UserComponent.prototype.fields
                    .filter(field => !['username', 'email', 'mpOpenId'].includes(field.code))
                    .map((field: Field) => {
                        if (field.code === 'expiredAt') {
                            delete field.filter;
                        }
                        return field;
                    })
            })
        ];
    }

    initListActionButtons(): void {
        super.initListActionButtons();
        this.listActionButtons.splice(0, 1);
    }

}
