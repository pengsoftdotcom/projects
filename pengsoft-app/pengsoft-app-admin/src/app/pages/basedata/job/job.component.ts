import { Component, OnInit, ViewChild } from '@angular/core';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { Button } from 'src/app/components/commons/button/button';
import { EditManyToManyComponent } from 'src/app/components/commons/edit-many-to-many/edit-many-to-many.component';
import { InputComponent } from 'src/app/components/commons/input/input.component';
import { TreeBeanComponent } from 'src/app/components/commons/tree-bean.component';
import { JobService } from 'src/app/services/basedata/job.service';
import { PostService } from 'src/app/services/basedata/post.service';
import { RoleService } from 'src/app/services/security/role.service';
import { EntityUtils } from 'src/app/utils/entity-utils';
import { FieldUtils } from 'src/app/utils/field-utils';

@Component({
    selector: 'app-job',
    templateUrl: './job.component.html',
    styleUrls: ['./job.component.scss']
})
export class JobComponent extends TreeBeanComponent<JobService> implements OnInit {

    department: any;

    buttons: Array<Button> = [
        {
            name: '保存', type: 'primary', size: 'default',
            authority: this.getAuthority('grantRoles'),
            action: () => {
                const user = this.editForm;
                const roles = this.jobRolesComponent.items.filter(item => item.direction === 'right').map(item => item.value);
                this.bean.grantRoles(user, roles, {
                    before: () => this.jobRolesComponent.loading = true,
                    success: () => this.message.info('保存成功'),
                    after: () => this.jobRolesComponent.loading = false
                });
            }
        }
    ];

    @ViewChild('jobRolesComponent', { static: true }) jobRolesComponent: EditManyToManyComponent;

    constructor(
        private role: RoleService,
        private post: PostService,
        protected bean: JobService,
        protected modal: NzModalService,
        protected message: NzMessageService
    ) {
        super(bean, modal, message);
    }

    get lazy(): boolean {
        return false;
    }

    get parentFilterForm(): any {
        if (this.department) {
            return { 'department.organization.id': this.department.organization.id };
        }
    }

    ngOnInit(): void {
        this.initForm();
        this.list();
    }

    initForm(): void {
        if (this.department) {
            this.filterForm = { 'department.id': this.department.id };
            this.editForm = { department: this.department };
        }
    }

    initFields(): void {
        super.initFields();
        this.fields.splice(1, 0,
            FieldUtils.buildTreeSelect({ code: 'department', name: '部门', list: { visible: false }, edit: { visible: false } }),
            FieldUtils.buildTreeSelect({
                code: 'post', name: '职务', edit: {
                    required: true,
                    input: {
                        load: (component: InputComponent) => {
                            this.post.findAll({ 'organization.id': this.department.organization.id }, {
                                before: () => component.loading = true,
                                success: (res: any) => component.edit.input.options = EntityUtils.convertListToTree(res),
                                after: () => component.loading = false
                            });
                        }
                    }
                }
            }),
            FieldUtils.buildTextForName(),
            FieldUtils.buildBooleanForLocked({ code: 'departmentChief', name: '部门主管' }),
            FieldUtils.buildBooleanForLocked({ code: 'organizationChief', name: '机构主管' })
        );
    }

    initListActionButtons(): void {
        super.initListActionButtons();
        this.listActionButtons.splice(0, 0, {
            name: '分配角色',
            type: 'link',
            divider: true,
            width: 73,
            authority: this.getAuthority('findAllJobRolesByJob'),
            action: (row: any) => this.editGrantedRoles(row)
        });
    }

    editGrantedRoles(row: any): void {
        this.editForm = row;
        this.jobRolesComponent.treeData = [];
        this.jobRolesComponent.show();
        this.role.findAll(null, {
            before: () => this.jobRolesComponent.loading = true,
            success: (roles: any) => {
                this.jobRolesComponent.items = roles.map(role => Object.assign({ title: role.name, key: role.id, value: role }));
                this.bean.findAllUserRolesByUser(row, {
                    success: (userRoles: any) => {
                        this.jobRolesComponent.targetKeys = userRoles.map(userRole => userRole.role.id);
                        this.jobRolesComponent.treeData = EntityUtils.convertListToTree(roles, bean => {
                            const node = EntityUtils.convertTreeBeanToTreeNode(bean);
                            node.expanded = true;
                            node.disabled = userRoles.some(userRole => userRole.role.id === node.key);
                            node.checked = node.disabled;
                            return node;
                        });
                    }
                });
            },
            after: () => this.jobRolesComponent.loading = false
        });
    }

    afterEditFormFilled(): void {
        if (!this.editForm.id && this.department) {
            this.editForm.department = this.department;
        }
    }

    afterFilterFormReset(): void {
        if (this.department) {
            this.filterForm = { 'department.id': this.department.id };
        }
        this.list();
    }

}
