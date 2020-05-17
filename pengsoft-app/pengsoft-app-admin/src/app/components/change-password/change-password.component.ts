import { Component } from '@angular/core';
import { UserDetailsService } from 'src/app/services/security/user-details.service';
import { FieldUtil } from 'src/app/utils/field-util';
import { BaseComponent } from '../commons/base.component';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.scss']
})
export class ChangePasswordComponent extends BaseComponent {

  form: any = {};

  fields = [
    FieldUtil.buildPassword({
      code: 'oldPassword',
      name: '原密码'
    }),
    FieldUtil.buildPassword({
      code: 'newPassword',
      name: '原密码'
    }),
    FieldUtil.buildPassword({
      code: 'confirmPassword',
      name: '确认密码'
    })
  ];

  constructor(private userDetails: UserDetailsService) { super(); }

  submit(): void {
    this.userDetails.changePassword(this.form.oldPassword, this.form.newPassword, this);
  }

}
