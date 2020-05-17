import { Directive, Input, ElementRef, OnInit } from '@angular/core';
import { SecurityService } from '../services/commons/security.service';

@Directive({
    // tslint:disable-next-line: directive-selector
    selector: '[hasAnyRole]'
})
export class HasAnyRoleDirective implements OnInit {

    @Input('hasAnyRole') roleCodes: string;

    constructor(private el: ElementRef, private security: SecurityService) { }

    ngOnInit(): void {
        const me = this;
        if (me.roleCodes && !this.security.hasAnyRole(me.roleCodes)) {
            me.el.nativeElement.remove();
        }
    }

}
