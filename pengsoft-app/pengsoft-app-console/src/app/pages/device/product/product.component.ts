import { Component, ViewChild } from '@angular/core';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { EntityComponent } from 'src/app/components/commons/entity.component';
import { InputComponent } from 'src/app/components/commons/input/input.component';
import { ProductService } from 'src/app/services/device/product.service';
import { DictionaryItemService } from 'src/app/services/system/dictionary-item.service';
import { EntityUtils } from 'src/app/utils/entity-utils';
import { FieldUtils } from 'src/app/utils/field-utils';
import { EditOneToManyComponent } from 'src/app/components/commons/edit-one-to-many/edit-one-to-many.component';
import { ProductConfigComponent } from '../product-config/product-config.component';

@Component({
    selector: 'app-product',
    templateUrl: './product.component.html',
    styleUrls: ['./product.component.scss']
})
export class ProductComponent extends EntityComponent<ProductService> {

    @ViewChild('configsComponent', { static: true }) configsComponent: EditOneToManyComponent;

    constructor(
        private dictionaryItem: DictionaryItemService,
        protected entity: ProductService,
        protected modal: NzModalService,
        protected message: NzMessageService
    ) {
        super(entity, modal, message);
    }

    initFields(): void {
        this.fields = [
            FieldUtils.buildTreeSelect({
                code: 'category', name: '类别',
                list: { width: 200, align: 'center' },
                edit: {
                    required: true,
                    input: {
                        load: (component: InputComponent) => this.dictionaryItem.findAllByTypeCode('product_category', null, {
                            success: (res: any) => component.edit.input.options = EntityUtils.convertListToTree(res)
                        })
                    }
                }
            }),
            FieldUtils.buildTextForCode({ width: 300 }),
            FieldUtils.buildTextForName(),
            FieldUtils.buildTextarea({ code: 'contact', name: '联系方式', edit: { input: { placeholder: '维修联系方式' } } })
        ];
    }

    initListActionButtons(): void {
        super.initListActionButtons();
        this.listActionButtons.splice(0, 0, {
            name: '配置', type: 'link', divider: true, width: 47, action: (row: any) => this.editConfigs(row), authority: 'device::product_config::find_all'
        });
    }

    editConfigs(row: any): void {
        this.configsComponent.component = ProductConfigComponent;
        this.configsComponent.params = { title: row.name, product: row };
        this.configsComponent.show();
    }

}
