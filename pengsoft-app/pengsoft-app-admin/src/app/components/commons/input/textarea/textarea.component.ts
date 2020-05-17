import { Component, Input, OnInit } from '@angular/core';
import { InputComponent } from '../input.component';

@Component({
    selector: 'app-input-textarea',
    templateUrl: './textarea.component.html',
    styleUrls: ['./textarea.component.scss']
})
export class TextareaComponent extends InputComponent implements OnInit {

    ngOnInit(): void {
        if (!this.field.edit.input.rows) {
            this.field.edit.input.rows = 4;
        }
    }

}
