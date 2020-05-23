import { Component, OnInit } from '@angular/core';
import { TreeBeanComponent } from 'src/app/components/commons/tree-bean.component';
import { JobService } from 'src/app/services/basedata/job.service';
import { NzModalService, NzMessageService } from 'ng-zorro-antd';
import { Field } from 'src/app/components/commons/form-item/field';
import { FieldUtils } from 'src/app/utils/field-utils';
import { InputComponent } from 'src/app/components/commons/input/input.component';
import { EntityUtils } from 'src/app/utils/entity-utils';
import { PostService } from 'src/app/services/basedata/post.service';

@Component({
    selector: 'app-job',
    templateUrl: './job.component.html',
    styleUrls: ['./job.component.scss']
})
export class JobComponent extends TreeBeanComponent<JobService> implements OnInit {

    department: any;

    constructor(
        private post: PostService,
        protected bean: JobService,
        protected modal: NzModalService,
        protected message: NzMessageService
    ) {
        super(bean, modal, message);
    }

    ngOnInit(): void {
        if (this.department) {
            this.filterForm = { 'department.id': this.department.id };
            this.editForm = { department: this.department };
        }
        super.list();
    }

    get lazy(): boolean {
        return false;
    }

    get parentFilterForm(): any {
        if (this.department) {
            return { 'department.organization.id': this.department.organization.id };
        }
    }

    get fields(): Array<Field> {
        const fields = [
            FieldUtils.buildTreeSelect({ code: 'department', name: '部门', list: { visible: false }, edit: { visible: false } }),
            FieldUtils.buildTreeSelect({
                code: 'post', name: '职务', edit: {
                    required: true,
                    input: {
                        load: (inputComponent: InputComponent) => {
                            this.post.findAll({ 'organization.id': this.department.organization.id }, {
                                before: () => inputComponent.loading = true,
                                success: (res: any) => inputComponent.field.edit.input.options = EntityUtils.convertListToTree(res),
                                after: () => inputComponent.loading = false
                            });
                        }
                    }
                }
            }),
            FieldUtils.buildTextForName()
        ];
        const parent = super.fields[0];
        fields.splice(1, 0, parent);
        return fields;
    }

    afterEditFormFilled(): void {
        if (!this.editForm.id && this.department) {
            this.editForm.department = this.department;
        }
    }

    afterFilterReset(): void {
        if (this.department) {
            this.filterForm = { 'department.id': this.department.id };
        } else {
            this.filterForm = {};
        }
        this.list();
    }

}
