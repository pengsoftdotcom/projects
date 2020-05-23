import { Component, ViewChild } from '@angular/core';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { BeanComponent } from 'src/app/components/commons/bean.component';
import { Button } from 'src/app/components/commons/button/button';
import { EditOneToManyComponent } from 'src/app/components/commons/edit-one-to-many/edit-one-to-many.component';
import { Field } from 'src/app/components/commons/form-item/field';
import { DictionaryTypeService } from 'src/app/services/system/dictionary-type.service';
import { FieldUtils } from 'src/app/utils/field-utils';
import { DictionaryItemComponent } from '../dictionary-item/dictionary-item.component';

@Component({
    selector: 'app-dictionary-type',
    templateUrl: './dictionary-type.component.html',
    styleUrls: ['./dictionary-type.component.scss']
})
export class DictionaryTypeComponent extends BeanComponent<DictionaryTypeService>  {

    @ViewChild('itemsComponent', { static: true }) itemsComponent: EditOneToManyComponent;

    constructor(
        protected bean: DictionaryTypeService,
        protected modal: NzModalService,
        protected message: NzMessageService
    ) {
        super(bean, modal, message);
    }

    get fields(): Array<Field> {
        return [
            FieldUtils.buildTextForCode(),
            FieldUtils.buildTextForName(),
            FieldUtils.buildTexareaForRemark()
        ];
    }

    get listActionButtons(): Array<Button> {
        return ([{
            name: '详情', type: 'link', divider: true, width: 45, action: (row: any) => this.editItems(row), authority: 'system::dictionary_item::find_all'
        }] as Array<Button>).concat(super.listActionButtons);
    }

    editItems(row: any): void {
        this.itemsComponent.component = DictionaryItemComponent;
        this.itemsComponent.params = { title: row.name, type: row };
        this.itemsComponent.show();
    }

}
