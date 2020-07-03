import { registerLocaleData } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import zh from '@angular/common/locales/zh';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {
    NzButtonModule,
    NzCollapseModule,
    NzDividerModule,
    NzGridModule,
    NzInputModule,
    NzLayoutModule,
    NzListModule,
    NzMenuModule,
    NzModalModule,
    NZ_I18N,
    zh_CN
} from 'ng-zorro-antd';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { IconsProviderModule } from './icons-provider.module';


registerLocaleData(zh);

@NgModule({
    declarations: [
        AppComponent
    ],
    imports: [
        HttpClientModule,
        FormsModule,
        BrowserModule,
        BrowserAnimationsModule,
        AppRoutingModule,
        IconsProviderModule,
        NzButtonModule,
        NzCollapseModule,
        NzDividerModule,
        NzInputModule,
        NzGridModule,
        NzLayoutModule,
        NzListModule,
        NzMenuModule,
        NzModalModule,
    ],
    providers: [{ provide: NZ_I18N, useValue: zh_CN }],
    bootstrap: [AppComponent]
})
export class AppModule { }
