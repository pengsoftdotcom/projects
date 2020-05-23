import { Component, OnChanges, SimpleChanges } from '@angular/core';
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
        if (changes.form) {
            if (this.form[this.field.code]) {
                if (this.field.edit.input.multiple) {
                    this.rawValue = this.form[this.field.code].split(',');
                } else {
                    if (typeof this.form[this.field.code] === 'object') {
                        this.rawValue = this.form[this.field.code].id;
                        if (!this.field.edit.input.options.find(option => option.value === this.rawValue)) {
                            this.field.edit.input.options.push({
                                label: this.form[this.field.code].name,
                                value: this.form[this.field.code].id
                            });
                        }
                    } else {
                        this.rawValue = this.form[this.field.code];
                    }
                }
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
        this.form[this.field.code] = this.field.edit.input.options.filter(option => values.some(value => value === option.value));
        if (this.form[this.field.code].length > 0) {
            if (this.field.edit.input.multiple) {
                this.form[this.field.code] = this.form[this.field.code].map((option: Option) => option.value).join(',');
            } else {
                this.form[this.field.code] = this.form[this.field.code][0].rawValue !== undefined ?
                    this.form[this.field.code][0].rawValue : this.form[this.field.code][0].value;
            }
        } else {
            this.form[this.field.code] = null;
        }
    }

    search(keywords: string): void {
        if (this.field.edit.input.load) {
            this.field.edit.input.load(this, keywords);
        }
    }

}
