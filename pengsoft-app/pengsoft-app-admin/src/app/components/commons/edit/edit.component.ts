import { Component, Input, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { NzDrawerRef, NzDrawerService } from 'ng-zorro-antd';
import { BaseComponent } from '../base.component';
import { Button } from '../button/button';
import { Field } from '../form-item/field';

@Component({
    selector: 'app-edit',
    templateUrl: './edit.component.html',
    styleUrls: ['./edit.component.scss']
})
export class EditComponent extends BaseComponent implements OnInit {

    @Input() mode: 'drawer' | 'modal' = 'drawer';

    @Input() span = 24;

    @Input() form: any = {};

    @Input() fields: Array<Field> = [];

    @Input() toolbarButtons: Array<Button> = [];

    @Input() errors: any = {};

    @ViewChild('content', { static: true }) content: TemplateRef<any>;

    formHeight = 0;

    drawerRef: NzDrawerRef;

    constructor(private drawer: NzDrawerService) {
        super();
        this.title = '编辑';
        this.width = '30%';
    }

    ngOnInit(): void {
        this.initFormHeight();
    }

    private initFormHeight() {
        const totalHeight = window.innerHeight;
        const titleHeight = 55;
        const toolbarHeight = 65;
        this.formHeight = totalHeight - titleHeight - toolbarHeight;
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
