import { Component } from '@angular/core';
import { CommunityService } from 'src/app/services/basedata/community.service';
import { FieldUtils } from 'src/app/utils/field-utils';
import { BaseComponent } from '../../commons/base.component';
import { InputComponent } from '../../commons/input/input.component';
import { EntityUtils } from 'src/app/utils/entity-utils';

@Component({
    selector: 'app-switch-community',
    templateUrl: './switch-community.component.html',
    styleUrls: ['./switch-community.component.scss']
})
export class SwitchCommunityComponent extends BaseComponent {

    field = FieldUtils.buildTreeSelect({
        code: 'community',
        edit: {
            label: { visible: false },
            input: {
                load: (component: InputComponent, event?: string) => {
                    const params: any = {};
                    if (event) {
                        params.name = event;
                    }
                    this.community.findAll(params, {
                        before: () => this.loading = true,
                        success: (res: any) => component.edit.input.options = EntityUtils.convertListToTree(res),
                        after: () => this.loading = false
                    });
                }
            }
        }
    });

    form: any = {};

    constructor(private community: CommunityService) { super(); }

}
