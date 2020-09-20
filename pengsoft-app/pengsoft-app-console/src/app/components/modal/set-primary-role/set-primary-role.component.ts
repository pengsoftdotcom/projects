import { Component, Input, OnInit } from '@angular/core';
import { HttpOptions } from 'src/app/services/commons/http-options';
import { UserService } from 'src/app/services/security/user.service';
import { BaseComponent } from '../../commons/base.component';

@Component({
    selector: 'app-set-primary-role',
    templateUrl: './set-primary-role.component.html',
    styleUrls: ['./set-primary-role.component.scss']
})
export class SetPrimaryRoleComponent extends BaseComponent implements OnInit {

    @Input() items = [];

    primary: string;

    constructor(private user: UserService) { super(); }

    ngOnInit(): void {
        this.primary = this.items.filter(item => item.primary)[0].id;
    }

    submit(options: HttpOptions): void {
        const userRole = this.items.filter(item => item.id === this.primary)[0];
        this.user.setPrimaryRole(userRole.user, userRole.role, options);
    }

}
