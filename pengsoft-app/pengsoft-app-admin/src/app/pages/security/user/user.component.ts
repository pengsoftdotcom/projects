import { Component, ViewChild } from '@angular/core';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { BeanComponent } from 'src/app/components/commons/bean.component';
import { Button } from 'src/app/components/commons/button/button';
import { EditManyToManyComponent } from 'src/app/components/commons/edit-many-to-many/edit-many-to-many.component';
import { Field } from 'src/app/components/commons/form-item/field';
import { ResetPasswordComponent } from 'src/app/components/modal/reset-password/reset-password.component';
import { UserService } from 'src/app/services/security/user.service';
import { FieldUtils } from 'src/app/utils/field-utils';
import { RoleService } from 'src/app/services/security/role.service';
import { EntityUtils } from 'src/app/utils/entity-utils';
import { SetMajorRoleComponent } from 'src/app/components/modal/set-major-role/set-major-role.component';
import { Option } from 'src/app/components/commons/form-item/option';

@Component({
    selector: 'app-user',
    templateUrl: './user.component.html',
    styleUrls: ['./user.component.scss']
})
export class UserComponent extends BeanComponent<UserService> {

    filterSpan = 12;

    @ViewChild('editManyToManyComponent', { static: true }) editManyToManyComponent: EditManyToManyComponent;

    constructor(
        private role: RoleService, protected user: UserService, protected modal: NzModalService, protected message: NzMessageService
    ) {
        super(user, modal, message);
    }

    get fields(): Array<Field> {
        return [
            FieldUtils.buildText({
                code: 'username', name: '账号', list: { filterable: true }, edit: {
                    label: {
                        tooltip: '4到20位字符，支持数字, 小写字母, 大写字母和分隔符("- _ @ .")的组合'
                    }
                }
            }),
            FieldUtils.buildPassword({
                code: 'password', name: '密码', edit: {
                    visible: (field: Field, form: any) => !form || !form.id,
                    label: {
                        tooltip: '6-20位字符，支持数字, 小写字母, 大写字母和标点符号的组合，至少含有其中2种'
                    }
                }
            }),
            FieldUtils.buildSelect({
                code: 'locale', name: '语言',
                list: {
                    width: 80,
                    align: 'center',
                    render: (field: Field, row: any) =>
                        field.edit.input.options.filter((option: Option) => option.value === row.locale)[0].label
                },
                edit: {
                    input: {
                        options: [{ label: '简体', value: 'zh_CN' }, { label: 'English', value: 'en_US' }]
                    }
                }
            }),
            FieldUtils.buildDatetime({ code: 'signedInAt', name: '登录时间', edit: { disabled: true } }),
            FieldUtils.buildNumber({ code: 'signInFailureCount', name: '今日登录失败次数', list: { width: 150 } }),
            FieldUtils.buildDatetime({ code: 'expiredAt', name: '过期时间', list: { filterable: true } }),
            FieldUtils.buildBooleanForEnabled()
        ].map(field => {
            field.edit.label.span = 6;
            return field;
        });
    }

    get listActionButtons(): Array<Button> {
        const buttons = super.listActionButtons;
        buttons.splice(1, 0, {
            name: '重置密码',
            type: 'link',
            divider: true,
            width: 73,
            action: (row: any) => this.resetPassword(row)
        }, {
            name: '分配角色',
            type: 'link',
            divider: true,
            width: 73,
            action: (row: any) => this.editGrantedRoles(row)
        });
        return buttons;
    }

    get buttons(): Array<Button> {
        return [
            {
                name: '设置主要角色', size: 'default',
                action: () => this.editMajorRole()
            },
            {
                name: '保存', type: 'primary', size: 'default',
                action: () => {
                    const user = this.editForm;
                    const roles = this.editManyToManyComponent.items.filter(item => item.direction === 'right').map(item => item.value);
                    this.user.grantRoles(user, roles, {
                        before: () => this.editManyToManyComponent.loading = true,
                        success: () => this.message.info('保存成功'),
                        after: () => this.editManyToManyComponent.loading = false
                    });
                }
            }
        ];
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
        this.editManyToManyComponent.treeData = [];
        this.editManyToManyComponent.show();
        this.role.findAll(null, {
            before: () => this.editManyToManyComponent.loading = true,
            success: (roles: any) => {
                this.editManyToManyComponent.items = roles.map(role => Object.assign({ title: role.name, key: role.id, value: role }));
                this.user.findAllUserRolesByUser(row, {
                    success: (userRoles: any) => {
                        this.editManyToManyComponent.targetKeys = userRoles.map(userRole => userRole.role.id);
                        this.editManyToManyComponent.treeData = EntityUtils.convertListToTree(roles, bean => {
                            const node = EntityUtils.convertTreeBeanToTreeNode(bean);
                            node.expanded = true;
                            node.disabled = userRoles.some(userRole => userRole.role.id === node.key);
                            node.checked = node.disabled;
                            return node;
                        });
                    }
                });
            },
            after: () => this.editManyToManyComponent.loading = false
        });
    }

    editMajorRole(): void {
        this.user.findAllUserRolesByUser(this.editForm.id, {
            success: (res: any) => this.modal.create({
                nzBodyStyle: { padding: '16px' },
                nzTitle: '设置主要角色',
                nzContent: SetMajorRoleComponent,
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
