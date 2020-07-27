import { Location } from '@angular/common';
import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { NzMessageService, NzModalRef, NzModalService } from 'ng-zorro-antd';
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

    form: any = {};

    fields: Array<Field> = [
        FieldUtils.buildText({
            code: 'username',
            edit: {
                label: { visible: false },
                input: { placeholder: '账号/手机/邮件/身份证', prefixIcon: 'user' }
            }
        }),
        FieldUtils.buildPassword({
            code: 'password',
            edit: {
                label: { visible: false },
                input: { placeholder: '请录入登录密码', prefixIcon: 'lock' }
            }
        }),
    ];

    constructor(
        private router: Router,
        private modal: NzModalService,
        private message: NzMessageService,
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

    signIn(): void {
        this.oauth.requestTokenByPassword(this.form.username, this.form.password, {
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
        this.message.info('暂未开放，敬请期待');
    }

    forgotPassword(): void {
        this.message.info('暂未开放，敬请期待');
    }

}
