import { NgModule } from '@angular/core';
import { CloseCircleFill, CloseOutline, MenuOutline, SearchOutline } from '@ant-design/icons-angular/icons';
import { NzIconModule, NZ_ICONS } from 'ng-zorro-antd/icon';


const icons = [CloseCircleFill, CloseOutline, MenuOutline, SearchOutline];

@NgModule({
    imports: [NzIconModule],
    exports: [NzIconModule],
    providers: [
        { provide: NZ_ICONS, useValue: icons }
    ]
})
export class IconsProviderModule {
}
