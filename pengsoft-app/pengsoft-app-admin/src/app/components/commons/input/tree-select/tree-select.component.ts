import { Component, OnChanges, SimpleChanges } from '@angular/core';
import { NzTreeNodeOptions } from 'ng-zorro-antd';
import { EntityUtils } from 'src/app/utils/entity-utils';
import { InputComponent } from '../input.component';

@Component({
    selector: 'app-input-tree-select',
    templateUrl: './tree-select.component.html',
    styleUrls: ['./tree-select.component.scss']
})
export class TreeSelectComponent extends InputComponent implements OnChanges {

    ngOnChanges(changes: SimpleChanges): void {
        if (changes.form) {
            if (this.form[this.field.code]) {
                if (this.field.edit.input.multiple) {
                    this.rawValue = this.form[this.field.code].split(',');
                } else {
                    this.rawValue = this.form[this.field.code].id;
                }
            }
        }
    }

    modelChange(event: any): void {
        let values: Array<any>;
        if (typeof this.rawValue === 'string') {
            values = [this.rawValue];
        } else {
            values = this.rawValue;
        }
        if (values && values.length > 0) {
            if (!this.field.edit.input.multiple) {
                this.form[this.field.code] = values[values.length - 1];
            } else {
                this.form[this.field.code] = values;
            }
            const tree = (this.field.edit.input.options as Array<NzTreeNodeOptions>);
            this.form[this.field.code] =
                EntityUtils.findTreeNodes(tree, this.form[this.field.code]).map(node => node.value);
            if (!this.field.edit.input.multiple) {
                this.form[this.field.code] = this.form[this.field.code][this.form[this.field.code].length - 1];
            }
        } else {
            this.form[this.field.code] = null;
        }
    }

}
