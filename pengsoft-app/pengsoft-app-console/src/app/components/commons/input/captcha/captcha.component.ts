import { Component } from '@angular/core';
import { InputComponent } from '../input.component';
import { HttpService } from 'src/app/services/commons/http.service';
import { NzMessageService } from 'ng-zorro-antd';
import { CaptchaService } from 'src/app/services/system/captcha.service';

@Component({
    selector: 'app-input-captcha',
    templateUrl: './captcha.component.html',
    styleUrls: ['./captcha.component.scss']
})
export class CaptchaComponent extends InputComponent {

    reg = /^1[3-9]\d{9}$/;

    seconds = 60;

    generated = false;

    constructor(private captcha: CaptchaService) {
        super();
    }

    generate(): void {
        if (!this.generated) {
            this.form[this.edit.code] = '';
            this.captcha.generate(this.form.username, {
                success: res => {
                    this.generated = true;
                    const interval = setInterval(() => {
                        if (this.seconds === 0) {
                            this.seconds = 60;
                            this.generated = false;
                            clearInterval(interval);
                        } else {
                            this.seconds -= 1;
                        }
                    }, 1000);
                },
                errors: this.errors
            });
        }
    }

}
