import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { NzMessageService, NzModalService, NzTreeNodeOptions, NzFormatEmitEvent } from 'ng-zorro-antd';
import { EntityComponent } from 'src/app/components/commons/entity.component';
import { Field } from 'src/app/components/commons/form-item/field';
import { InputComponent } from 'src/app/components/commons/input/input.component';
import { ResetPasswordComponent } from 'src/app/components/modal/reset-password/reset-password.component';
import { SwitchOrganizationComponent } from 'src/app/components/modal/switch-organization/switch-organization.component';
import { JobService } from 'src/app/services/basedata/job.service';
import { StaffService } from 'src/app/services/basedata/staff.service';
import { SecurityService } from 'src/app/services/commons/security.service';
import { DictionaryItemService } from 'src/app/services/system/dictionary-item.service';
import { EntityUtils } from 'src/app/utils/entity-utils';
import { FieldUtils } from 'src/app/utils/field-utils';
import { PersonComponent } from '../person/person.component';
import { DepartmentService } from 'src/app/services/basedata/department.service';


@Component({
    selector: 'app-staff',
    templateUrl: './staff.component.html',
    styleUrls: ['./staff.component.scss']
})
export class StaffComponent extends EntityComponent<StaffService> implements OnInit {

    organization: any;

    department: any;

    navData: Array<NzTreeNodeOptions>;

    constructor(
        private location: Location,
        private dictionaryItem: DictionaryItemService,
        private job: JobService,
        private departmentService: DepartmentService,
        private security: SecurityService,
        protected entity: StaffService,
        protected modal: NzModalService,
        protected message: NzMessageService
    ) {
        super(entity, modal, message);
        this.organization = this.security.userDetails.organization;
    }

    initFields(): void {
        PersonComponent.prototype.dictionaryItem = this.dictionaryItem;
        PersonComponent.prototype.initFields();
        this.fields = [
            FieldUtils.buildText({ code: 'person', name: '人员', children: PersonComponent.prototype.fields }),
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
                code: 'primary', name: '主要', list: {
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
    }

    initListToolbarButtons(): void {
        super.initListToolbarButtons();
        if (!this.security.userDetails.organization) {
            this.listToolbarButtons.splice(1, 0, {
                name: '切换机构',
                type: 'link',
                authority: 'basedata::organization::find_all',
                action: () => this.switchOrganization()
            });
        }
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

    afterInit(): void {
        if (!this.organization) {
            this.switchOrganization();
        } else {
            this.filterForm['organization.id'] = this.organization.id;
            this.loadNavData();
        }
    }

    beforeEditFormFilled(): void {
        super.beforeEditFormFilled();
        this.editForm.person = {};
    }

    resetPassword(row: any): void {
        this.modal.create({
            nzBodyStyle: { padding: '16px', marginBottom: '-24px' },
            nzTitle: '重置密码',
            nzContent: ResetPasswordComponent,
            nzComponentParams: {
                form: { id: row.person.user.id }
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
                this.title = this.organization.name;
                this.afterInit();
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

    private loadNavData() {
        this.departmentService.findAll({ 'organization.id': this.organization.id }, {
            success: (res: any) => {
                this.navData = EntityUtils.convertListToTree(res);
                this.navData = [{
                    key: this.organization.id,
                    title: this.organization.name,
                    value: this.organization,
                    isLeaf: !this.navData || this.navData.length === 0,
                    children: this.navData
                }];
            }
        });
    }

    nav(event: NzFormatEmitEvent): void {
        if (this.organization && this.organization.id === event.node.key) {
            this.filterForm['organization.id'] = this.organization.id;
            delete this.filterForm['department.id'];
            this.department = null;
        } else {
            this.department = event.node.origin.value;
            this.filterForm['department.id'] = this.department.id;
            delete this.filterForm['organization.id'];
        }
        this.list();
    }

}
