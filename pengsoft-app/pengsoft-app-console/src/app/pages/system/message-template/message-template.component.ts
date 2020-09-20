import { Component } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { EntityComponent } from 'src/app/components/commons/entity.component';
import { Field } from 'src/app/components/commons/form-item/field';
import { MessageTemplateService } from 'src/app/services/message-template.service';
import { UserService } from 'src/app/services/security/user.service';
import { FieldUtils } from 'src/app/utils/field-utils';

@Component({
    selector: 'app-message-template',
    templateUrl: './message-template.component.html',
    styleUrls: ['./message-template.component.scss']
})
export class MessageTemplateComponent extends EntityComponent<MessageTemplateService> {

    constructor(
        private user: UserService,
        protected entity: MessageTemplateService,
        protected modal: NzModalService,
        protected message: NzMessageService
    ) {
        super(entity, modal, message);
    }

    initFields(): void {
        this.fields = [
            FieldUtils.buildTextForCode(),
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
            FieldUtils.buildTextarea({ code: 'content', name: '内容' }),
            FieldUtils.buildText({
                code: 'smsCode', name: '短信模版编码', list: {
                    width: 140, align: 'center',
                    render: (field: Field, row: any, sanitizer: DomSanitizer) => row[field.code] ? sanitizer.bypassSecurityTrustHtml(`<code>${row[field.code]}</code>`) : null
                }
            }),
            FieldUtils.buildText({ code: 'smsSignature', name: '短信模版签名', list: { width: 140 } })
        ];
    }

}
