import { Component } from '@angular/core';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { BeanComponent } from 'src/app/components/commons/bean.component';
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
export class CaptchaComponent extends BeanComponent<CaptchaService> {

    constructor(
        protected bean: CaptchaService,
        protected modal: NzModalService,
        protected message: NzMessageService
    ) {
        super(bean, modal, message);

    }
    get fields(): Array<Field> {
        return [
            FieldUtils.buildTextForCode({ width: 120, align: 'center', sortable: false }),
            FieldUtils.buildDatetimeForExpiredAt(),
            FieldUtils.buildText({
                code: 'user', name: '用户', children: UserComponent.prototype.fields.map((field: Field) => {
                    field.edit.label.span = 4;
                    if (field.code === 'expiredAt') {
                        delete field.filter;
                    }
                    return field;
                })
            })
        ];
    }

    get listToolbarButtons(): Array<Button> {
        const buttons = super.listToolbarButtons;
        buttons.splice(1, 1);
        return buttons;
    }

    get listActionButtons(): Array<Button> {
        const buttons = super.listActionButtons;
        buttons.splice(0, 1);
        return buttons;
    }

}
