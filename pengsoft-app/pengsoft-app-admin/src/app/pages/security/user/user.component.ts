import { Component, OnInit } from '@angular/core';
import { BeanComponent } from 'src/app/components/commons/bean.component';
import { UserService } from 'src/app/services/security/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent extends BeanComponent<UserService> {

  constructor(protected user: UserService) { super(user); }

}
