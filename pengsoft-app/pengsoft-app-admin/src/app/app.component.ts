import { Location } from '@angular/common';
import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Route, Router } from '@angular/router';
import { NzMessageService, NzModalRef, NzModalService } from 'ng-zorro-antd';
import { environment } from 'src/environments/environment';
import { ChangePasswordComponent } from './components/modal/change-password/change-password.component';
import { SecurityService } from './services/commons/security.service';
import { UserDetailsService } from './services/security/user-details.service';


@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

    isCollapsed = false;

    userDetails: any = {};

    menus = [];

    @ViewChild('jobs', { static: true }) jobs: TemplateRef<any>;

    switchJobModal: NzModalRef;

    @ViewChild('roles', { static: true }) roles: TemplateRef<any>;

    switchRoleModal: NzModalRef;

    constructor(
        private title: Title,
        private userDetailsService: UserDetailsService,
        private security: SecurityService,
        private modal: NzModalService,
        private message: NzMessageService,
        private router: Router,
        private location: Location
    ) {
        this.title.setTitle(environment.title);
        this.menus = this.router.config.filter(route => route.data || route.children);
    }

    ngOnInit(): void {
        this.userDetails = this.security.userDetails;
        if (this.userDetails.roles && this.userDetails.roles.length > 0) {
            if (this.userDetails.currentRole) {
                this.userDetails.currentRole = this.userDetails.roles.find((role: any) => role.id === this.userDetails.currentRole.id);
            }
            if (this.userDetails.majorRole) {
                this.userDetails.majorRole = this.userDetails.roles.find((role: any) => role.id === this.userDetails.majorRole.id);
            }
        }

        if (this.userDetails.jobs && this.userDetails.jobs.length > 0) {
            if (this.userDetails.currentJob) {
                this.userDetails.currentJob = this.userDetails.jobs.find((job: any) => job.id === this.userDetails.currentJob.id);
            }
            if (this.userDetails.majorJob) {
                this.userDetails.majorJob = this.userDetails.jobs.find((job: any) => job.id === this.userDetails.majorJob.id);
            }
        }
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
        this.switchJobModal = this.modal.create({
            nzStyle: { top: '30%' },
            nzWidth: 450,
            nzTitle: '切换职位',
            nzContent: this.jobs,
            nzFooter: null
        });
    }

    currentJobChanged(): void {
        this.userDetailsService.setCurrentJob(this.userDetails.currentJob, {
            success: (res: any) => {
                this.security.userDetails = res;
                this.ngOnInit();
                this.message.info('设置成功，页面将刷新', { nzDuration: 1000 }).onClose.subscribe(() => window.location.reload());
            },
            failure: () => this.message.error('设置失败')
        });
    }

    majorJobChanged(): void {
        this.userDetailsService.setMajorJob(this.userDetails.majorJob, {
            success: (res: any) => {
                this.security.userDetails = res;
                this.ngOnInit();
                this.message.info('设置成功', { nzDuration: 1000 }).onClose.subscribe(() => this.switchJobModal.close());
            },
            failure: () => this.message.error('设置失败')
        });
    }

    switchRole(): void {
        this.switchRoleModal = this.modal.create({
            nzStyle: { top: '30%' },
            nzWidth: 450,
            nzTitle: '切换角色',
            nzContent: this.roles,
            nzFooter: null
        });
    }

    currentRoleChanged(): void {
        this.userDetailsService.setCurrentRole(this.userDetails.currentRole, {
            success: (res: any) => {
                this.security.userDetails = res;
                this.ngOnInit();
                this.message.info('设置成功，页面将刷新', { nzDuration: 1000 }).onClose.subscribe(() => window.location.reload());
            },
            failure: () => this.message.error('设置失败')
        });
    }

    majorRoleChanged(): void {
        this.userDetailsService.setMajorRole(this.userDetails.majorRole, {
            success: (res: any) => {
                this.security.userDetails = res;
                this.ngOnInit();
                this.message.info('设置成功', { nzDuration: 1000 }).onClose.subscribe(() => this.switchRoleModal.close());
            },
            failure: () => this.message.error('设置失败')
        });
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
