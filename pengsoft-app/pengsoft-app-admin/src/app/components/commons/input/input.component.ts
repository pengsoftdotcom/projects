import { BaseComponent } from '../base.component';
import { Input, OnInit } from '@angular/core';
import { Field } from '../form-item/field';

export class InputComponent extends BaseComponent implements OnInit {

    rawValue: any;

    @Input() form = {};

    @Input() field: Field;

    ngOnInit(): void {
        if (this.field.edit.input.load) {
            this.field.edit.input.load(this);
        }
    }

    get disabled(): boolean {
        return this.field.edit.disabled === true;
    }

    get placeholder(): string {
        const placeholder = this.field.edit.input.placeholder;
        return placeholder ? placeholder : '';
    }

    modelChange(event: any): void {

    }

}
