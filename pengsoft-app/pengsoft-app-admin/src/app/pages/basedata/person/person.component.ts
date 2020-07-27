import { Component } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { EntityComponent } from 'src/app/components/commons/entity.component';
import { Field } from 'src/app/components/commons/form-item/field';
import { InputComponent } from 'src/app/components/commons/input/input.component';
import { ResetPasswordComponent } from 'src/app/components/modal/reset-password/reset-password.component';
import { PersonService } from 'src/app/services/basedata/person.service';
import { DictionaryItemService } from 'src/app/services/system/dictionary-item.service';
import { FieldUtils } from 'src/app/utils/field-utils';

@Component({
    selector: 'app-person',
    templateUrl: './person.component.html',
    styleUrls: ['./person.component.scss']
})
export class PersonComponent extends EntityComponent<PersonService> {

    constructor(
        public dictionaryItem: DictionaryItemService,
        protected entity: PersonService,
        protected modal: NzModalService,
        protected message: NzMessageService
    ) {
        super(entity, modal, message);
    }

    initFields(): void {
        this.fields = [
            FieldUtils.buildAvatar(),
            FieldUtils.buildTextForName({ code: 'name', name: '姓名', edit: { required: true } }),
            FieldUtils.buildText({ code: 'nickname', name: '昵称', filter: {} }),
            FieldUtils.buildSelect({
                code: 'gender', name: '性别',
                list: { width: 80, align: 'center' },
                edit: {
                    input: {
                        load: (component: InputComponent) => {
                            this.dictionaryItem.findAllByTypeCode('gender', null, {
                                before: () => component.loading = true,
                                success: (res: any) => component.edit.input.options
                                    = res.map((value: any) => Object.assign({ label: value.name, value })),
                                after: () => component.loading = false
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
                        if (row && row[field.code]) {
                            return sanitizer.bypassSecurityTrustHtml(`<code>${row[field.code]}</code>`);
                        } else {
                            return null;
                        }
                    }
                },
                edit: { required: true, disabled: (form: any) => !!form.id },
                filter: {}
            })
        ];
    }

    initListActionButtons(): void {
        super.initListActionButtons();
        this.listActionButtons.splice(0, 0, {
            name: '重置密码',
            type: 'link',
            divider: true,
            width: 75,
            authority: 'security::user::reset_password',
            action: (row: any) => this.resetPassword(row)
        });
    }

    resetPassword(row: any): void {
        this.modal.create({
            nzBodyStyle: { padding: '16px', marginBottom: '-24px' },
            nzTitle: '重置密码',
            nzContent: ResetPasswordComponent,
            nzComponentParams: {
                form: { id: row.user.id }
            },
            nzOnOk: component => new Promise(resolve => {
                component.submit({
                    before: () => component.loading = true,
                    success: () => {
                        this.message.info('重置成功');
                        resolve(true);
                    },
                    failure: () => resolve(false),
                    after: () => component.loading = false
                });
            })
        });
    }

}
