import { Component, OnInit } from '@angular/core';
import { EditComponent } from 'src/app/components/commons/edit/edit.component';

@Component({
    selector: 'app-edit-house',
    templateUrl: './edit-house.component.html',
    styleUrls: ['./edit-house.component.scss']
})
export class EditHouseComponent extends EditComponent implements OnInit {

    houseFields = [];

    ownerFields = [];

    ngOnInit(): void {
        super.ngOnInit();
        const length = this.fields.length;
        this.houseFields = this.fields.slice(0, length - 1);
        this.ownerFields = this.fields[length - 1].children;
    }


}
