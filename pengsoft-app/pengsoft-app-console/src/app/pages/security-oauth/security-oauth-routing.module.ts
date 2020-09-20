import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientComponent } from './client/client.component';


const routes: Routes = [
    {
        path: 'security-oauth',
        data: { name: '授权设置', icon: 'safety-certificate' },
        children: [
            { path: 'client', component: ClientComponent, data: { code: 'security_oauth::client::find_page', name: '客户端' } }
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class SecurityOAuthRoutingModule { }
