import { NgModule } from '@angular/core';
import { NZ_ICONS, NzIconModule } from 'ng-zorro-antd/icon';

import {
    HomeOutline,
    SafetyOutline,
    SafetyCertificateOutline,
    UserOutline,
    LockOutline,
    SwitcherOutline,
    MenuFoldOutline,
    MenuUnfoldOutline,
    SettingOutline,
    UploadOutline,
    DeleteOutline,
    DownloadOutline,
    BookOutline,
    PlusOutline,
    ReloadOutline,
    GlobalOutline,
    MobileOutline
} from '@ant-design/icons-angular/icons';

const icons = [
    MenuFoldOutline,
    MenuUnfoldOutline,
    HomeOutline,
    SafetyOutline,
    SafetyCertificateOutline,
    UserOutline,
    LockOutline,
    SwitcherOutline,
    SettingOutline,
    UploadOutline,
    DeleteOutline,
    DownloadOutline,
    BookOutline,
    PlusOutline,
    ReloadOutline,
    GlobalOutline,
    MobileOutline
];

@NgModule({
    imports: [NzIconModule],
    exports: [NzIconModule],
    providers: [
        { provide: NZ_ICONS, useValue: icons }
    ]
})
export class IconsProviderModule {
}
