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

    @ViewChild('organizations', { static: true }) organizations: TemplateRef<any>;

    switchOrganizationModal: NzModalRef;

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
            if (this.userDetails.primaryRole) {
                this.userDetails.primaryRole = this.userDetails.roles.find((role: any) => role.id === this.userDetails.primaryRole.id);
            }
        }

        if (this.userDetails.jobs && this.userDetails.jobs.length > 0) {
            if (this.userDetails.currentJob) {
                this.userDetails.currentJob = this.userDetails.jobs.find((job: any) => job.id === this.userDetails.currentJob.id);
            }
            if (this.userDetails.primaryJob) {
                this.userDetails.primaryJob = this.userDetails.jobs.find((job: any) => job.id === this.userDetails.primaryJob.id);
            }
        }

        if (this.userDetails.organizations && this.userDetails.organizations.length > 0) {
            if (this.userDetails.organization) {
                this.userDetails.organization
                    = this.userDetails.organizations.find((organization: any) => organization.id === this.userDetails.organization.id);
            }
        }
    }

    getMenuAuthority(route: Route): string {
        let authority: string;
        if (route.children) {
            authority = route.children.map(child => child.data.code).join(',');
        } else if (route.data) {
            authority = route.data.code;
        }
        return authority;
    }

    get avatar(): string {
        return this.userDetails.person.avatar.accessPath + '?x-oss-process=image/resize,w_28,h_28';
    }

    get name(): string {
        let name = this.userDetails.user.username;
        if (this.userDetails.person) {
            name = this.userDetails.person.nickname;
        }
        return name;
    }

    isMenuOpen(menu: any): boolean {
        const paths = this.location.path().split('/');
        paths.shift();
        return menu.path === paths.shift();
    }

    editPerson(): void {
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
                this.message.info('设置成功，页面即将刷新', { nzDuration: 1000 }).onClose.subscribe(() => window.location.reload());
            },
            failure: () => this.message.error('设置失败')
        });
    }

    primaryJobChanged(): void {
        this.userDetailsService.setPrimaryJob(this.userDetails.primaryJob, {
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
                this.message.info('设置成功，页面即将刷新', { nzDuration: 1000 }).onClose.subscribe(() => window.location.reload());
            },
            failure: () => this.message.error('设置失败')
        });
    }

    primaryRoleChanged(): void {
        this.userDetailsService.setPrimaryRole(this.userDetails.primaryRole, {
            success: (res: any) => {
                this.security.userDetails = res;
                this.ngOnInit();
                this.message.info('设置成功', { nzDuration: 1000 }).onClose.subscribe(() => this.switchRoleModal.close());
            },
            failure: () => this.message.error('设置失败')
        });
    }

    switchOrganization(): void {
        this.switchJobModal = this.modal.create({
            nzStyle: { top: '30%' },
            nzWidth: 450,
            nzTitle: '切换机构',
            nzContent: this.organizations,
            nzFooter: null
        });
    }

    currentOrganizationChanged(): void {
        this.userDetailsService.setOrganization(this.userDetails.organization, {
            success: (res: any) => {
                this.security.userDetails = res;
                this.ngOnInit();
                this.message.info('设置成功，页面即将刷新', { nzDuration: 1000 }).onClose.subscribe(() => window.location.reload());
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
