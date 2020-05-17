import { Directive, ElementRef, Input, OnInit } from '@angular/core';
import { SecurityService } from '../services/commons/security.service';

@Directive({
    // tslint:disable-next-line: directive-selector
    selector: '[hasAnyAuthority]'
})
export class HasAnyAuthorityDirective implements OnInit {

    @Input('hasAnyAuthority') authorityCodes: string;

    constructor(private el: ElementRef, private security: SecurityService) { }

    ngOnInit(): void {
        if (this.authorityCodes && !this.security.hasAnyAuthority(this.authorityCodes)) {
            this.el.nativeElement.remove();
        }
    }

}
