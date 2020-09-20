import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ComponentsModule } from 'src/app/components/components.module';
import { SecurityRoutingModule } from './security-routing.module';
import { UserComponent } from './user/user.component';
import { AuthorityComponent } from './authority/authority.component';
import { RoleComponent } from './role/role.component';



@NgModule({
    declarations: [UserComponent, AuthorityComponent, RoleComponent],
    imports: [
        CommonModule,
        SecurityRoutingModule,
        ComponentsModule
    ]
})
export class SecurityModule { }
