import { Component } from '@angular/core';
import { BaseComponent } from 'src/app/components/commons/base.component';
import { Field } from 'src/app/components/commons/form-item/field';
import { SecurityOAuthService } from 'src/app/services/security-oauth/security-oauth.service';
import { FieldUtil } from 'src/app/utils/field-util';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss']
})
export class SignInComponent extends BaseComponent {

  form: any = {};

  fields: Field[] = [
    FieldUtil.buildText({
      code: 'username',
      edit: {
        label: { visible: false },
        input: { placeholder: '账号/手机/邮件/身份证', prefixIcon: 'user', span: 24 }
      }
    }),
    FieldUtil.buildPassword({
      code: 'password',
      edit: {
        label: { visible: false },
        input: { placeholder: '请录入登录密码', prefixIcon: 'lock', span: 24 }
      }
    }),
  ];

  constructor(private oauth: SecurityOAuthService) { super(); }

  signIn(): void {
    this.oauth.requestTokenByPassword(this.form.username, this.form.password, this);
  }

  signUp(): void {

  }

  forgetPassword(): void {

  }

}
