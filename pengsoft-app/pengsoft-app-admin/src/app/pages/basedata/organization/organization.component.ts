import { Component, OnInit, ViewChild } from '@angular/core';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { EditOneToManyComponent } from 'src/app/components/commons/edit-one-to-many/edit-one-to-many.component';
import { Field } from 'src/app/components/commons/form-item/field';
import { InputComponent } from 'src/app/components/commons/input/input.component';
import { TreeBeanComponent } from 'src/app/components/commons/tree-bean.component';
import { OrganizationService } from 'src/app/services/basedata/organization.service';
import { DictionaryItemService } from 'src/app/services/system/dictionary-item.service';
import { EntityUtils } from 'src/app/utils/entity-utils';
import { FieldUtils } from 'src/app/utils/field-utils';
import { DepartmentComponent } from '../department/department.component';
import { PostComponent } from '../post/post.component';

@Component({
    selector: 'app-organization',
    templateUrl: './organization.component.html',
    styleUrls: ['./organization.component.scss']
})
export class OrganizationComponent extends TreeBeanComponent<OrganizationService> implements OnInit {

    @ViewChild('postsComponent', { static: true }) postsComponent: EditOneToManyComponent;

    @ViewChild('departmentsComponent', { static: true }) departmentsComponent: EditOneToManyComponent;

    constructor(
        private dictionaryItem: DictionaryItemService,
        protected bean: OrganizationService,
        protected modal: NzModalService,
        protected message: NzMessageService
    ) {
        super(bean, modal, message);
    }

    get lazy(): boolean {
        return false;
    }

    get parentFilterForm(): any {
        return null;
    }

    ngOnInit(): void {
        super.ngOnInit();
        this.editComponent.width = '40%';
    }

    initFields(): void {
        super.initFields();
        this.fields.splice(1, 0,
            FieldUtils.buildTextForCode({ width: 300 }),
            FieldUtils.buildTextForName(),
            FieldUtils.buildText({ code: 'simpleName', name: '简称' }),
            FieldUtils.buildCascader({
                code: 'category', name: '类别',
                list: { width: 200, align: 'center' },
                edit: {
                    required: true,
                    input: {
                        load: (component: InputComponent) => {
                            this.dictionaryItem.findAllByTypeCode('organization_category', null, {
                                before: () => component.loading = true,
                                success: (res: any) => component.edit.input.options = EntityUtils.convertListToTree(res),
                                after: () => component.loading = false
                            });
                        }
                    }
                },
                filter: {}
            })
        );
    }

    initListActionButtons(): void {
        super.initListActionButtons();
        this.listActionButtons.splice(0, 0, {
            name: '职务', type: 'link', divider: true, width: 45,
            action: (row: any) => this.editPosts(row)
        }, {
            name: '部门', type: 'link', divider: true, width: 45,
            action: (row: any) => this.editDepartments(row)
        });
    }

    editPosts(row: any): void {
        this.departmentsComponent.component = PostComponent;
        this.departmentsComponent.params = { title: row.name, organization: row };
        this.departmentsComponent.show();
    }

    editDepartments(row: any): void {
        this.departmentsComponent.component = DepartmentComponent;
        this.departmentsComponent.params = { title: row.name, organization: row };
        this.departmentsComponent.show();
    }

}
