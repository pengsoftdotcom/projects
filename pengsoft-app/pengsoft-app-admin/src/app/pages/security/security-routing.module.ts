import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserComponent } from './user/user.component';


const routes: Routes = [
  {
    path: 'security',
    data: { name: '安全设置', icon: 'safety-certificate' },
    children: [
      { path: 'user', component: UserComponent, data: { name: '用户' } }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SecurityRoutingModule { }
