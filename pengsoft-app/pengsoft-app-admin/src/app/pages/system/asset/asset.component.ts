import { Component, TemplateRef, ViewChild } from '@angular/core';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { BeanComponent } from 'src/app/components/commons/bean.component';
import { Button } from 'src/app/components/commons/button/button';
import { Field } from 'src/app/components/commons/form-item/field';
import { AssetService } from 'src/app/services/system/asset.service';
import { FieldUtils } from 'src/app/utils/field-utils';

@Component({
    selector: 'app-asset',
    templateUrl: './asset.component.html',
    styleUrls: ['./asset.component.scss']
})
export class AssetComponent extends BeanComponent<AssetService> {

    @ViewChild('content', { static: true }) content: TemplateRef<any>;

    constructor(
        protected bean: AssetService,
        protected modal: NzModalService,
        protected message: NzMessageService) {
        super(bean, modal, message);
    }

    get fields(): Array<Field> {
        return [
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
                filter: { disabled: false, input: { placeholder: '小于输入的值' } }
            }),
            FieldUtils.buildBooleanForLocked(),
        ];
    }

    get listToolbarButtons(): Array<Button> {
        const buttons = super.listToolbarButtons;
        buttons.splice(1, 1, {
            name: '上传', type: 'primary', action: () => {
                this.modal.create({
                    nzTitle: '上传',
                    nzContent: this.content,
                    nzCancelText: null,
                    nzOnOk: () => this.list()
                });
            }
        });
        return buttons;
    }

    get listActionButtons(): Array<Button> {
        const buttons = super.listActionButtons;
        buttons[0].name = '查看';
        return buttons;
    }

    get editToolbarButtons(): Array<Button> {
        return [];
    }

}
