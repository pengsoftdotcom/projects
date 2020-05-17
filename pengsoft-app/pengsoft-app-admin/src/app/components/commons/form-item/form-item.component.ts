import { Component, Input, OnInit } from '@angular/core';
import { InputType } from 'src/app/enums/input-type.enum';
import { BaseComponent } from '../base.component';
import { Field } from './field';

@Component({
    selector: 'app-form-item',
    templateUrl: './form-item.component.html',
    styleUrls: ['./form-item.component.scss']
})
export class FormItemComponent extends BaseComponent implements OnInit {

    InputType = InputType;

    @Input() form = {};

    @Input() errors = {};

    @Input() field: Field;

    ngOnInit(): void {
        if (this.field.edit === undefined) {
            throw new Error('The edit is not configured.');
        }
        if (this.field.edit.label === undefined) {
            throw new Error('The label is not configured.');
        }
        if (this.field.edit.input === undefined) {
            throw new Error('The input is not configured.');
        }
        if (typeof this.field.edit.visible === 'function') {
            this.visible = this.field.edit.visible(this.field, this.form);
        } else {
            this.visible = !(this.field.edit.visible === false);
        }
    }

    get status(): string {
        return JSON.stringify(this.errors) === '{}' ? null : this.errors[this.field.code] ? 'error' : null;
    }

    get required(): boolean {
        return this.field.edit.required;
    }

    get label(): string {
        let label = this.field.name;
        if (this.field.edit.label.value) {
            label = this.field.edit.label.value;
        }
        return label;
    }

    get labelVisible(): boolean {
        return !(this.field.edit.label.visible === false);
    }

    get inputVisible(): boolean {
        return !(this.field.edit.input.visible === false);
    }

}
