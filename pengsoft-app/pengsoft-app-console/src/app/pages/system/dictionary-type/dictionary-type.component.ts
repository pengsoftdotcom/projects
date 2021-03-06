import { Component, ViewChild } from '@angular/core';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { EntityComponent } from 'src/app/components/commons/entity.component';
import { EditOneToManyComponent } from 'src/app/components/commons/edit-one-to-many/edit-one-to-many.component';
import { DictionaryTypeService } from 'src/app/services/system/dictionary-type.service';
import { FieldUtils } from 'src/app/utils/field-utils';
import { DictionaryItemComponent } from '../dictionary-item/dictionary-item.component';

@Component({
    selector: 'app-dictionary-type',
    templateUrl: './dictionary-type.component.html',
    styleUrls: ['./dictionary-type.component.scss']
})
export class DictionaryTypeComponent extends EntityComponent<DictionaryTypeService>  {

    @ViewChild('itemsComponent', { static: true }) itemsComponent: EditOneToManyComponent;

    constructor(
        protected entity: DictionaryTypeService,
        protected modal: NzModalService,
        protected message: NzMessageService
    ) {
        super(entity, modal, message);
    }

    initFields(): void {
        this.fields = [
            FieldUtils.buildTextForCode(),
            FieldUtils.buildTextForName(),
            FieldUtils.buildTextareaForRemark()
        ];
    }

    initListActionButtons(): void {
        super.initListActionButtons();
        this.listActionButtons.splice(0, 0, {
            name: '详情', type: 'link', divider: true, width: 47, action: (row: any) => this.editItems(row), authority: 'system::dictionary_item::find_all'
        });
    }

    editItems(row: any): void {
        this.itemsComponent.component = DictionaryItemComponent;
        this.itemsComponent.params = { title: row.name, type: row };
        this.itemsComponent.show();
    }

}
