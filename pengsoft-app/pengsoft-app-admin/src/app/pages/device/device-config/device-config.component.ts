import { Component, OnInit, Input } from '@angular/core';
import { EntityComponent } from 'src/app/components/commons/entity.component';
import { DeviceConfigService } from 'src/app/services/device/device-config.service';
import { NzModalService, NzMessageService } from 'ng-zorro-antd';
import { FieldUtils } from 'src/app/utils/field-utils';
import { Field } from 'src/app/components/commons/form-item/field';

@Component({
    selector: 'app-device-config',
    templateUrl: './device-config.component.html',
    styleUrls: ['./device-config.component.scss']
})
export class DeviceConfigComponent extends EntityComponent<DeviceConfigService> implements OnInit {

    @Input() device: any;

    constructor(
        protected entity: DeviceConfigService,
        protected modal: NzModalService,
        protected message: NzMessageService
    ) {
        super(entity, modal, message);
    }

    initFields(): void {
        this.fields = [
            FieldUtils.buildSelect({ code: 'device', name: '设备', list: { visible: false }, edit: { visible: false } }),
            FieldUtils.buildTextForCode(),
            FieldUtils.buildTextForName(),
            FieldUtils.buildSelect({
                code: 'type', name: '类型',
                list: {
                    width: 100, align: 'center',
                    render: (field: Field, row: any) => field.edit.input.options.filter(option => option.value === row[field.code])[0].label
                },
                edit: {
                    required: true,
                    input: {
                        options: [
                            { label: '整数', value: 'int' },
                            { label: '浮点数', value: 'float' },
                            { label: '字符串', value: 'String' }
                        ]
                    }
                }
            }),
            FieldUtils.buildText({ code: 'value', name: '值' }),
            FieldUtils.buildTextareaForRemark()
        ];
    }

    initForm(): void {
        this.filterForm = { 'device.id': this.device.id };
        this.editForm = { device: this.device };
    }

    afterInit(): void {
        this.initForm();
        this.list();
    }

    list(): void {
        this.entity.findAll(this.filterForm, {
            before: () => this.listComponent.loading = true,
            success: (res: any) => {
                this.listComponent.allChecked = false;
                this.listComponent.indeterminate = false;
                this.listData = res;
            },
            after: () => this.listComponent.loading = false
        });
    }

    afterEditFormFilled(): void {
        if (!this.editForm.id && this.device) {
            this.editForm.device = this.device;
        }
    }

    afterFilterFormReset(): void {
        if (this.device) {
            this.filterForm = { 'device.id': this.device.id };
        }
        this.list();
    }


}
