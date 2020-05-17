import { Component, Input, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { BaseComponent } from '../base.component';
import { NzDrawerService, NzDrawerRef } from 'ng-zorro-antd';
import { Field } from '../form-item/field';
import { Button } from '../button/button';

@Component({
    selector: 'app-edit',
    templateUrl: './edit.component.html',
    styleUrls: ['./edit.component.scss']
})
export class EditComponent extends BaseComponent implements OnInit {

    @Input() mode: 'drawer' | 'modal' = 'drawer';

    @Input() span = 24;

    @Input() form = {};

    @Input() fields: Array<Field> = [];

    visibleFields: Array<Field>;

    @Input() toolbarButtons: Array<Button> = [];

    @Input() errors = {};

    @ViewChild('content', { static: true }) content: TemplateRef<any>;

    formHeight = 0;

    drawerRef: NzDrawerRef;

    constructor(private drawer: NzDrawerService) {
        super();
        this.title = '编辑';
        this.width = '30%';
    }

    ngOnInit(): void {
        this.handleVisibleFields();
        this.handleFormHeight();
    }

    private handleFormHeight() {
        const totalHeight = window.innerHeight;
        const titleHeight = 55;
        const toolbarHeight = 65;
        this.formHeight = totalHeight - titleHeight - toolbarHeight;
    }

    private handleVisibleFields() {
        this.visibleFields = this.fields.filter(field => field.edit.visible);
        this.visibleFields.filter(field => field.children)
            .forEach(field => field.children = field.children.filter(subfield => subfield.edit.visible));
    }

    show(): void {
        this.drawerRef = this.drawer.create({
            nzBodyStyle: { padding: 0 },
            nzWidth: this.width,
            nzTitle: this.title,
            nzContent: this.content
        });
    }

    hide(): void {
        if (this.drawerRef) {
            this.drawerRef.close();
        }
    }

}
