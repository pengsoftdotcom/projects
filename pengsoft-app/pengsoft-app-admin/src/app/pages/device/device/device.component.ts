import { Component, OnInit } from '@angular/core';
import { BeanComponent } from 'src/app/components/commons/bean.component';
import { DeviceService } from 'src/app/services/device/device.service';
import { NzModalService, NzMessageService } from 'ng-zorro-antd';
import { Field } from 'src/app/components/commons/form-item/field';

@Component({
    selector: 'app-device',
    templateUrl: './device.component.html',
    styleUrls: ['./device.component.scss']
})
export class DeviceComponent extends BeanComponent<DeviceService> {

    constructor(
        protected bean: DeviceService,
        protected modal: NzModalService,
        protected message: NzMessageService
    ) {
        super(bean, modal, message);
    }

    initFields(): void {

    }

}
