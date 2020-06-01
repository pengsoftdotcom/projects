import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NzGridModule, NzTabsModule, NzSpinModule } from 'ng-zorro-antd';
import { NzSpaceModule } from 'ng-zorro-antd/space';
import { ComponentsModule } from 'src/app/components/components.module';
import { BasedataRoutingModule } from './basedata-routing.module';
import { DepartmentComponent } from './department/department.component';
import { JobComponent } from './job/job.component';
import { OrganizationComponent } from './organization/organization.component';
import { PostComponent } from './post/post.component';
import { EditStaffComponent } from './staff/edit-staff.component';
import { StaffComponent } from './staff/staff.component';
import { UserProfileComponent } from './user-profile/user-profile.component';



@NgModule({
  declarations: [
    UserProfileComponent,
    OrganizationComponent,
    DepartmentComponent,
    PostComponent,
    JobComponent,
    StaffComponent,
    EditStaffComponent
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
