import { Component, OnInit } from '@angular/core';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { TreeBeanComponent } from 'src/app/components/commons/tree-bean.component';
import { PostService } from 'src/app/services/basedata/post.service';
import { FieldUtils } from 'src/app/utils/field-utils';

@Component({
    selector: 'app-post',
    templateUrl: './post.component.html',
    styleUrls: ['./post.component.scss']
})
export class PostComponent extends TreeBeanComponent<PostService> implements OnInit {

    organization: any;

    constructor(
        protected bean: PostService,
        protected modal: NzModalService,
        protected message: NzMessageService
    ) {
        super(bean, modal, message);
    }

    get lazy(): boolean {
        return false;
    }

    get parentFilterForm(): any {
        if (this.organization) {
            return { 'organization.id': this.organization.id };
        }
    }
    ngOnInit(): void {
        this.initForm();
        this.list();
    }


    initForm(): void {
        if (this.organization) {
            this.filterForm = { 'organization.id': this.organization.id };
            this.editForm = { organization: this.organization };
        }
    }

    initFields(): void {
        this.fields.splice(1, 0,
            FieldUtils.buildTreeSelect({ code: 'organization', name: '机构', list: { visible: false }, edit: { visible: false } }),
            FieldUtils.buildTextForName()
        );
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

}
