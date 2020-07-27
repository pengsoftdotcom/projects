import { Component, OnInit } from '@angular/core';
import { TreeEntityComponent } from 'src/app/components/commons/tree-entity.component';
import { BannerService } from 'src/app/services/portal/banner.service';
import { NzModalService, NzMessageService } from 'ng-zorro-antd';
import { FieldUtils } from 'src/app/utils/field-utils';

@Component({
    selector: 'app-banner',
    templateUrl: './banner.component.html',
    styleUrls: ['./banner.component.scss']
})
export class BannerComponent extends TreeEntityComponent<BannerService> {

    constructor(
        protected entity: BannerService,
        protected modal: NzModalService,
        protected message: NzMessageService
    ) {
        super(entity, modal, message);
    }

    get lazy(): boolean {
        return false;
    }

    get parentParams(): any {
        return null;
    }

    initFields(): void {
        this.fields.splice(1, 0,
            FieldUtils.buildTextForName(),
            FieldUtils.buildText({ code: 'link', name: '链接' }),
            FieldUtils.buildText({ code: 'style', name: '样式' })
        );
    }

}
