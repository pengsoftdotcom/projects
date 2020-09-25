import { Component } from '@angular/core';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { TreeEntityComponent } from 'src/app/components/commons/tree-entity.component';
import { SecurityService } from 'src/app/services/commons/security.service';
import { ColumnService } from 'src/app/services/portal/column.service';
import { FieldUtils } from 'src/app/utils/field-utils';


@Component({
    selector: 'app-column',
    templateUrl: './column.component.html',
    styleUrls: ['./column.component.scss']
})
export class ColumnComponent extends TreeEntityComponent<ColumnService> {

    sortable = false;

    constructor(
        private security: SecurityService,
        protected entity: ColumnService,
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
        this.fields.splice(1, 0,
            FieldUtils.buildText({ code: 'title', name: '标题' })
        );
    }

    initSortable() {
        this.sortable = this.security.hasAnyAuthority(this.getAuthority('sort'));
    }

    afterInit(): void {
        this.initSortable();
        this.list();
    }

}
