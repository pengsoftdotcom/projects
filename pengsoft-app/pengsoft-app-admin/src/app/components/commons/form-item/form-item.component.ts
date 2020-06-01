import { Component, Input, OnInit } from '@angular/core';
import { InputType } from 'src/app/enums/input-type.enum';
import { BaseComponent } from '../base.component';
import { Field } from './field';
import { Edit } from './edit';

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

    @Input() filterable = false;

    edit: Edit;

    ngOnInit(): void {
        this.edit = this.filterable ? this.field.filter : this.field.edit;
        if (this.edit === undefined) {
            throw new Error('The edit is not configured.');
        }
        if (this.edit.label === undefined) {
            throw new Error('The label is not configured.');
        }
        if (this.edit.input === undefined) {
            throw new Error('The input is not configured.');
        }
        if (typeof this.edit.visible === 'function') {
            this.visible = this.edit.visible(this.form, this.edit);
        } else {
            this.visible = !(this.edit.visible === false);
        }
    }

    get status(): string {
        return JSON.stringify(this.errors) === '{}' ? null : this.errors[this.edit.code] ? 'error' : null;
    }

    get required(): boolean {
        return !this.filterable && this.edit.required;
    }

    get label(): string {
        let label = this.field.name;
        if (this.edit.label.value) {
            label = this.edit.label.value;
        }
        return label;
    }

    get labelVisible(): boolean {
        return !(this.edit.label.visible === false);
    }

    get inputVisible(): boolean {
        return !(this.edit.input.visible === false);
    }

}
