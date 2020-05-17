import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { HasAnyAuthorityDirective } from './has-any-authority.directive';
import { HasAnyRoleDirective } from './has-any-role.directive';

@NgModule({
    declarations: [HasAnyRoleDirective, HasAnyAuthorityDirective],
    imports: [CommonModule],
    exports: [HasAnyRoleDirective, HasAnyAuthorityDirective]
})
export class DirectivesModule { }
