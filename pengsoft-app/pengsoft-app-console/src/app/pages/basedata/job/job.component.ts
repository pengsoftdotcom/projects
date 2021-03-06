import { Component, OnInit, ViewChild, Input } from '@angular/core';
import { NzFormatEmitEvent, NzMessageService, NzModalService, NzTreeNodeOptions } from 'ng-zorro-antd';
import { Button } from 'src/app/components/commons/button/button';
import { EditManyToManyComponent } from 'src/app/components/commons/edit-many-to-many/edit-many-to-many.component';
import { InputComponent } from 'src/app/components/commons/input/input.component';
import { TreeEntityComponent } from 'src/app/components/commons/tree-entity.component';
import { DepartmentService } from 'src/app/services/basedata/department.service';
import { JobService } from 'src/app/services/basedata/job.service';
import { PostService } from 'src/app/services/basedata/post.service';
import { SecurityService } from 'src/app/services/commons/security.service';
import { RoleService } from 'src/app/services/security/role.service';
import { EntityUtils } from 'src/app/utils/entity-utils';
import { FieldUtils } from 'src/app/utils/field-utils';
import { SwitchOrganizationComponent } from 'src/app/components/modal/switch-organization/switch-organization.component';
import { Location } from '@angular/common';

@Component({
    selector: 'app-job',
    templateUrl: './job.component.html',
    styleUrls: ['./job.component.scss']
})
export class JobComponent extends TreeEntityComponent<JobService> implements OnInit {

    showSwitcher = true;

    organization: any;

    @Input() department: any;

    buttons: Array<Button> = [
        {
            name: '保存', type: 'primary', size: 'default',
            authority: this.getAuthority('grantRoles'),
            action: () => {
                const user = this.editForm;
                const roles = this.jobRolesComponent.items.filter(item => item.direction === 'right').map(item => item.value);
                this.entity.grantRoles(user, roles, {
                    before: () => this.jobRolesComponent.loading = true,
                    success: () => this.message.info('保存成功'),
                    after: () => this.jobRolesComponent.loading = false
                });
            }
        }
    ];

    @ViewChild('jobRolesComponent', { static: true }) jobRolesComponent: EditManyToManyComponent;

    constructor(
        private location: Location,
        private security: SecurityService,
        private role: RoleService,
        private post: PostService,
        private departmentService: DepartmentService,
        protected entity: JobService,
        protected modal: NzModalService,
        protected message: NzMessageService
    ) {
        super(entity, modal, message);
        this.organization = this.security.userDetails.organization;
    }

    get lazy(): boolean {
        return false;
    }

    get parentParams(): any {
        if (this.department) {
            return { 'department.organization.id': this.department.organization.id };
        } else {
            return null;
        }
    }

    ngOnInit(): void {
        this.showSwitcher = !this.organization && !this.department;
        super.ngOnInit();
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
                            this.post.findAll({ 'organization.id': this.organization.id }, {
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

    initListToolbarButtons(): void {
        super.initListToolbarButtons();
        if (this.showSwitcher) {
            this.listToolbarButtons.splice(1, 0,
                { name: '切换机构', type: 'link', authority: 'basedata::organization::find_all', action: () => this.switchOrganization() }
            );
        }
        this.listToolbarButtons.find(button => button.name === '新增').disabled = () => !this.department;
    }

    initListActionButtons(): void {
        super.initListActionButtons();
        this.listActionButtons.splice(0, 0,
            {
                name: '分配角色', type: 'link', divider: true, width: 75, authority: this.getAuthority('findAllJobRolesByJob'),
                action: (row: any) => this.editGrantedRoles(row)
            }
        );
    }

    afterInit(): void {
        if (this.department) {
            this.organization = this.department.organization;
            this.initForm();
            this.list();
        }

        if (!this.organization) {
            this.switchOrganization();
        } else {
            this.loadNavData();
        }
    }

    initForm(): void {
        if (this.organization) {
            this.filterForm['department.organization.id'] = this.organization.id;
        } else {
            delete this.filterForm['department.organization.id'];
        }
        if (this.department) {
            this.filterForm['department.id'] = this.department.id;
            this.editForm = { department: this.department };
        } else {
            delete this.filterForm['department.id'];
        }
    }

    editGrantedRoles(row: any): void {
        this.editForm = row;
        this.jobRolesComponent.treeData = [];
        this.jobRolesComponent.show();
        this.role.findAll(null, {
            before: () => this.jobRolesComponent.loading = true,
            success: (roles: any) => {
                this.jobRolesComponent.items = roles.map(role => Object.assign({ title: role.name, key: role.id, value: role }));
                this.entity.findAllUserRolesByUser(row, {
                    success: (userRoles: any) => {
                        this.jobRolesComponent.targetKeys = userRoles.map(userRole => userRole.role.id);
                        this.jobRolesComponent.treeData = EntityUtils.convertListToTree(roles, entity => {
                            const node = EntityUtils.convertTreeEntityToTreeNode(entity);
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
        if (this.allowLoadNavData) {
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
    }

    nav(event: NzFormatEmitEvent): void {
        if (this.organization && this.organization.id === event.node.key) {
            this.filterForm['department.organization.id'] = this.organization.id;
            this.department = null;
            delete this.filterForm['department.id'];
        } else {
            this.department = event.node.origin.value;
            this.initForm();
            delete this.filterForm['department.organization.id'];
        }
        this.list();
    }

}
