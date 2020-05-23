import { Component } from '@angular/core';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { BeanComponent } from 'src/app/components/commons/bean.component';
import { Field } from 'src/app/components/commons/form-item/field';
import { UserProfileService } from 'src/app/services/basedata/user-profile.service';
import { DictionaryItemService } from 'src/app/services/system/dictionary-item.service';
import { FieldUtils } from 'src/app/utils/field-utils';
import { InputComponent } from 'src/app/components/commons/input/input.component';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
    selector: 'app-user-profile',
    templateUrl: './user-profile.component.html',
    styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent extends BeanComponent<UserProfileService> {

    constructor(
        private dictionaryItem: DictionaryItemService,
        protected bean: UserProfileService,
        protected modal: NzModalService,
        protected message: NzMessageService
    ) {
        super(bean, modal, message);
    }

    get fields(): Array<Field> {
        return [
            FieldUtils.buildAvatar(),
            FieldUtils.buildTextForName({ code: 'name', name: '姓名', edit: { required: true } }),
            FieldUtils.buildText({ code: 'nickname', name: '昵称', filter: {} }),
            FieldUtils.buildSelect({
                code: 'gender', name: '性别',
                list: { width: 80, align: 'center' },
                edit: {
                    input: {
                        load: (inputComponent: InputComponent) => {
                            this.dictionaryItem.findAllByTypeCode('gender', null, {
                                before: () => inputComponent.loading = true,
                                success: (res: any) => inputComponent.field.edit.input.options
                                    = res.map((value: any) => Object.assign({ label: value.name, value: value.id, rawValue: value })),
                                after: () => inputComponent.loading = false
                            });
                        }
                    }
                },
                filter: {}
            }),
            FieldUtils.buildText({
                code: 'mobile', name: '手机',
                list: {
                    width: 140, align: 'center',
                    render: (field: Field, row: any, sanitizer: DomSanitizer) => {
                        if (row.mobile) {
                            return sanitizer.bypassSecurityTrustHtml(`<code>${row.mobile}</code>`);
                        } else {
                            return null;
                        }
                    }
                },
                edit: { required: true, disabled: (field: Field, form: any) => !!form.id },
                filter: {}
            })
        ];
    }

}
