import { Component, OnInit } from '@angular/core';
import { EditComponent } from 'src/app/components/commons/edit/edit.component';

@Component({
    selector: 'app-edit-staff',
    templateUrl: './edit-staff.component.html',
    styleUrls: ['./edit-staff.component.scss']
})
export class EditStaffComponent extends EditComponent implements OnInit {

    userProfileFields = [];

    jobFields = [];

    ngOnInit(): void {
        super.ngOnInit();
        this.userProfileFields = this.fields[0].children;
        this.jobFields = this.fields.slice(1);
    }

}
