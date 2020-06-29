import { Component, Input, OnInit } from '@angular/core';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { BeanComponent } from 'src/app/components/commons/bean.component';
import { Field } from 'src/app/components/commons/form-item/field';
import { ProductConfigService } from 'src/app/services/device/product-config.service';
import { FieldUtils } from 'src/app/utils/field-utils';

@Component({
    selector: 'app-product-config',
    templateUrl: './product-config.component.html',
    styleUrls: ['./product-config.component.scss']
})
export class ProductConfigComponent extends BeanComponent<ProductConfigService> implements OnInit {

    @Input() product: any;

    constructor(
        protected bean: ProductConfigService,
        protected modal: NzModalService,
        protected message: NzMessageService
    ) {
        super(bean, modal, message);
    }

    initFields(): void {
        this.fields = [
            FieldUtils.buildSelect({ code: 'product', name: '产品', list: { visible: false }, edit: { visible: false } }),
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
        this.filterForm = { 'product.id': this.product.id };
        this.editForm = { product: this.product };
    }

    afterInit(): void {
        this.initForm();
        this.list();
    }

    list(): void {
        this.bean.findAll(this.filterForm, {
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
        if (!this.editForm.id && this.product) {
            this.editForm.product = this.product;
        }
    }

    afterFilterFormReset(): void {
        if (this.product) {
            this.filterForm = { 'product.id': this.product.id };
        }
        this.list();
    }

}
