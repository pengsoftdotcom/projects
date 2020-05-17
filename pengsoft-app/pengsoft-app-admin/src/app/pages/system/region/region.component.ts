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
    protected region: RegionService, protected modal: NzModalService, protected message: NzMessageService
  ) {
    super(region, modal, message);
  }

  get lazy(): boolean {
    return false;
  }

  get fields(): Array<Field> {
    return super.fields.concat([
      FieldUtils.buildText({ code: 'code', name: '编码', list: { filterable: true, sortable: true, sortPriority: 1 } }),
      FieldUtils.buildText({ code: 'name', name: '名称', list: { filterable: true, sortable: true } }),
      FieldUtils.buildTexarea({ code: 'remark', name: '备注' })
    ]);
  }

}
