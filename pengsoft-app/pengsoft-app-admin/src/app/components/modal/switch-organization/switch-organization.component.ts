import { Component, OnInit } from '@angular/core';
import { OrganizationService } from 'src/app/services/basedata/organization.service';
import { BaseComponent } from '../../commons/base.component';
import { FieldUtils } from 'src/app/utils/field-utils';
import { InputComponent } from '../../commons/input/input.component';
import { EntityUtils } from 'src/app/utils/entity-utils';

@Component({
    selector: 'app-switch-organization',
    templateUrl: './switch-organization.component.html',
    styleUrls: ['./switch-organization.component.scss']
})
export class SwitchOrganizationComponent extends BaseComponent {

    field = FieldUtils.buildTreeSelect({
        code: 'organization',
        edit: {
            label: { visible: false },
            input: {
                load: (component: InputComponent, event?: string) => {
                    const params: any = {};
                    if (event) {
                        params.name = event;
                    }
                    this.organization.findAll(params, {
                        before: () => this.loading = true,
                        success: (res: any) => component.edit.input.options = EntityUtils.convertListToTree(res),
                        after: () => this.loading = false
                    });
                }
            }
        }
    });

    form: any = {};

    constructor(private organization: OrganizationService) { super(); }

}
