import { Component, OnInit, ViewChild } from '@angular/core';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { EditOneToManyComponent } from 'src/app/components/commons/edit-one-to-many/edit-one-to-many.component';
import { Field } from 'src/app/components/commons/form-item/field';
import { TreeBeanComponent } from 'src/app/components/commons/tree-bean.component';
import { DepartmentService } from 'src/app/services/basedata/department.service';
import { FieldUtils } from 'src/app/utils/field-utils';
import { JobComponent } from '../job/job.component';
import { Button } from 'src/app/components/commons/button/button';


@Component({
    selector: 'app-department',
    templateUrl: './department.component.html',
    styleUrls: ['./department.component.scss']
})
export class DepartmentComponent extends TreeBeanComponent<DepartmentService> implements OnInit {

    organization: any;

    @ViewChild('jobsComponent', { static: true }) jobsComponent: EditOneToManyComponent;

    constructor(
        protected bean: DepartmentService,
        protected modal: NzModalService,
        protected message: NzMessageService
    ) {
        super(bean, modal, message);
    }

    ngOnInit(): void {
        if (this.organization) {
            this.filterForm = { 'organization.id': this.organization.id };
            this.editForm = { organization: this.organization };
        }
        super.list();
    }

    get lazy(): boolean {
        return false;
    }

    get parentFilterForm(): any {
        if (this.organization) {
            return { 'organization.id': this.organization.id };
        }
    }

    get fields(): Array<Field> {
        const fields = [
            FieldUtils.buildTreeSelect({ code: 'organization', name: '机构', list: { visible: false }, edit: { visible: false } }),
            FieldUtils.buildTextForName()
        ];
        const parent = super.fields[0];
        fields.splice(1, 0, parent);
        return fields;
    }

    get listActionButtons(): Array<Button> {
        const buttons: Array<Button> = [{
            name: '职位', type: 'link', divider: true, width: 45,
            action: (row: any) => this.editJobs(row)
        }];
        return buttons.concat(super.listActionButtons);
    }

    editJobs(row: any): void {
        this.jobsComponent.component = JobComponent;
        this.jobsComponent.params = { title: row.name, department: row };
        this.jobsComponent.show();
    }

    afterEditFormFilled(): void {
        if (!this.editForm.id && this.organization) {
            this.editForm.organization = this.organization;
        }
    }

    afterFilterReset(): void {
        if (this.organization) {
            this.filterForm = { 'organization.id': this.organization.id };
        } else {
            this.filterForm = {};
        }
        this.list();
    }

}
