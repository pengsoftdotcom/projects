import { Component } from '@angular/core';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { BeanComponent } from 'src/app/components/commons/bean.component';
import { ProductService } from 'src/app/services/device/product.service';
import { Field } from 'src/app/components/commons/form-item/field';
import { DictionaryItemService } from 'src/app/services/system/dictionary-item.service';
import { FieldUtils } from 'src/app/utils/field-utils';
import { InputComponent } from 'src/app/components/commons/input/input.component';
import { EntityUtils } from 'src/app/utils/entity-utils';

@Component({
    selector: 'app-product',
    templateUrl: './product.component.html',
    styleUrls: ['./product.component.scss']
})
export class ProductComponent extends BeanComponent<ProductService> {

    constructor(
        private dictionaryItem: DictionaryItemService,
        protected bean: ProductService,
        protected modal: NzModalService,
        protected message: NzMessageService
    ) {
        super(bean, modal, message);
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
            FieldUtils.buildTextForName(),
            FieldUtils.buildTextarea({ code: 'contact', name: '联系方式', edit: { input: { placeholder: '维修联系方式' } } })
        ];
    }

}
