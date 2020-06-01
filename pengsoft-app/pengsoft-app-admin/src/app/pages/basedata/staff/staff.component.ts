import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { BeanComponent } from 'src/app/components/commons/bean.component';
import { Field } from 'src/app/components/commons/form-item/field';
import { InputComponent } from 'src/app/components/commons/input/input.component';
import { ResetPasswordComponent } from 'src/app/components/modal/reset-password/reset-password.component';
import { SwitchOrganizationComponent } from 'src/app/components/modal/switch-organization/switch-organization.component';
import { JobService } from 'src/app/services/basedata/job.service';
import { StaffService } from 'src/app/services/basedata/staff.service';
import { DictionaryItemService } from 'src/app/services/system/dictionary-item.service';
import { EntityUtils } from 'src/app/utils/entity-utils';
import { FieldUtils } from 'src/app/utils/field-utils';
import { UserProfileComponent } from '../user-profile/user-profile.component';

@Component({
    selector: 'app-staff',
    templateUrl: './staff.component.html',
    styleUrls: ['./staff.component.scss']
})
export class StaffComponent extends BeanComponent<StaffService> implements OnInit {

    organization: any;

    constructor(
        private location: Location,
        private dictionaryItem: DictionaryItemService,
        private job: JobService,
        protected bean: StaffService,
        protected modal: NzModalService,
        protected message: NzMessageService
    ) {
        super(bean, modal, message);
        this.initFields();
        this.initListToolbarButtons();

    }

    ngOnInit(): void {
        if (!this.organization) {
            this.switchOrganization();
        }
    }

    initFields(): void {
        if (!this.dictionaryItem) {
            return;
        }
        UserProfileComponent.prototype.dictionaryItem = this.dictionaryItem;
        UserProfileComponent.prototype.initFields();
        this.fields = [
            FieldUtils.buildText({ code: 'userProfile', name: '用户信息', children: UserProfileComponent.prototype.fields }),
            FieldUtils.buildTreeSelect({
                code: 'job', name: '职位',
                list: { width: 100, align: 'center' },
                edit: {
                    input: {
                        lazy: false,
                        load: (component: InputComponent) => this.job.findAll({ 'department.organization.id': this.organization.id }, {
                            before: () => component.loading = true,
                            success: (res: any) => component.edit.input.options = EntityUtils.convertListToTree(res),
                            after: () => component.loading = false
                        })
                    }
                }
            }),
            FieldUtils.buildBoolean({
                code: 'major', name: '主要', list: {
                    render: (field: Field, row: any, sanitizer: DomSanitizer) => {
                        if (row[field.code]) {
                            return sanitizer.bypassSecurityTrustHtml('<span style="color: #0b8235">是</span>');
                        } else {
                            return sanitizer.bypassSecurityTrustHtml('<span style="color: #ff4d4f">否</span>');
                        }
                    }
                }
            })
        ];
        FieldUtils.resetSubfieldEditCode(this.fields);
    }

    initListToolbarButtons(): void {
        super.initListToolbarButtons();
        this.listToolbarButtons.splice(1, 0, {
            name: '切换机构',
            type: 'link',
            action: () => this.switchOrganization()
        });
    }


    initListActionButtons(): void {
        super.initListActionButtons();
        this.listActionButtons.splice(0, 0, {
            name: '重置密码',
            type: 'link',
            divider: true,
            width: 73,
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
                form: { id: row.userProfile.user.id }
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

    switchOrganization(): void {
        this.modal.create({
            nzTitle: '切换机构',
            nzContent: SwitchOrganizationComponent,
            nzOnOk: component => {
                this.organization = component.form.organization;
                this.filterForm['organization.id'] = this.organization.id;
                this.list();
            },
            nzOnCancel: () => {
                if (!this.organization) {
                    this.modal.confirm({
                        nzTitle: '确认',
                        nzContent: '如不选择机构，将退回到最近一次打开页面',
                        nzOnOk: () => this.location.back()
                    });
                    return false;
                }
            }
        });
    }

}
