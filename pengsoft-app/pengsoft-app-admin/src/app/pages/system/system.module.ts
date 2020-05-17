import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ComponentsModule } from 'src/app/components/components.module';
import { RegionComponent } from './region/region.component';
import { SystemRoutingModule } from './system-routing.module';
import { AssetComponent } from './asset/asset.component';



@NgModule({
  declarations: [RegionComponent, AssetComponent],
  imports: [
    CommonModule,
    SystemRoutingModule,
    ComponentsModule
  ]
})
export class SystemModule { }
