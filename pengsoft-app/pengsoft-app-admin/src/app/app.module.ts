import { registerLocaleData } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import zh from '@angular/common/locales/zh';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {
    NzAvatarModule,
    NzButtonModule,
    NzDividerModule,
    NzDropDownModule,
    NzFormModule,
    NzLayoutModule,
    NzMenuModule,
    NzMessageModule,
    NzModalModule,
    NzSpinModule,
    NzRadioModule,
    NZ_I18N,
    zh_CN
} from 'ng-zorro-antd';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ComponentsModule } from './components/components.module';
import { DirectivesModule } from './directives/directives.module';
import { IconsProviderModule } from './icons-provider.module';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { SecurityModule } from './pages/security/security.module';
import { SignInComponent } from './pages/sign-in/sign-in.component';
import { SecurityOAuthModule } from './pages/security-oauth/security-oauth.module';
import { SystemModule } from './pages/system/system.module';
import { BasedataModule } from './pages/basedata/basedata.module';
import { DeviceModule } from './pages/device/device.module';
import { PortalModule } from './pages/portal/portal.module';

registerLocaleData(zh);

@NgModule({
    declarations: [
        AppComponent,
        DashboardComponent,
        SignInComponent
    ],
    imports: [
        BrowserModule,
        FormsModule,
        BrowserAnimationsModule,
        AppRoutingModule,
        ReactiveFormsModule,
        HttpClientModule,
        NzButtonModule,
        NzLayoutModule,
        NzMenuModule,
        NzFormModule,
        NzModalModule,
        NzMessageModule,
        NzDividerModule,
        NzAvatarModule,
        NzDropDownModule,
        NzSpinModule,
        NzRadioModule,
        IconsProviderModule,
        ComponentsModule,
        DirectivesModule,
        PortalModule,
        DeviceModule,
        BasedataModule,
        SystemModule,
        SecurityOAuthModule,
        SecurityModule
    ],
    providers: [{ provide: NZ_I18N, useValue: zh_CN }],
    bootstrap: [AppComponent]
})
export class AppModule { }
