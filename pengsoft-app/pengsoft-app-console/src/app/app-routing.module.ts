import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { ForgetPasswordComponent } from './pages/forget-password/forget-password.component';
import { SignInComponent } from './pages/sign-in/sign-in.component';
import { SignUpComponent } from './pages/sign-up/sign-up.component';

const routes: Routes = [
    { path: '', pathMatch: 'full', redirectTo: '/sign-in' },
    { path: 'dashboard', component: DashboardComponent, data: { name: '首页', icon: 'home' } },
    { path: 'forget-password', component: ForgetPasswordComponent },
    { path: 'sign-in', component: SignInComponent },
    { path: 'sign-up', component: SignUpComponent }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
