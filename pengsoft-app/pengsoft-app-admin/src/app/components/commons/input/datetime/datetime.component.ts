import { Component, OnInit } from '@angular/core';
import { DateUtils } from 'src/app/utils/date-utils';
import { InputComponent } from '../input.component';

@Component({
    selector: 'app-input-datetime',
    templateUrl: './datetime.component.html',
    styleUrls: ['./datetime.component.scss']
})
export class DatetimeComponent extends InputComponent implements OnInit {


    ngOnInit(): void {
        if (this.form[this.field.code]) {
            this.rawValue = DateUtils.parse(this.form[this.field.code]);
        }
    }


    modelChange(event: any): void {
        if (this.rawValue) {
            this.form[this.field.code] = DateUtils.formatDateTime(this.rawValue);
        } else {
            this.form[this.field.code] = null;
        }
    }

}
