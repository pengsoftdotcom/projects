import { BaseComponent } from '../base.component';
import { Input, OnInit } from '@angular/core';
import { Field } from '../form-item/field';
import { Edit } from '../form-item/edit';

export class InputComponent extends BaseComponent implements OnInit {

    rawValue: any;

    @Input() form = {};

    @Input() edit: Edit;

    @Input() filterable = false;


    ngOnInit(): void {
        if (this.edit.input.load) {
            this.edit.input.load(this);
        }
    }

    get disabled(): boolean {
        if (!this.filterable) {
            if (typeof this.edit.disabled === 'function') {
                return this.edit.disabled(this.form, this.edit);
            } else {
                return this.edit.disabled === true;
            }
        } else {
            return false;
        }
    }

    get placeholder(): string {
        const placeholder = this.edit.input.placeholder;
        return placeholder ? placeholder : '';
    }

    modelChange(event: any): void {

    }

}
