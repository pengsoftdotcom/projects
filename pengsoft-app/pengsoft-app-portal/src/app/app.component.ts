import { Component, TemplateRef, ViewChild } from '@angular/core';
import { NzModalService } from 'ng-zorro-antd';
@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss']
})
export class AppComponent {

    keywords = '';

    menus = [{
        name: '产品与服务'
    }, {
        name: '解决方案'
    }, {
        name: '客户与案例'
    }, {
        name: '合作伙伴'
    }, {
        name: '关于我们'
    }];

    @ViewChild('search', { static: true }) searchTempateRef: TemplateRef<any>;

    constructor(private modal: NzModalService) {

    }

    showSearch(): void {
        const search = this.modal.create({
            nzContent: this.searchTempateRef,
            nzFooter: null,
            nzClosable: false,
            nzStyle: { top: 0 }
        });
    }

    clearKeywords(): void {
        this.keywords = '';
    }

}
