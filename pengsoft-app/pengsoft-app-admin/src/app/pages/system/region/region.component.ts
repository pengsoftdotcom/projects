import { Component } from '@angular/core';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { Field } from 'src/app/components/commons/form-item/field';
import { TreeBeanComponent } from 'src/app/components/commons/tree-bean.component';
import { RegionService } from 'src/app/services/system/region.service';
import { FieldUtils } from 'src/app/utils/field-utils';

@Component({
    selector: 'app-region',
    templateUrl: './region.component.html',
    styleUrls: ['./region.component.scss']
})
export class RegionComponent extends TreeBeanComponent<RegionService> {

    constructor(
        protected bean: RegionService,
        protected modal: NzModalService,
        protected message: NzMessageService
    ) {
        super(bean, modal, message);
    }

    get fields(): Array<Field> {
        return super.fields.concat([
            FieldUtils.buildTextForCode(),
            FieldUtils.buildTextForName(),
            FieldUtils.buildTexareaForRemark()
        ]);
    }

    get lazy(): boolean {
        return false;
    }

    get parentFilterForm(): any {
        return null;
    }

}
