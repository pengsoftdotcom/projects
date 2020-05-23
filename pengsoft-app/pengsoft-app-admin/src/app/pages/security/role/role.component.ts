import { Component, ViewChild } from '@angular/core';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { Button } from 'src/app/components/commons/button/button';
import { EditManyToManyComponent } from 'src/app/components/commons/edit-many-to-many/edit-many-to-many.component';
import { Field } from 'src/app/components/commons/form-item/field';
import { TreeBeanComponent } from 'src/app/components/commons/tree-bean.component';
import { AuthorityService } from 'src/app/services/security/authority.service';
import { RoleService } from 'src/app/services/security/role.service';
import { FieldUtils } from 'src/app/utils/field-utils';

@Component({
    selector: 'app-role',
    templateUrl: './role.component.html',
    styleUrls: ['./role.component.scss']
})
export class RoleComponent extends TreeBeanComponent<RoleService> {

    @ViewChild('editManyToManyComponent', { static: true }) editManyToManyComponent: EditManyToManyComponent;

    constructor(
        private authority: AuthorityService,
        protected bean: RoleService,
        protected modal: NzModalService,
        protected message: NzMessageService
    ) {
        super(bean, modal, message);
    }

    get fields(): Array<Field> {
        return super.fields.concat([
            FieldUtils.buildTextForCode(),
            FieldUtils.buildTextForName(),
            FieldUtils.buildTexareaForRemark()
        ]);

    }

    get lazy(): boolean {
        return false;
    }

    get parentFilterForm(): any {
        return null;
    }

    get listActionButtons(): Array<Button> {
        const buttons = super.listActionButtons;
        buttons.splice(1, 0, {
            name: '分配权限',
            type: 'link',
            divider: true,
            width: 73,
            action: (row: any) => this.editGrantedAuthorities(row)
        });
        return buttons;
    }

    get buttons(): Array<Button> {
        return [
            {
                name: '保存', type: 'primary', size: 'default',
                action: () => {
                    const role = this.editForm;
                    const authorities = this.editManyToManyComponent.items
                        .filter(item => item.direction === 'right')
                        .map(item => item.value);
                    this.bean.grantAuthorities(role, authorities, {
                        before: () => this.editManyToManyComponent.loading = true,
                        success: () => {
                            this.message.info('保存成功');
                            this.editManyToManyComponent.hide();
                        },
                        after: () => this.editManyToManyComponent.loading = false
                    });
                }
            }
        ];
    }

    editGrantedAuthorities(row: any): void {
        this.editForm = row;
        this.editManyToManyComponent.show();
        this.authority.findAll(null, {
            before: () => this.editManyToManyComponent.loading = true,
            success: (authorities: any) => {
                this.editManyToManyComponent.items =
                    authorities.map(authority => Object.assign({ title: authority.name, key: authority.id, value: authority }));
                this.bean.findAllRoleAuthoritiesByRole(row, {
                    success: (roleAuthorities: any) =>
                        this.editManyToManyComponent.targetKeys = roleAuthorities.map(roleAuthority => roleAuthority.authority.id)
                });
            },
            after: () => this.editManyToManyComponent.loading = false
        });
    }

}
