import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { BaseComponent } from 'src/app/components/commons/base.component';
import { UserDetailsService } from 'src/app/services/security/user-details.service';
import { FieldUtils } from 'src/app/utils/field-utils';

@Component({
    selector: 'app-forget-password',
    templateUrl: './forget-password.component.html',
    styleUrls: ['./forget-password.component.scss']
})
export class ForgetPasswordComponent extends BaseComponent implements OnInit {

    @ViewChild('content', { static: true }) content: TemplateRef<any>;

    fields = [
        FieldUtils.buildText({
            code: 'username',
            edit: {
                label: { visible: false },
                input: { placeholder: '录入已绑定的手机号码', prefixIcon: 'user' }
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
        private userDetails: UserDetailsService, private message: NzMessageService, private modal: NzModalService, private router: Router
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

    resetPassword(): void {
        this.errors = {};

        if (!this.form.passwordConfirm) {
            this.errors.passwordConfirm = ['请录入'];
        }

        if (this.form.passwordConfirm !== this.form.password) {
            this.errors.passwordConfirm = ['密码不一致'];
        }

        if (JSON.stringify(this.errors) !== '{}') {
            return;
        }

        this.userDetails.resetPassword(this.form.username, this.form.password, this.form.captcha, {
            errors: this.errors,
            success: () => this.message.info('重置成功，将立即跳转登录！').onClose.subscribe(() => this.signIn())
        });

    }

    signIn(): void {
        this.router.navigateByUrl('sign-in');
    }

    signUp(): void {
        this.router.navigateByUrl('sign-up');
    }

}
