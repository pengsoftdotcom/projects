import { Component, ViewChild } from '@angular/core';
import { JsonEditorComponent, JsonEditorOptions } from 'ang-jsoneditor';
import { InputComponent } from '../input.component';

@Component({
    selector: 'app-input-json',
    templateUrl: './json.component.html',
    styleUrls: ['./json.component.scss']
})
export class JsonComponent extends InputComponent {

    jsonEditorOptions: JsonEditorOptions;

    @ViewChild(JsonEditorComponent, { static: false }) jsonEditor: JsonEditorComponent;

    constructor() {
        super();
        this.jsonEditorOptions = new JsonEditorOptions();
        this.jsonEditorOptions.modes = ['tree'];
        this.jsonEditorOptions.onChange = () => this.form[this.edit.code] = this.jsonEditor.get();
    }

}
