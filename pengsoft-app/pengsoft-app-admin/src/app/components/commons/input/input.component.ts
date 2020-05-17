import { BaseComponent } from '../base.component';
import { Input, OnInit } from '@angular/core';
import { Field } from '../form-item/field';

export class InputComponent extends BaseComponent {

    rawValue: any;

    @Input() form = {};

    @Input() field: Field;

    isDisabled(): boolean {
        return this.field.edit.disabled === true;
    }

    getPlaceholder(): string {
        const placeholder = this.field.edit.input.placeholder;
        return placeholder ? placeholder : '';
    }

    modelChange(event: any): void {

    }

}
