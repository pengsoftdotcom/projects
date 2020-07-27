import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DeviceGroupComponent } from './device-group/device-group.component';
import { DeviceComponent } from './device/device.component';
import { ProductComponent } from './product/product.component';
import { PurchaseBatchComponent } from './purchase-batch/purchase-batch.component';


const routes: Routes = [
    {
        path: 'device',
        data: { name: '设备管理', icon: 'mobile' },
        children: [
            { path: 'product', component: ProductComponent, data: { code: 'device::product::find_page', name: '产品' } },
            { path: 'purchase-batch', component: PurchaseBatchComponent, data: { code: 'device::purchase_batch::find_page', name: '批次' } },
            { path: 'device-group', component: DeviceGroupComponent, data: { code: 'device::device_group::find_page', name: '分组' } },
            { path: 'device', component: DeviceComponent, data: { code: 'device::device::find_page', name: '设备' } }
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class DeviceRoutingModule { }
