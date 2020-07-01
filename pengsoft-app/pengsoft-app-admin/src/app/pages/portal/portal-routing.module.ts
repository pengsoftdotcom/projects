import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BannerComponent } from './banner/banner.component';


const routes: Routes = [
    {
        path: 'portal',
        data: { name: '门户管理', icon: 'global' },
        children: [
            { path: 'banner', component: BannerComponent, data: { code: 'portal::banner::find_all', name: '栏目' } },
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class PortalRoutingModule { }
