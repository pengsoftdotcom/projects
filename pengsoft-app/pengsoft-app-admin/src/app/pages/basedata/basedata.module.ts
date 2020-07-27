import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NzGridModule, NzSpinModule, NzTabsModule } from 'ng-zorro-antd';
import { NzSpaceModule } from 'ng-zorro-antd/space';
import { ComponentsModule } from 'src/app/components/components.module';
import { BasedataRoutingModule } from './basedata-routing.module';
import { DepartmentComponent } from './department/department.component';
import { JobComponent } from './job/job.component';
import { OrganizationComponent } from './organization/organization.component';
import { PersonComponent } from './person/person.component';
import { PostComponent } from './post/post.component';
import { EditStaffComponent } from './staff/edit-staff.component';
import { StaffComponent } from './staff/staff.component';
import { EditOrganizationComponent } from './organization/edit-organization.component';
import { CommunityComponent } from './community/community.component';
import { BuildingComponent } from './building/building.component';
import { FloorComponent } from './floor/floor.component';
import { HouseComponent } from './house/house.component';
import { EditHouseComponent } from './house/edit-house.component';




@NgModule({
  declarations: [
    PersonComponent,
    OrganizationComponent,
    DepartmentComponent,
    PostComponent,
    JobComponent,
    StaffComponent,
    EditStaffComponent,
    EditOrganizationComponent,
    CommunityComponent,
    BuildingComponent,
    FloorComponent,
    HouseComponent,
    EditHouseComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    BasedataRoutingModule,
    ComponentsModule,
    NzSpaceModule,
    NzGridModule,
    NzTabsModule,
    NzSpinModule
  ]
})
export class BasedataModule { }
