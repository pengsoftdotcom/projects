import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ComponentsModule } from 'src/app/components/components.module';
import { PortalRoutingModule } from './portal-routing.module';
import { BannerComponent } from './banner/banner.component';



@NgModule({
    declarations: [BannerComponent],
    imports: [
        CommonModule,
        PortalRoutingModule,
        ComponentsModule
    ]
})
export class PortalModule { }
