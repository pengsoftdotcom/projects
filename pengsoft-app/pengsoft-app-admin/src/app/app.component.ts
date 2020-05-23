import { Location } from '@angular/common';
import { Component } from '@angular/core';
import { Router, Route } from '@angular/router';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { ChangePasswordComponent } from './components/modal/change-password/change-password.component';
import { SecurityService } from './services/commons/security.service';


@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss']
})
export class AppComponent {

    isCollapsed = false;

    userDetails: any = {};

    menus = [];

    constructor(
        private security: SecurityService,
        private modal: NzModalService,
        private message: NzMessageService,
        private router: Router,
        private location: Location
    ) {
        this.userDetails = this.security.userDetails;
        this.menus = this.router.config.filter(route => route.data || route.children);
    }

    getMenuAuthority(route: Route): string {
        let authority: string;
        if (route.data && route.data.code) {
            authority = route.data.code;
        } else {
            authority = route.children.map(child => child.data.code).join(',');
        }
        return authority;
    }

    get avatar(): string {
        return this.userDetails.userProfile.avatar.accessPath + '?x-oss-process=image/resize,w_28,h_28';
    }

    get name(): string {
        let name = this.userDetails.user.username;
        if (this.userDetails.userProfile) {
            name = this.userDetails.userProfile.nickname;
        }
        return name;
    }

    isMenuOpen(menu: any): boolean {
        const paths = this.location.path().split('/');
        paths.shift();
        return menu.path === paths.shift();
    }

    editProfile(): void {
        this.message.info('暂未开放，敬请期待');
    }

    verify(): void {
        this.message.info('暂未开放，敬请期待');
    }

    settings(): void {
        this.message.info('暂未开放，敬请期待');
    }

    switchJob(): void {
        this.message.info('暂未开放，敬请期待');
    }

    switchRole(): void {
        this.message.info('暂未开放，敬请期待');
    }

    changePassword(): void {
        this.modal.create({
            nzContent: ChangePasswordComponent,
            nzStyle: { top: '30%' },
            nzWidth: 450,
            nzTitle: '修改密码',
            nzOnOk: component => new Promise(resolve => {
                component.submit({
                    before: () => component.loading = true,
                    success: () => resolve(true),
                    failure: () => resolve(false),
                    after: () => component.loading = false
                });
            })
        });
    }

    signIn(): void {
        this.router.navigateByUrl('/sign-in');
    }

    signOut(): void {
        this.modal.confirm({
            nzTitle: '确定要退出登录吗？',
            nzOnOk: () => {
                this.security.clear();
                this.signIn();
            }
        });
    }

}
