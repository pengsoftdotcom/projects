import { Component, OnInit, ViewChild } from '@angular/core';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { EditOneToManyComponent } from 'src/app/components/commons/edit-one-to-many/edit-one-to-many.component';
import { InputComponent } from 'src/app/components/commons/input/input.component';
import { TreeEntityComponent } from 'src/app/components/commons/tree-entity.component';
import { OrganizationService } from 'src/app/services/basedata/organization.service';
import { DictionaryItemService } from 'src/app/services/system/dictionary-item.service';
import { EntityUtils } from 'src/app/utils/entity-utils';
import { FieldUtils } from 'src/app/utils/field-utils';
import { DepartmentComponent } from '../department/department.component';
import { PostComponent } from '../post/post.component';
import { PersonComponent } from '../person/person.component';

@Component({
    selector: 'app-organization',
    templateUrl: './organization.component.html',
    styleUrls: ['./organization.component.scss']
})
export class OrganizationComponent extends TreeEntityComponent<OrganizationService> implements OnInit {

    @ViewChild('postsComponent', { static: true }) postsComponent: EditOneToManyComponent;

    @ViewChild('departmentsComponent', { static: true }) departmentsComponent: EditOneToManyComponent;

    constructor(
        private dictionaryItem: DictionaryItemService,
        protected entity: OrganizationService,
        protected modal: NzModalService,
        protected message: NzMessageService
    ) {
        super(entity, modal, message);
    }

    get lazy(): boolean {
        return false;
    }

    get parentParams(): any {
        return null;
    }

    initFields(): void {
        super.initFields();
        PersonComponent.prototype.dictionaryItem = this.dictionaryItem;
        PersonComponent.prototype.initFields();
        PersonComponent.prototype.fields.forEach(field => {
            switch (field.code) {
                case 'nickname':
                case 'gender':
                    field.list.visible = false;
                    break;
                default: break;
            }
        });
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
            }),
            FieldUtils.buildText({ code: 'admin', name: '管理员', children: PersonComponent.prototype.fields })
        );
    }

    initListActionButtons(): void {
        super.initListActionButtons();
        this.listActionButtons.splice(0, 0, {
            name: '部门', type: 'link', divider: true, width: 47, authority: 'basedata::department::find_all',
            action: (row: any) => this.editDepartments(row)
        }, {
            name: '职务', type: 'link', divider: true, width: 47, authority: 'basedata::post::find_all',
            action: (row: any) => this.editPosts(row)
        });
    }

    beforeEditFormFilled(): void {
        super.beforeEditFormFilled();
        this.editForm.admin = {};
    }

    editDepartments(organization: any): void {
        this.departmentsComponent.component = DepartmentComponent;
        this.departmentsComponent.params = { title: organization.name, organization };
        this.departmentsComponent.show();
    }

    editPosts(organization: any): void {
        this.postsComponent.component = PostComponent;
        this.postsComponent.params = { title: organization.name, organization };
        this.postsComponent.show();
    }

}
