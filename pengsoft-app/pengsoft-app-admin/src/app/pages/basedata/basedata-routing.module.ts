import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BuildingComponent } from './building/building.component';
import { CommunityComponent } from './community/community.component';
import { DepartmentComponent } from './department/department.component';
import { FloorComponent } from './floor/floor.component';
import { HouseComponent } from './house/house.component';
import { JobComponent } from './job/job.component';
import { OrganizationComponent } from './organization/organization.component';
import { PersonComponent } from './person/person.component';
import { PostComponent } from './post/post.component';
import { StaffComponent } from './staff/staff.component';



const routes: Routes = [
    {
        path: 'basedata',
        data: { name: '基础数据', icon: 'book' },
        children: [
            { path: 'community', component: CommunityComponent, data: { code: 'basedata::community::find_page', name: '社区' } },
            { path: 'building', component: BuildingComponent, data: { code: 'basedata::building::find_page', name: '楼栋' } },
            { path: 'floor', component: FloorComponent, data: { code: 'basedata::floor::find_page', name: '楼层' } },
            { path: 'house', component: HouseComponent, data: { code: 'basedata::house::find_page', name: '房屋' } },
            { path: 'organization', component: OrganizationComponent, data: { code: 'basedata::organization::find_all', name: '机构' } },
            { path: 'department', component: DepartmentComponent, data: { code: 'basedata::department::find_all', name: '部门' } },
            { path: 'post', component: PostComponent, data: { code: 'basedata::post::find_all', name: '职务' } },
            { path: 'job', component: JobComponent, data: { code: 'basedata::job::find_all', name: '职位' } },
            { path: 'staff', component: StaffComponent, data: { code: 'basedata::staff::find_page', name: '员工' } },
            { path: 'person', component: PersonComponent, data: { code: 'basedata::person::find_page', name: '人员' } }
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class BasedataRoutingModule { }
