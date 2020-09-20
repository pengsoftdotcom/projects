import { Component, ViewChild } from '@angular/core';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { EntityComponent } from 'src/app/components/commons/entity.component';
import { Edit } from 'src/app/components/commons/form-item/edit';
import { DeviceService } from 'src/app/services/device/device.service';
import { FieldUtils } from 'src/app/utils/field-utils';
import { EditOneToManyComponent } from 'src/app/components/commons/edit-one-to-many/edit-one-to-many.component';
import { DeviceConfigComponent } from '../device-config/device-config.component';
import { DeviceConfigService } from 'src/app/services/device/device-config.service';

@Component({
    selector: 'app-device',
    templateUrl: './device.component.html',
    styleUrls: ['./device.component.scss']
})
export class DeviceComponent extends EntityComponent<DeviceService> {

    @ViewChild('configsComponent', { static: true }) configsComponent: EditOneToManyComponent;

    constructor(
        protected entity: DeviceService,
        protected modal: NzModalService,
        protected message: NzMessageService
    ) {
        super(entity, modal, message);
    }

    initFields(): void {
        this.fields = [
            FieldUtils.buildSelect({
                code: 'purchaseBatchItem', name: '采购详情', list: { visible: false }, edit: {
                    disabled: true,
                    input: {
                        optionLabelRender: (edit: Edit, form: any) => form.purchaseBatchItem.purchaseBatch.name
                    }
                }
            }),
            FieldUtils.buildSelect({ code: 'product', name: '产品', edit: { disabled: true } }),
            FieldUtils.buildTextForCode({ align: 'center', width: 200 }),
            FieldUtils.buildTextForName(),
            FieldUtils.buildText({ code: 'host', name: '主机', list: { width: 160, align: 'center' } }),
            FieldUtils.buildNumber({ code: 'port', name: '端口', list: { width: 100, align: 'center' } }),
            FieldUtils.buildBooleanForLocked({ code: 'activated', name: '激活', edit: { disabled: false } }),
            FieldUtils.buildBooleanForLocked({ code: 'online', name: '在线', edit: { disabled: false } }),
            FieldUtils.buildJson({ code: 'info', name: '信息' })
        ];
    }

    initListActionButtons(): void {
        super.initListActionButtons();
        this.listActionButtons.splice(0, 0,
            { name: '编辑配置', type: 'link', divider: true, width: 75, action: (row: any) => this.editConfigs(row), authority: 'device::device_config::find_all' },
            { name: '推送配置', type: 'link', divider: true, width: 75, action: (row: any) => this.pushConfigs(row), authority: 'device::device::push_config' },
            { name: '推送系统时间', type: 'link', divider: true, width: 103, action: (row: any) => this.pushTime(row), authority: 'device::device::push_time' }
        );
    }

    pushConfigs(row: any): void {
        this.entity.pushConfig(row, {
            before: () => this.loading = true,
            success: (res: any) => this.message.info('推送配置成功'),
            after: () => this.loading = false
        });
    }

    pushTime(row: any): void {
        this.entity.pushTime(row, {
            before: () => this.loading = true,
            success: (res: any) => this.message.info('推送系统时间成功'),
            after: () => this.loading = false
        });
    }

    editConfigs(row: any): void {
        this.configsComponent.component = DeviceConfigComponent;
        this.configsComponent.params = { title: row.name, device: row };
        this.configsComponent.show();
    }

}
