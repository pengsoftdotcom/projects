import { Component, Input, OnInit } from '@angular/core';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { EntityComponent } from 'src/app/components/commons/entity.component';
import { InputComponent } from 'src/app/components/commons/input/input.component';
import { ProductService } from 'src/app/services/device/product.service';
import { PurchaseBatchItemService } from 'src/app/services/device/purchase-batch-item.service';
import { FieldUtils } from 'src/app/utils/field-utils';

@Component({
    selector: 'app-purchase-batch-item',
    templateUrl: './purchase-batch-item.component.html',
    styleUrls: ['./purchase-batch-item.component.scss']
})
export class PurchaseBatchItemComponent extends EntityComponent<PurchaseBatchItemService> implements OnInit {

    @Input() purchaseBatch: any;

    constructor(
        private product: ProductService,
        protected entity: PurchaseBatchItemService,
        protected modal: NzModalService,
        protected message: NzMessageService
    ) {
        super(entity, modal, message);
    }

    initFields(): void {
        this.fields = [
            FieldUtils.buildSelect({ code: 'purchaseBatch', name: '批次', list: { visible: false }, edit: { visible: false } }),
            FieldUtils.buildSelect({
                code: 'product', name: '产品', edit: {
                    required: true,
                    input: {
                        load: (component: InputComponent, keywords?: string) => {
                            const params: any = {};
                            if (keywords && keywords.length >= 2) {
                                params.name = keywords;
                            }
                            const page = { page: 1, size: 20, sort: [] };
                            if (keywords === undefined || keywords.length >= 2) {
                                this.product.findPage(params, page, {
                                    success: (res: any) => component.edit.input.options =
                                        res.content.map(value => Object.assign({ label: value.name, value }))
                                });
                            }
                        }
                    }
                }
            }),
            FieldUtils.buildNumber({ code: 'quantity', name: '采购数量', list: { width: 200 } }),
            FieldUtils.buildNumber({ code: 'usedQuantity', name: '使用数量', list: { width: 200 } })
        ];
    }

    initForm(): void {
        this.filterForm = { 'purchaseBatch.id': this.purchaseBatch.id };
        this.editForm = { purchaseBatch: this.purchaseBatch };
    }

    afterInit(): void {
        this.initForm();
        this.list();
    }

    afterEditFormFilled(): void {
        if (!this.editForm.id && this.purchaseBatch) {
            this.editForm.purchaseBatch = this.purchaseBatch;
        }
    }

    afterFilterFormReset(): void {
        if (this.purchaseBatch) {
            this.filterForm = { 'purchaseBatch.id': this.purchaseBatch.id };
        }
        this.list();
    }

}
