import { Input, OnInit } from '@angular/core';
import { BaseComponent } from '../base.component';
import { Edit } from '../form-item/edit';

export class InputComponent extends BaseComponent implements OnInit {

    rawValue: any;

    @Input() form = {};

    @Input() edit: Edit;

    @Input() filterable = false;

    disabled = false;

    placeholder = '';


    ngOnInit(): void {
        this.initDisabled();
        this.initPlaceholder();
        if (this.edit.input.load) {
            this.edit.input.load(this);
        }
    }

    initDisabled(): void {
        if (!this.filterable) {
            if (typeof this.edit.disabled === 'function') {
                this.disabled = this.edit.disabled(this.form, this.edit);
            } else {
                this.disabled = this.edit.disabled === true;
            }
        }
    }

    initPlaceholder(): void {
        if (this.edit.input.placeholder) {
            this.placeholder = this.edit.input.placeholder;
        }
    }

    modelChange(event: any): void {

    }

}
