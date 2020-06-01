import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProductComponent } from './product/product.component';
import { DeviceComponent } from './device/device.component';
import { BatchComponent } from './batch/batch.component';


const routes: Routes = [
  {
    path: 'device',
    data: { name: '设备管理', icon: 'book' },
    children: [
      { path: 'product', component: ProductComponent, data: { code: 'device::product::find_page', name: '产品' } },
      { path: 'batch', component: BatchComponent, data: { code: 'device::batch::find_page', name: '批次' } },
      { path: 'device', component: DeviceComponent, data: { code: 'device::device::find_page', name: '设备' } }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DeviceRoutingModule { }
