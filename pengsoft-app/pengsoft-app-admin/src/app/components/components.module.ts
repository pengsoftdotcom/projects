import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NzSpinModule } from 'ng-zorro-antd/spin';
import { NzToolTipModule } from 'ng-zorro-antd/tooltip';
import { IconsProviderModule } from '../icons-provider.module';
import { FormItemComponent } from './commons/form-item/form-item.component';
import { PasswordComponent } from './commons/input/password/password.component';
import { TextComponent } from './commons/input/text/text.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { ChangePasswordComponent } from './change-password/change-password.component';



@NgModule({
  declarations: [FormItemComponent, TextComponent, PasswordComponent, SignInComponent, ChangePasswordComponent],
  imports: [
    CommonModule,
    FormsModule,
    NzFormModule,
    NzButtonModule,
    NzInputModule,
    NzToolTipModule,
    NzSpinModule,
    IconsProviderModule
  ],
  exports: [FormItemComponent, SignInComponent, ChangePasswordComponent]
})
export class ComponentsModule { }
