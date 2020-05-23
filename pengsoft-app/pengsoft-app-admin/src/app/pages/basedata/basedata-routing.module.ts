import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { OrganizationComponent } from './organization/organization.component';


const routes: Routes = [
    {
        path: 'basedata',
        data: { name: '基础数据', icon: 'book' },
        children: [
            { path: 'basedata', component: OrganizationComponent, data: { code: 'basedata::organization::find_all', name: '机构' } },
            { path: 'user-profile', component: UserProfileComponent, data: { code: 'basedata::user_profile::find_page', name: '用户信息' } }
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class BasedataRoutingModule { }
