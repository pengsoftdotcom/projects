import { Component, OnChanges, SimpleChanges } from '@angular/core';
import { NzTreeNodeOptions } from 'ng-zorro-antd';
import { EntityUtils } from 'src/app/utils/entity-utils';
import { InputComponent } from '../input.component';

@Component({
    selector: 'app-input-cascader',
    templateUrl: './cascader.component.html',
    styleUrls: ['./cascader.component.scss']
})
export class CascaderComponent extends InputComponent implements OnChanges {

    ngOnChanges(changes: SimpleChanges): void {
        if (changes.form) {
            if (this.form[this.field.code]) {
                const id = this.form[this.field.code].id;
                const parentIds = this.form[this.field.code].parentIds;
                if (parentIds) {
                    this.rawValue = parentIds.split('::').concat(id);
                } else {
                    this.rawValue = [id];
                }
            }
        }
    }

    modelChange(event: any): void {
        if (this.rawValue && this.rawValue.length > 0) {
            const nodes = EntityUtils.findTreeNodes((this.field.edit.input.options as Array<NzTreeNodeOptions>), this.rawValue);
            this.form[this.field.code] = nodes[this.rawValue.length - 1].value;
        } else {
            this.form[this.field.code] = null;
        }
    }

}
