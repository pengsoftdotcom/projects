import { Component, ViewChild } from '@angular/core';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { EntityComponent } from 'src/app/components/commons/entity.component';
import { EditOneToManyComponent } from 'src/app/components/commons/edit-one-to-many/edit-one-to-many.component';
import { ProductService } from 'src/app/services/device/product.service';
import { PurchaseBatchService } from 'src/app/services/device/purchase-batch.service';
import { FieldUtils } from 'src/app/utils/field-utils';
import { PurchaseBatchItemComponent } from '../purchase-batch-item/purchase-batch-item.component';


@Component({
    selector: 'app-purchase-batch',
    templateUrl: './purchase-batch.component.html',
    styleUrls: ['./purchase-batch.component.scss']
})
export class PurchaseBatchComponent extends EntityComponent<PurchaseBatchService> {

    @ViewChild('itemsComponent', { static: true }) itemsComponent: EditOneToManyComponent;

    constructor(
        private product: ProductService,
        protected entity: PurchaseBatchService,
        protected modal: NzModalService,
        protected message: NzMessageService
    ) {
        super(entity, modal, message);
    }

    initFields(): void {
        this.fields = [
            FieldUtils.buildTextForName(),
            FieldUtils.buildDate({ code: 'purchasedAt', name: '采购时间', edit: { input: { placeholder: '默认今天' } } })
        ];
    }

    initListActionButtons(): void {
        super.initListActionButtons();
        this.listActionButtons.splice(0, 0, {
            name: '详情', type: 'link', width: 47, divider: true,
            action: (row: any) => this.editItems(row), authority: 'device::purchase_batch_item::find_all'
        });
    }

    editItems(row: any): void {
        this.itemsComponent.component = PurchaseBatchItemComponent;
        this.itemsComponent.params = { title: row.name, purchaseBatch: row };
        this.itemsComponent.show();
    }

}
