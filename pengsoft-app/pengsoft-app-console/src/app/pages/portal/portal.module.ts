import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ComponentsModule } from 'src/app/components/components.module';
import { PortalRoutingModule } from './portal-routing.module';
import { ColumnComponent } from './column/column.component';



@NgModule({
    declarations: [ColumnComponent],
    imports: [
        CommonModule,
        PortalRoutingModule,
        ComponentsModule
    ]
})
export class PortalModule { }
