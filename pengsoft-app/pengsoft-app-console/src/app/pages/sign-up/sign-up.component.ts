import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { BaseComponent } from 'src/app/components/commons/base.component';
import { UserService } from 'src/app/services/security/user.service';
import { FieldUtils } from 'src/app/utils/field-utils';

@Component({
    selector: 'app-sign-up',
    templateUrl: './sign-up.component.html',
    styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent extends BaseComponent implements OnInit {

    @ViewChild('content', { static: true }) content: TemplateRef<any>;

    fields = [
        FieldUtils.buildText({
            code: 'username',
            edit: {
                label: { visible: false },
                input: { placeholder: '录入手机号码', prefixIcon: 'user' }
            }
        }),
        FieldUtils.buildPassword({
            code: 'password',
            edit: {
                label: { visible: false },
                input: { placeholder: '录入登录密码', prefixIcon: 'lock' }
            }
        }),
        FieldUtils.buildPassword({
            code: 'passwordConfirm',
            edit: {
                label: { visible: false },
                input: { placeholder: '再次录入密码', prefixIcon: 'lock' }
            }
        }),
        FieldUtils.buildCaptcha()
    ];

    form: any = {};

    errors: any = {};

    constructor(
        private user: UserService, private message: NzMessageService, private modal: NzModalService, private router: Router
    ) {
        super();
    }

    ngOnInit(): void {
        this.modal.create({
            nzContent: this.content,
            nzStyle: { top: '30%' },
            nzMaskStyle: { background: '#ffffff' },
            nzWidth: 400,
            nzClosable: false,
            nzMaskClosable: false,
            nzFooter: null
        });
    }

    signIn(): void {
        this.router.navigateByUrl('sign-in');
    }

    signUp(): void {

    }

}
