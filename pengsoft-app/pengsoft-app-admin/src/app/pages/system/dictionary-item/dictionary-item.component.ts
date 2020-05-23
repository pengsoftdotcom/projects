import { Component, Input, OnInit } from '@angular/core';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { Field } from 'src/app/components/commons/form-item/field';
import { TreeBeanComponent } from 'src/app/components/commons/tree-bean.component';
import { DictionaryItemService } from 'src/app/services/system/dictionary-item.service';
import { FieldUtils } from 'src/app/utils/field-utils';
import { SecurityService } from 'src/app/services/commons/security.service';

@Component({
    selector: 'app-dictionary-item',
    templateUrl: './dictionary-item.component.html',
    styleUrls: ['./dictionary-item.component.scss']
})
export class DictionaryItemComponent extends TreeBeanComponent<DictionaryItemService> implements OnInit {

    @Input() type: any;

    sortable = false;

    constructor(
        private security: SecurityService,
        protected bean: DictionaryItemService,
        protected modal: NzModalService,
        protected message: NzMessageService
    ) {
        super(bean, modal, message);
    }

    ngOnInit(): void {
        if (this.type) {
            this.filterForm = { 'type.id': this.type.id };
            this.editForm = { type: this.type };
        }
        this.sortable = this.security.hasAnyAuthority(this.getAuthority('sort'));
        super.list();
    }

    get lazy(): boolean {
        return false;
    }

    get parentFilterForm(): any {
        if (this.type) {
            return { 'type.id': this.type.id };
        }
    }

    get fields(): Array<Field> {
        const fields = [
            FieldUtils.buildSelect({ code: 'type', name: '类型', list: { visible: false }, edit: { visible: false } }),
            FieldUtils.buildTextForCode(),
            FieldUtils.buildTextForName(),
            FieldUtils.buildTexareaForRemark()
        ];
        const parent = super.fields[0];
        fields.splice(1, 0, parent);
        return fields;
    }

    afterEditFormFilled(): void {
        if (!this.editForm.id && this.type) {
            this.editForm.type = this.type;
        }
    }

    afterFilterReset(): void {
        if (this.type) {
            this.filterForm = { 'type.id': this.type.id };
        } else {
            this.filterForm = {};
        }
        this.list();
    }

}
