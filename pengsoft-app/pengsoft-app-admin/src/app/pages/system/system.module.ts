import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ComponentsModule } from 'src/app/components/components.module';
import { RegionComponent } from './region/region.component';
import { SystemRoutingModule } from './system-routing.module';
import { AssetComponent } from './asset/asset.component';
import { FormsModule } from '@angular/forms';
import { DictionaryTypeComponent } from './dictionary-type/dictionary-type.component';
import { DictionaryItemComponent } from './dictionary-item/dictionary-item.component';
import { CaptchaComponent } from './captcha/captcha.component';
import { MessageComponent } from './message/message.component';
import { MessageTemplateComponent } from './message-template/message-template.component';



@NgModule({
  declarations: [
    RegionComponent,
    AssetComponent,
    DictionaryTypeComponent,
    DictionaryItemComponent,
    CaptchaComponent,
    MessageComponent,
    MessageTemplateComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    SystemRoutingModule,
    ComponentsModule
  ]
})
export class SystemModule { }
