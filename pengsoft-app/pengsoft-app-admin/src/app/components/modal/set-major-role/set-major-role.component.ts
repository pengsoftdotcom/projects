import { Component, Input, OnInit } from '@angular/core';
import { HttpOptions } from 'src/app/services/commons/http-options';
import { UserService } from 'src/app/services/security/user.service';
import { BaseComponent } from '../../commons/base.component';

@Component({
    selector: 'app-set-major-role',
    templateUrl: './set-major-role.component.html',
    styleUrls: ['./set-major-role.component.scss']
})
export class SetMajorRoleComponent extends BaseComponent implements OnInit {

    @Input() items = [];

    major: string;

    constructor(private user: UserService) { super(); }

    ngOnInit(): void {
        this.major = this.items.filter(item => item.major)[0].id;
    }

    submit(options: HttpOptions): void {
        const userRole = this.items.filter(item => item.id === this.major)[0];
        this.user.setMajorRole(userRole.user, userRole.role, options);
    }

}
