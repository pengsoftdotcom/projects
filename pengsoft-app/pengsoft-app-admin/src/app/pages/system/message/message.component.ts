import { Component } from '@angular/core';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { BeanComponent } from 'src/app/components/commons/bean.component';
import { Field } from 'src/app/components/commons/form-item/field';
import { InputComponent } from 'src/app/components/commons/input/input.component';
import { MessageService } from 'src/app/services/message.service';
import { UserService } from 'src/app/services/security/user.service';
import { FieldUtils } from 'src/app/utils/field-utils';

@Component({
    selector: 'app-message',
    templateUrl: './message.component.html',
    styleUrls: ['./message.component.scss']
})
export class MessageComponent extends BeanComponent<MessageService> {

    constructor(
        protected user: UserService,
        protected bean: MessageService,
        protected modal: NzModalService,
        protected message: NzMessageService
    ) {
        super(bean, modal, message);
    }

    initFields(): void {
        this.fields = [
            FieldUtils.buildSelect({
                code: 'sender', name: '发件人',
                list: { width: 150, render: (field: Field, row: any) => row[field.code].username },
                edit: { input: { load: (component: InputComponent, keywords?: string) => this.loadOptionsForUser(component, keywords) } },
                filter: {}
            }),
            FieldUtils.buildSelect({
                code: 'recipient', name: '收件人',
                list: { width: 150, render: (field: Field, row: any) => row[field.code].username },
                edit: { input: { load: (component: InputComponent, keywords?: string) => this.loadOptionsForUser(component, keywords) } },
                filter: {}
            }),
            FieldUtils.buildDatetime({
                code: 'sentAt', name: '发送时间',
                filter: { input: { placeholder: '早于输入的时间' } }
            }),
            FieldUtils.buildDatetime({
                code: 'readAt', name: '阅读时间',
                filter: { input: { placeholder: '早于输入的时间' } }
            }),
            FieldUtils.buildSelect({
                code: 'types', name: '类型', list: {
                    render: (field: Field, row: any) => {
                        if (row.types) {
                            return row.types.split(',')
                                .map(type => field.edit.input.options.find(option => option.value === type).label)
                                .join(', ');
                        } else {
                            return null;
                        }
                    }
                }, edit: {
                    input: {
                        multiple: true,
                        options: [
                            { label: '内部消息', value: 'INTERNAL' },
                            { label: '手机短信', value: 'SMS' },
                            { label: '电子邮件', value: 'EMAIL' },
                            { label: '微信模版消息', value: 'MP_TEMPLATE' }
                        ]
                    }
                }
            }),
            FieldUtils.buildText({
                code: 'subject', name: '主题',
                filter: {}
            }),
            FieldUtils.buildTextarea({ code: 'content', name: '内容' })
        ];
    }


    private loadOptionsForUser(component: InputComponent, keywords?: string) {
        const params: any = {};
        if (keywords) {
            params.username = keywords;
        }
        this.user.findPage(params, { page: 0, size: 20, sort: [] }, {
            before: () => component.loading = true,
            success: (res: any) => component.edit.input.options
                = res.content.map((value: any) => Object.assign({ label: value.username, value })),
            after: () => component.loading = false
        });
    }

}
