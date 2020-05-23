import { BaseComponent } from '../base.component';
import { Input, OnInit } from '@angular/core';
import { Field } from '../form-item/field';

export class InputComponent extends BaseComponent implements OnInit {

    rawValue: any;

    @Input() form = {};

    @Input() field: Field;

    @Input() filterable = false;

    ngOnInit(): void {
        if (this.field.edit.input.load) {
            this.field.edit.input.load(this);
        }
    }

    get disabled(): boolean {
        if (!this.filterable) {
            if (typeof this.field.edit.disabled === 'function') {
                return this.field.edit.disabled(this.field, this.form);
            } else {
                return this.field.edit.disabled === true;
            }
        } else {
            return false;
        }
    }

    get placeholder(): string {
        const placeholder = this.field.edit.input.placeholder;
        return placeholder ? placeholder : '';
    }

    modelChange(event: any): void {

    }

}
