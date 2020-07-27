import { Component, Input, OnInit } from '@angular/core';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { TreeEntityComponent } from 'src/app/components/commons/tree-entity.component';
import { SecurityService } from 'src/app/services/commons/security.service';
import { DictionaryItemService } from 'src/app/services/system/dictionary-item.service';
import { FieldUtils } from 'src/app/utils/field-utils';

@Component({
    selector: 'app-dictionary-item',
    templateUrl: './dictionary-item.component.html',
    styleUrls: ['./dictionary-item.component.scss']
})
export class DictionaryItemComponent extends TreeEntityComponent<DictionaryItemService> implements OnInit {

    @Input() type: any;

    sortable = false;

    constructor(
        private security: SecurityService,
        protected entity: DictionaryItemService,
        protected modal: NzModalService,
        protected message: NzMessageService
    ) {
        super(entity, modal, message);
    }

    get lazy(): boolean {
        return false;
    }

    get parentParams(): any {
        return { 'type.id': this.type.id };
    }

    initFields(): void {
        super.initFields();
        this.fields.splice(1, 0,
            FieldUtils.buildSelect({ code: 'type', name: '类型', list: { visible: false }, edit: { visible: false } }),
            FieldUtils.buildTextForCode(),
            FieldUtils.buildTextForName(),
            FieldUtils.buildTextareaForRemark()
        );
    }

    initSortable() {
        this.sortable = this.security.hasAnyAuthority(this.getAuthority('sort'));
    }

    initForm(): void {
        this.filterForm = { 'type.id': this.type.id };
        this.editForm = { type: this.type };
    }

    afterInit(): void {
        this.initForm();
        this.initSortable();
        this.list();
    }

    afterEditFormFilled(): void {
        if (!this.editForm.id) {
            this.editForm.type = this.type;
        }
    }

    afterFilterFormReset(): void {
        this.filterForm = { 'type.id': this.type.id };
        this.list();
    }

}
