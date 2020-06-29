import { Component, ViewChild } from '@angular/core';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { EditOneToManyComponent } from 'src/app/components/commons/edit-one-to-many/edit-one-to-many.component';
import { TreeBeanComponent } from 'src/app/components/commons/tree-bean.component';
import { DeviceGroupService } from 'src/app/services/device/device-group.service';
import { FieldUtils } from 'src/app/utils/field-utils';
import { DeviceComponent } from '../device/device.component';

@Component({
    selector: 'app-device-group',
    templateUrl: './device-group.component.html',
    styleUrls: ['./device-group.component.scss']
})
export class DeviceGroupComponent extends TreeBeanComponent<DeviceGroupService> {


    @ViewChild('devicesComponent', { static: true }) devicesComponent: EditOneToManyComponent;

    constructor(
        protected bean: DeviceGroupService,
        protected modal: NzModalService,
        protected message: NzMessageService
    ) {
        super(bean, modal, message);
    }


    get lazy(): boolean {
        return false;
    }
    get parentParams(): any {
        return null;
    }

    initFields(): void {
        super.initFields();
        this.fields.splice(1, 0, FieldUtils.buildTextForName());
    }


    initListActionButtons(): void {
        super.initListActionButtons();
        this.listActionButtons.splice(0, 0, {
            name: '设备', type: 'link', divider: true, width: 47,
            action: (row: any) => this.editDevices(row)
        });
    }

    editDevices(row: any): void {
        this.devicesComponent.component = DeviceComponent;
        this.devicesComponent.params = { title: row.name, group: row };
        this.devicesComponent.show();
    }

}
