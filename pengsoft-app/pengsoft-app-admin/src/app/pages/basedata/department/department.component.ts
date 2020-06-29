import { Location } from '@angular/common';
import { Component, OnInit, ViewChild } from '@angular/core';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { EditOneToManyComponent } from 'src/app/components/commons/edit-one-to-many/edit-one-to-many.component';
import { TreeBeanComponent } from 'src/app/components/commons/tree-bean.component';
import { SwitchOrganizationComponent } from 'src/app/components/modal/switch-organization/switch-organization.component';
import { DepartmentService } from 'src/app/services/basedata/department.service';
import { SecurityService } from 'src/app/services/commons/security.service';
import { FieldUtils } from 'src/app/utils/field-utils';
import { JobComponent } from '../job/job.component';


@Component({
    selector: 'app-department',
    templateUrl: './department.component.html',
    styleUrls: ['./department.component.scss']
})
export class DepartmentComponent extends TreeBeanComponent<DepartmentService> implements OnInit {

    organization: any;

    @ViewChild('jobsComponent', { static: true }) jobsComponent: EditOneToManyComponent;

    constructor(
        private location: Location,
        private security: SecurityService,
        protected bean: DepartmentService,
        protected modal: NzModalService,
        protected message: NzMessageService
    ) {
        super(bean, modal, message);
        this.organization = this.security.userDetails.organization;
    }

    get lazy(): boolean {
        return false;
    }

    get parentParams(): any {
        if (this.organization) {
            return { 'organization.id': this.organization.id };
        }
    }

    initFields(): void {
        super.initFields();
        this.fields.splice(1, 0,
            FieldUtils.buildTreeSelect({ code: 'organization', name: '机构', list: { visible: false }, edit: { visible: false } }),
            FieldUtils.buildTextForName(),
            FieldUtils.buildText({ code: 'simpleName', name: '简称' })
        );
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
            name: '职位', type: 'link', divider: true, width: 47,
            action: (row: any) => this.editJobs(row)
        });
    }

    initForm(): void {
        if (this.organization) {
            this.filterForm = { 'organization.id': this.organization.id };
        }
        this.editForm = { organization: this.organization };
    }

    afterInit(): void {
        if (!this.organization) {
            this.switchOrganization();
        } else {
            this.initForm();
            this.list();
        }
    }

    editJobs(row: any): void {
        this.jobsComponent.component = JobComponent;
        this.jobsComponent.width = '70%';
        this.jobsComponent.params = { title: row.name, department: row, allowLoadNavData: false };
        this.jobsComponent.show();
    }

    afterEditFormFilled(): void {
        if (!this.editForm.id && this.organization) {
            this.editForm.organization = this.organization;
        }
    }

    afterFilterFormReset(): void {
        if (this.organization) {
            this.filterForm = { 'organization.id': this.organization.id };
        }
        this.list();
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

}
