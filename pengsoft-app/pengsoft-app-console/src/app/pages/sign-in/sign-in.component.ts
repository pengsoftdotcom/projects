import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { NzMessageService, NzModalService, NzTabChangeEvent } from 'ng-zorro-antd';
import { BaseComponent } from 'src/app/components/commons/base.component';
import { Field } from 'src/app/components/commons/form-item/field';
import { SecurityService } from 'src/app/services/commons/security.service';
import { OAuthService } from 'src/app/services/security-oauth/oauth.service';
import { UserDetailsService } from 'src/app/services/security/user-details.service';
import { FieldUtils } from 'src/app/utils/field-utils';

@Component({
    selector: 'app-sign-in',
    templateUrl: './sign-in.component.html',
    styleUrls: ['./sign-in.component.scss']
})
export class SignInComponent extends BaseComponent implements OnInit {

    @ViewChild('content', { static: true }) content: TemplateRef<any>;

    form: any = {
        grant_type: 'password'
    };

    errors: any = {};

    fields1: Array<Field> = [
        FieldUtils.buildText({
            code: 'username',
            edit: {
                label: { visible: false },
                input: { placeholder: '录入账号/手机号码/邮件/身份证', prefixIcon: 'user' }
            }
        }),
        FieldUtils.buildPassword({
            code: 'password',
            edit: {
                label: { visible: false },
                input: { placeholder: '录入登录密码', prefixIcon: 'lock' }
            }
        })
    ];

    fields2: Array<Field> = [
        FieldUtils.buildText({
            code: 'username',
            edit: {
                label: { visible: false },
                input: { placeholder: '录入已绑定的手机号码', prefixIcon: 'user' }
            }
        }),
        FieldUtils.buildCaptcha()
    ];

    constructor(
        private router: Router,
        private modal: NzModalService,
        private security: SecurityService,
        private oauth: OAuthService,
        private userDetails: UserDetailsService
    ) {
        super();
    }

    ngOnInit(): void {
        if (this.security.isAuthenticated()) {
            this.router.navigateByUrl('/dashboard');
        } else {
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
    }

    change(event: NzTabChangeEvent) {
        this.errors = {};
        if (event.index === 0) {
            this.form.grant_type = 'password';
        } else {
            this.form.grant_type = 'captcha';
        }
    }

    signIn(): void {
        this.errors = {};
        if (!this.form.username) {
            this.errors.username = ['请录入'];
        }
        if (this.form.grant_type === 'password' && !this.form.password) {
            this.errors.password = ['请录入'];
        }
        if (this.form.grant_type === 'captcha' && !this.form.captcha) {
            this.errors.captcha = ['请录入'];
        }
        if (JSON.stringify(this.errors) !== '{}') {
            return;
        }
        this.oauth.requestToken(this.form, {
            before: () => this.loading = true,
            success: (accessToken: any) => {
                this.security.accessToken = accessToken;
                this.userDetails.current({
                    before: () => this.loading = true,
                    success: (userDetails: any) => {
                        this.security.userDetails = userDetails;
                        window.location.reload();
                    },
                    after: () => this.loading = false
                });
            },
            after: () => this.loading = false
        });
    }

    signUp(): void {
        this.router.navigateByUrl('sign-up');
    }

    forgotPassword(): void {
        this.router.navigateByUrl('forget-password');
    }

}
