import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AssetComponent } from './asset/asset.component';
import { RegionComponent } from './region/region.component';


const routes: Routes = [
    {
        path: 'system',
        data: { code: 'security_oauth_client_admin', name: '系统设置', icon: 'setting' },
        children: [
            { path: 'region', component: RegionComponent, data: { code: 'system::region::find_all_by_parent', name: '行政区划' } },
            { path: 'asset', component: AssetComponent, data: { code: 'system::asset::find_page', name: '附件' } },
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class SystemRoutingModule { }
