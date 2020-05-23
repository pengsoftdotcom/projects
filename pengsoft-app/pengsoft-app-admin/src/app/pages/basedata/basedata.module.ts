import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ComponentsModule } from 'src/app/components/components.module';
import { BasedataRoutingModule } from './basedata-routing.module';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { OrganizationComponent } from './organization/organization.component';
import { DepartmentComponent } from './department/department.component';
import { PostComponent } from './post/post.component';
import { JobComponent } from './job/job.component';



@NgModule({
  declarations: [UserProfileComponent, OrganizationComponent, DepartmentComponent, PostComponent, JobComponent],
  imports: [
    CommonModule,
    FormsModule,
    BasedataRoutingModule,
    ComponentsModule
  ]
})
export class BasedataModule { }
