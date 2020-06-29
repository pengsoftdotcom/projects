import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { NzButtonModule } from 'ng-zorro-antd';
import { ComponentsModule } from 'src/app/components/components.module';
import { DeviceConfigComponent } from './device-config/device-config.component';
import { DeviceGroupComponent } from './device-group/device-group.component';
import { DeviceRoutingModule } from './device-routing.module';
import { DeviceComponent } from './device/device.component';
import { ProductConfigComponent } from './product-config/product-config.component';
import { ProductComponent } from './product/product.component';
import { PurchaseBatchItemComponent } from './purchase-batch-item/purchase-batch-item.component';
import { PurchaseBatchComponent } from './purchase-batch/purchase-batch.component';



@NgModule({
  declarations: [
    ProductComponent,
    DeviceComponent,
    PurchaseBatchComponent,
    PurchaseBatchItemComponent,
    ProductConfigComponent,
    DeviceConfigComponent,
    DeviceGroupComponent
  ],
  imports: [
    CommonModule,
    DeviceRoutingModule,
    ComponentsModule,
    NzButtonModule
  ]
})
export class DeviceModule { }
