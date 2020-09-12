import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AssetComponent } from './asset/asset.component';
import { CaptchaComponent } from './captcha/captcha.component';
import { DictionaryTypeComponent } from './dictionary-type/dictionary-type.component';
import { MessageTemplateComponent } from './message-template/message-template.component';
import { MessageComponent } from './message/message.component';
import { RegionComponent } from './region/region.component';


const routes: Routes = [
    {
        path: 'system',
        data: { name: '系统设置', icon: 'setting' },
        children: [
            { path: 'region', component: RegionComponent, data: { code: 'system::region::find_all_by_parent', name: '行政区划' } },
            { path: 'asset', component: AssetComponent, data: { code: 'system::asset::find_page', name: '附件' } },
            {
                path: 'dictionary-type', component: DictionaryTypeComponent,
                data: { code: 'system::dictionary_type::find_page', name: '数据字典' }
            },
            { path: 'captcha', component: CaptchaComponent, data: { code: 'system::captcha::find_page', name: '验证码' } },
            { path: 'message', component: MessageComponent, data: { code: 'system::message::find_page', name: '消息' } },
            {
                path: 'message-template', component: MessageTemplateComponent,
                data: { code: 'system::message_template::find_page', name: '消息模版' }
            },
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class SystemRoutingModule { }
