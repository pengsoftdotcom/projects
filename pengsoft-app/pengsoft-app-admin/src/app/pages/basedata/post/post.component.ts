import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { TreeBeanComponent } from 'src/app/components/commons/tree-bean.component';
import { SwitchOrganizationComponent } from 'src/app/components/modal/switch-organization/switch-organization.component';
import { PostService } from 'src/app/services/basedata/post.service';
import { SecurityService } from 'src/app/services/commons/security.service';
import { FieldUtils } from 'src/app/utils/field-utils';

@Component({
    selector: 'app-post',
    templateUrl: './post.component.html',
    styleUrls: ['./post.component.scss']
})
export class PostComponent extends TreeBeanComponent<PostService> implements OnInit {

    organization: any;

    constructor(
        private location: Location,
        private security: SecurityService,
        protected bean: PostService,
        protected modal: NzModalService,
        protected message: NzMessageService
    ) {
        super(bean, modal, message);
        this.organization = this.security.userDetails.organization;
    }

    get lazy(): boolean {
        return false;
    }

    get parentParams(): any {
        if (this.organization) {
            return { 'organization.id': this.organization.id };
        }
    }

    initForm(): void {
        if (this.organization) {
            this.filterForm = { 'organization.id': this.organization.id };
        }
        this.editForm = { organization: this.organization };
    }

    initFields(): void {
        super.initFields();
        this.fields.splice(1, 0,
            FieldUtils.buildTreeSelect({ code: 'organization', name: '机构', list: { visible: false }, edit: { visible: false } }),
            FieldUtils.buildTextForName()
        );
    }

    initListToolbarButtons(): void {
        super.initListToolbarButtons();
        if (!this.security.userDetails.organization) {
            this.listToolbarButtons.splice(1, 0, {
                name: '切换机构',
                type: 'link',
                authority: 'basedata::organization::find_all',
                action: () => this.switchOrganization()
            });
        }
    }

    afterInit(): void {
        if (!this.organization) {
            this.switchOrganization();
        } else {
            this.initForm();
            this.list();
        }
    }

    afterEditFormFilled(): void {
        if (!this.editForm.id && this.organization) {
            this.editForm.organization = this.organization;
        }
    }

    afterFilterFormReset(): void {
        if (this.organization) {
            this.filterForm = { 'organization.id': this.organization.id };
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

}
