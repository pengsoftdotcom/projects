import { Component, OnChanges, SimpleChanges, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { EntityUtils } from 'src/app/utils/entity-utils';
import { Option } from '../../form-item/option';
import { InputComponent } from '../input.component';

@Component({
    selector: 'app-input-select',
    templateUrl: './select.component.html',
    styleUrls: ['./select.component.scss']
})
export class SelectComponent extends InputComponent implements OnChanges {

    constructor(public sanitizer: DomSanitizer) { super(); }

    ngOnChanges(changes: SimpleChanges): void {
        if (changes.form && this.edit) {
            if (this.form[this.edit.code]) {
                if (this.edit.input.multiple) {
                    this.rawValue = this.form[this.edit.code].split(',');
                } else {
                    if (typeof this.form[this.edit.code] === 'object') {
                        this.rawValue = this.form[this.edit.code].id;
                        if (!this.edit.input.options.find(option => option.value === this.rawValue)) {
                            this.edit.input.options.push({
                                label: this.form[this.edit.code].name,
                                value: this.form[this.edit.code].id
                            });
                        }
                    } else {
                        this.rawValue = this.form[this.edit.code];
                    }
                }
            } else {
                this.rawValue = undefined;
            }
        }
    }

    compareWith(v1: any, v2: any): boolean {
        return EntityUtils.equals(v1, v2);
    }

    modelChange(event: any): void {
        let values: Array<string>;
        if (this.rawValue instanceof Array) {
            values = this.rawValue;
        } else {
            values = [this.rawValue];
        }
        if (values.length > 0) {
            if (this.edit.input.multiple) {
                this.form[this.edit.code] = values.join(',');
            } else {
                this.form[this.edit.code] = values[0];
            }
        } else {
            this.form[this.edit.code] = null;
        }
    }

    search(keywords: string): void {
        if (this.edit.input.load) {
            this.edit.input.load(this, keywords);
        }
    }

}
