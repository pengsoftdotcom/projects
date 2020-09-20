import { Component, TemplateRef, ViewChild } from '@angular/core';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { EntityComponent } from 'src/app/components/commons/entity.component';
import { AssetService } from 'src/app/services/system/asset.service';
import { FieldUtils } from 'src/app/utils/field-utils';

@Component({
    selector: 'app-asset',
    templateUrl: './asset.component.html',
    styleUrls: ['./asset.component.scss']
})
export class AssetComponent extends EntityComponent<AssetService> {

    @ViewChild('content', { static: true }) content: TemplateRef<any>;

    constructor(
        protected entity: AssetService,
        protected modal: NzModalService,
        protected message: NzMessageService) {
        super(entity, modal, message);
    }

    initFields(): void {
        this.fields = [
            FieldUtils.buildText({
                code: 'originalName', name: '原名称',
                edit: { disabled: true },
                filter: { disabled: false }
            }),
            FieldUtils.buildText({
                code: 'presentName', name: '现名称',
                list: { visible: false },
                edit: { disabled: true },
                filter: { disabled: false }
            }),
            FieldUtils.buildText({ code: 'storagePath', name: '存储地址', list: { visible: false }, edit: { disabled: true } }),
            FieldUtils.buildText({ code: 'accessPath', name: '访问地址', list: { visible: false }, edit: { disabled: true } }),
            FieldUtils.buildText({ code: 'contentType', name: 'MIME类型', edit: { disabled: true } }),
            FieldUtils.buildNumber({
                code: 'contentLength', name: '大小(B)', edit: { disabled: true },
                filter: { disabled: false, input: { placeholder: '小于录入的值' } }
            }),
            FieldUtils.buildBooleanForLocked(),
        ];
    }

    initListToolbarButtons(): void {
        super.initListToolbarButtons();
        this.listToolbarButtons.splice(1, 1, {
            name: '上传', type: 'primary', action: () => {
                this.modal.create({
                    nzTitle: '上传',
                    nzContent: this.content,
                    nzCancelText: null,
                    nzOnOk: () => this.list()
                });
            }
        });
    }

    initListActionButtons(): void {
        super.initListActionButtons();
        this.listActionButtons[0].name = '查看';
    }

    intEditToolbarButtons(): void {
        this.editToolbarButtons = [];
    }

}
