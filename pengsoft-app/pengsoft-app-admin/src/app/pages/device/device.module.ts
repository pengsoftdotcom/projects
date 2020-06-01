import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ComponentsModule } from 'src/app/components/components.module';
import { DeviceRoutingModule } from './device-routing.module';
import { ProductComponent } from './product/product.component';
import { DeviceComponent } from './device/device.component';
import { BatchComponent } from './batch/batch.component';
import { NzButtonModule } from 'ng-zorro-antd';



@NgModule({
  declarations: [ProductComponent, DeviceComponent, BatchComponent],
  imports: [
    CommonModule,
    DeviceRoutingModule,
    ComponentsModule,
    NzButtonModule
  ]
})
export class DeviceModule { }
