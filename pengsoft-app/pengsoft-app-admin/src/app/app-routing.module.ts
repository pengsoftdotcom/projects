import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { SignInComponent } from './pages/sign-in/sign-in.component';

const routes: Routes = [
    { path: '', pathMatch: 'full', redirectTo: '/sign-in' },
    { path: 'sign-in', component: SignInComponent },
    { path: 'dashboard', component: DashboardComponent, data: { name: '首页', icon: 'home' } }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
