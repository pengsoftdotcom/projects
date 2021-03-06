import { Component, ViewChild } from '@angular/core';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { EditManyToManyComponent } from 'src/app/components/commons/edit-many-to-many/edit-many-to-many.component';
import { TreeEntityComponent } from 'src/app/components/commons/tree-entity.component';
import { AuthorityService } from 'src/app/services/security/authority.service';
import { RoleService } from 'src/app/services/security/role.service';
import { FieldUtils } from 'src/app/utils/field-utils';
import { Button } from 'src/app/components/commons/button/button';

@Component({
    selector: 'app-role',
    templateUrl: './role.component.html',
    styleUrls: ['./role.component.scss']
})
export class RoleComponent extends TreeEntityComponent<RoleService> {

    buttons: Array<Button> = [
        {
            name: '保存', type: 'primary', size: 'default',
            authority: this.getAuthority('grantAuthorities'),
            action: () => {
                const role = this.editForm;
                const authorities = this.editManyToManyComponent.items
                    .filter(item => item.direction === 'right')
                    .map(item => item.value);
                this.entity.grantAuthorities(role, authorities, {
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

    @ViewChild('editManyToManyComponent', { static: true }) editManyToManyComponent: EditManyToManyComponent;

    constructor(
        private authority: AuthorityService,
        protected entity: RoleService,
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
        this.fields.splice(1, 0,
            FieldUtils.buildTextForCode(),
            FieldUtils.buildTextForName(),
            FieldUtils.buildTextareaForRemark()
        );

    }

    initListActionButtons(): void {
        super.initListActionButtons();
        this.listActionButtons.splice(0, 0, {
            name: '分配权限',
            type: 'link',
            divider: true,
            width: 75,
            action: (row: any) => this.editGrantedAuthorities(row)
        });
    }

    editGrantedAuthorities(row: any): void {
        this.editForm = row;
        this.editManyToManyComponent.show();
        this.authority.findAll(null, {
            before: () => this.editManyToManyComponent.loading = true,
            success: (authorities: any) => {
                this.editManyToManyComponent.items =
                    authorities.map(authority => Object.assign({ title: authority.name, key: authority.id, value: authority }));
                this.entity.findAllRoleAuthoritiesByRole(row, {
                    success: (roleAuthorities: any) =>
                        this.editManyToManyComponent.targetKeys = roleAuthorities.map(roleAuthority => roleAuthority.authority.id)
                });
            },
            after: () => this.editManyToManyComponent.loading = false
        });
    }

}
