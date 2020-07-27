import { Component } from '@angular/core';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { TreeEntityComponent } from 'src/app/components/commons/tree-entity.component';
import { RegionService } from 'src/app/services/system/region.service';
import { FieldUtils } from 'src/app/utils/field-utils';

@Component({
    selector: 'app-region',
    templateUrl: './region.component.html',
    styleUrls: ['./region.component.scss']
})
export class RegionComponent extends TreeEntityComponent<RegionService> {

    constructor(
        protected entity: RegionService,
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

        this.fields = [
            FieldUtils.buildCascaderForRegion(this.entity, { code: 'parent', name: '上级', list: { visible: false } }),
            FieldUtils.buildTextForCode(),
            FieldUtils.buildTextForName(),
            FieldUtils.buildTextareaForRemark()
        ];
    }

}
