import { Component } from '@angular/core';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { BeanComponent } from 'src/app/components/commons/bean.component';
import { Field } from 'src/app/components/commons/form-item/field';
import { AuthorityService } from 'src/app/services/security/authority.service';
import { FieldUtils } from 'src/app/utils/field-utils';

@Component({
    selector: 'app-authority',
    templateUrl: './authority.component.html',
    styleUrls: ['./authority.component.scss']
})
export class AuthorityComponent extends BeanComponent<AuthorityService> {

    constructor(protected authority: AuthorityService, protected modal: NzModalService, protected message: NzMessageService) {
        super(authority, modal, message);
    }

    get fields(): Array<Field> {
        return [
            FieldUtils.buildText({ code: 'code', name: '编码', list: { filterable: true, sortable: true, sortPriority: 1 } }),
            FieldUtils.buildText({ code: 'name', name: '名称', list: { filterable: true, sortable: true } }),
            FieldUtils.buildTexarea({ code: 'remark', name: '备注' }),
        ];
    }

}
