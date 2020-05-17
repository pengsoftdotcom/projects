import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ComponentsModule } from 'src/app/components/components.module';
import { ClientComponent } from './client/client.component';
import { SecurityOAuthRoutingModule } from './security-oauth-routing.module';



@NgModule({
    declarations: [ClientComponent],
    imports: [
        CommonModule,
        SecurityOAuthRoutingModule,
        ComponentsModule
    ]
})
export class SecurityOAuthModule { }
