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
  }

  isRequired(): boolean {
    return this.field.edit.required;
  }

  isVisible(): boolean {
    return !(this.field.edit.visible === false);
  }

  getLabel(): string {
    let label = this.field.name;
    if (this.field.edit.label.value) {
      label = this.field.edit.label.value;
    }
    return label;
  }

  isLabelVisible(): boolean {
    return !(this.field.edit.label.visible === false);
  }

  isInputVisible(): boolean {
    return !(this.field.edit.input.visible === false);
  }

}
