import { Component } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { EntityUtils } from 'src/app/utils/entity-utils';
import { InputComponent } from '../input.component';

@Component({
    selector: 'app-input-select',
    templateUrl: './select.component.html',
    styleUrls: ['./select.component.scss']
})
export class SelectComponent extends InputComponent {

    loaded = false;

    constructor(public sanitizer: DomSanitizer) { super(); }

    compareWith(bean1: any, bean2: any): boolean {
        return EntityUtils.equals(bean1, bean2);
    }

    openChange(open): void {
        if (open && !this.loaded && this.field.edit.input.load) {
            this.loaded = true;
            this.field.edit.input.load(this);
        }
    }

    search(keywords: string): void {
        if (keywords && keywords.length > 2) {
            this.field.edit.input.load(this);
        }
    }

}
