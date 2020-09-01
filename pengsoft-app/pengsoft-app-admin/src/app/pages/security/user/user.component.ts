import { Component, ViewChild } from '@angular/core';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { EntityComponent } from 'src/app/components/commons/entity.component';
import { EditManyToManyComponent } from 'src/app/components/commons/edit-many-to-many/edit-many-to-many.component';
import { Field } from 'src/app/components/commons/form-item/field';
import { ResetPasswordComponent } from 'src/app/components/modal/reset-password/reset-password.component';
import { SetPrimaryRoleComponent } from 'src/app/components/modal/set-primary-role/set-primary-role.component';
import { RoleService } from 'src/app/services/security/role.service';
import { UserService } from 'src/app/services/security/user.service';
import { EntityUtils } from 'src/app/utils/entity-utils';
import { FieldUtils } from 'src/app/utils/field-utils';

@Component({
    selector: 'app-user',
    templateUrl: './user.component.html',
    styleUrls: ['./user.component.scss']
})
export class UserComponent extends EntityComponent<UserService> {

    buttons = [
        {
            name: '设置主要角色', size: 'default',
            authority: this.getAuthority('setPrimaryRole'),
            action: () => this.editPrimaryRole()
        },
        {
            name: '保存', type: 'primary', size: 'default',
            authority: this.getAuthority('grantRoles'),
            action: () => {
                const user = this.editForm;
                const roles = this.userRolesComponent.items.filter(item => item.direction === 'right').map(item => item.value);
                this.entity.grantRoles(user, roles, {
                    before: () => this.userRolesComponent.loading = true,
                    success: () => this.message.info('保存成功'),
                    after: () => this.userRolesComponent.loading = false
                });
            }
        }
    ];

    @ViewChild('userRolesComponent', { static: true }) userRolesComponent: EditManyToManyComponent;

    constructor(
        private role: RoleService,
        protected entity: UserService,
        protected modal: NzModalService,
        protected message: NzMessageService
    ) {
        super(entity, modal, message);
    }

    initFields(): void {
        this.fields = [
            FieldUtils.buildText({
                code: 'username', name: '账号',
                edit: {
                    required: true,
                    label: {
                        tooltip: '4到20位字符，支持数字, 小写字母, 大写字母和分隔符("- _ @ .")的组合'
                    }
                },
                filter: { label: { tooltip: null } }
            }),
            FieldUtils.buildText({ code: 'mobile', name: '手机' }),
            FieldUtils.buildText({ code: 'email', name: '邮件' }),
            FieldUtils.buildText({ code: 'mpOpenId', name: '微信Open ID' }),
            FieldUtils.buildPassword({
                code: 'password', name: '密码',
                edit: {
                    required: true,
                    visible: (form: any) => !form || !form.id,
                    label: {
                        tooltip: '6-20位字符，支持数字, 小写字母, 大写字母和标点符号的组合，至少含有其中2种'
                    }
                }
            }),
            FieldUtils.buildSelect({
                code: 'locale', name: '语言',
                list: {
                    width: 80, align: 'center',
                    render: (field: Field, row: any) => field.edit.input.options
                        .filter(option => EntityUtils.equals(option.value, row[field.code]))[0].label
                },
                edit: { input: { options: [{ label: '简体', value: 'zh_CN' }, { label: 'English', value: 'en_US' }] } },
                filter: {}
            }),
            FieldUtils.buildDatetime({ code: 'signedInAt', name: '登录时间', edit: { disabled: true, input: { placeholder: '尚未登录' } } }),
            FieldUtils.buildNumber({ code: 'signInFailureCount', name: '今日登录失败次数', list: { width: 150 } }),
            FieldUtils.buildDatetimeForExpiredAt(),
            FieldUtils.buildBooleanForEnabled()
        ];
    }

    initListActionButtons(): void {
        super.initListActionButtons();
        this.listActionButtons.splice(0, 0, {
            name: '重置密码',
            type: 'link',
            divider: true,
            width: 75,
            authority: this.getAuthority('resetPassword'),
            action: (row: any) => this.resetPassword(row)
        }, {
            name: '分配角色',
            type: 'link',
            divider: true,
            width: 75,
            authority: this.getAuthority('findAllUserRolesByUser'),
            action: (row: any) => this.editGrantedRoles(row)
        });
    }

    resetPassword(row: any): void {
        this.modal.create({
            nzBodyStyle: { padding: '16px', marginBottom: '-24px' },
            nzTitle: '重置密码',
            nzContent: ResetPasswordComponent,
            nzComponentParams: {
                form: { id: row.id }
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

    editGrantedRoles(row: any): void {
        this.editForm = row;
        this.userRolesComponent.treeData = [];
        this.userRolesComponent.show();
        this.role.findAll(null, {
            before: () => this.userRolesComponent.loading = true,
            success: (roles: any) => {
                this.userRolesComponent.items = roles.map(role => Object.assign({ title: role.name, key: role.id, value: role }));
                this.entity.findAllUserRolesByUser(row, {
                    success: (userRoles: any) => {
                        this.userRolesComponent.targetKeys = userRoles.map(userRole => userRole.role.id);
                        this.userRolesComponent.treeData = EntityUtils.convertListToTree(roles, entity => {
                            const node = EntityUtils.convertTreeEntityToTreeNode(entity);
                            node.expanded = true;
                            node.disabled = userRoles.some(userRole => userRole.role.id === node.key);
                            node.checked = node.disabled;
                            return node;
                        });
                    }
                });
            },
            after: () => this.userRolesComponent.loading = false
        });
    }

    editPrimaryRole(): void {
        this.entity.findAllUserRolesByUser(this.editForm, {
            success: (res: any) => this.modal.create({
                nzBodyStyle: { padding: '16px' },
                nzTitle: '设置主要角色',
                nzContent: SetPrimaryRoleComponent,
                nzComponentParams: { items: res },
                nzOnOk: component => new Promise(resolve => {
                    component.submit({
                        before: () => component.loading = true,
                        success: () => {
                            this.message.info('设置成功');
                            resolve(true);
                        },
                        failure: () => resolve(false),
                        after: () => component.loading = false
                    });
                })
            })
        });

    }

}
