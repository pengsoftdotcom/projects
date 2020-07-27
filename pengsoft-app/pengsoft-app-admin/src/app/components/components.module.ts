import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import {
    NzButtonModule,
    NzDatePickerModule,
    NzDividerModule,
    NzDrawerModule,
    NzFormModule,
    NzInputModule,
    NzInputNumberModule,
    NzPageHeaderModule,
    NzSelectModule,
    NzSpinModule,
    NzSwitchModule,
    NzTableModule,
    NzToolTipModule,
    NzTransferModule,
    NzTreeModule,
    NzRadioModule,
    NzTreeSelectModule,
    NzUploadModule,
    NzCascaderModule,
    NzTabsModule,
} from 'ng-zorro-antd';
import { NzSpaceModule } from 'ng-zorro-antd/space';
import { DirectivesModule } from '../directives/directives.module';
import { IconsProviderModule } from '../icons-provider.module';
import { ButtonComponent } from './commons/button/button.component';
import { EditComponent } from './commons/edit/edit.component';
import { FormItemComponent } from './commons/form-item/form-item.component';
import { BooleanComponent } from './commons/input/boolean/boolean.component';
import { DatetimeComponent } from './commons/input/datetime/datetime.component';
import { NumberComponent } from './commons/input/number/number.component';
import { PasswordComponent } from './commons/input/password/password.component';
import { SelectComponent } from './commons/input/select/select.component';
import { TextComponent } from './commons/input/text/text.component';
import { TextareaComponent } from './commons/input/textarea/textarea.component';
import { FilterComponent } from './commons/filter/filter.component';
import { ListComponent } from './commons/list/list.component';
import { ChangePasswordComponent } from './modal/change-password/change-password.component';
import { ResetPasswordComponent } from './modal/reset-password/reset-password.component';
import { EditManyToManyComponent } from './commons/edit-many-to-many/edit-many-to-many.component';
import { SetPrimaryRoleComponent } from './modal/set-primary-role/set-primary-role.component';
import { TreeSelectComponent } from './commons/input/tree-select/tree-select.component';
import { UploadComponent } from './commons/input/upload/upload.component';
import { EditOneToManyComponent } from './commons/edit-one-to-many/edit-one-to-many.component';
import { AvatarComponent } from './commons/input/avatar/avatar.component';
import { CascaderComponent } from './commons/input/cascader/cascader.component';
import { DateComponent } from './commons/input/date/date.component';
import { SwitchOrganizationComponent } from './modal/switch-organization/switch-organization.component';
import { JsonComponent } from './commons/input/json/json.component';
import { NgJsonEditorModule } from 'ang-jsoneditor';
import { SwitchCommunityComponent } from './modal/switch-community/switch-community.component';

@NgModule({
    declarations: [
        FormItemComponent,
        TextComponent,
        NumberComponent,
        PasswordComponent,
        ChangePasswordComponent,
        ButtonComponent,
        ListComponent,
        FilterComponent,
        EditComponent,
        BooleanComponent,
        DatetimeComponent,
        SelectComponent,
        TextareaComponent,
        ResetPasswordComponent,
        EditManyToManyComponent,
        SetPrimaryRoleComponent,
        TreeSelectComponent,
        EditOneToManyComponent,
        UploadComponent,
        AvatarComponent,
        CascaderComponent,
        DateComponent,
        SwitchOrganizationComponent,
        JsonComponent,
        SwitchCommunityComponent
    ],
    imports: [
        CommonModule,
        FormsModule,
        NzFormModule,
        NzButtonModule,
        NzInputModule,
        NzInputNumberModule,
        NzSwitchModule,
        NzDatePickerModule,
        NzSelectModule,
        NzTreeSelectModule,
        NzToolTipModule,
        NzSpinModule,
        NzPageHeaderModule,
        NzSpaceModule,
        NzDividerModule,
        NzTableModule,
        NzDrawerModule,
        NzTransferModule,
        NzTreeModule,
        NzRadioModule,
        NzUploadModule,
        NzCascaderModule,
        NzTabsModule,
        NgJsonEditorModule,
        IconsProviderModule,
        DirectivesModule
    ],
    exports: [
        FormItemComponent,
        ChangePasswordComponent,
        ButtonComponent,
        ListComponent,
        EditComponent,
        EditManyToManyComponent,
        EditOneToManyComponent,
        UploadComponent
    ]
})
export class ComponentsModule { }
