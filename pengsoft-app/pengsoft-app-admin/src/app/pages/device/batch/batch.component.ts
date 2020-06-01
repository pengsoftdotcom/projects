import { Component } from '@angular/core';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { BeanComponent } from 'src/app/components/commons/bean.component';
import { Field } from 'src/app/components/commons/form-item/field';
import { InputComponent } from 'src/app/components/commons/input/input.component';
import { BatchService } from 'src/app/services/device/batch.service';
import { ProductService } from 'src/app/services/device/product.service';
import { FieldUtils } from 'src/app/utils/field-utils';

@Component({
    selector: 'app-batch',
    templateUrl: './batch.component.html',
    styleUrls: ['./batch.component.scss']
})
export class BatchComponent extends BeanComponent<BatchService> {

    constructor(
        private product: ProductService,
        protected bean: BatchService,
        protected modal: NzModalService,
        protected message: NzMessageService
    ) {
        super(bean, modal, message);
    }

    initFields(): void {
        this.fields = [
            FieldUtils.buildSelect({
                code: 'product', name: '产品',
                list: { width: 200, align: 'center' },
                edit: {
                    required: true,
                    input: {
                        load: (component: InputComponent, event?: any) => {
                            this.product.findAll(event ? { name: event } : null, {
                                success: (res: any) => component.edit.input.options =
                                    res.map(value => Object.assign({ label: value.name, value }))
                            });
                        }
                    }
                },
                filter: {}
            }),
            FieldUtils.buildNumber({ code: 'purchaseQuantity', name: '采购数量' }),
            FieldUtils.buildNumber({ code: 'usageAmount', name: '使用数量' }),
            FieldUtils.buildNumber({ code: 'warranty', name: '保修期(月)' }),
            FieldUtils.buildDatetime({ code: 'purchasedAt', name: '采购时间', edit: { required: true } })
        ];
    }

}
