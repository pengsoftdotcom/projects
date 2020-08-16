import { Location } from '@angular/common';
import { Component, Input, ViewChild } from '@angular/core';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { EditOneToManyComponent } from 'src/app/components/commons/edit-one-to-many/edit-one-to-many.component';
import { TreeEntityComponent } from 'src/app/components/commons/tree-entity.component';
import { SwitchCommunityComponent } from 'src/app/components/modal/switch-community/switch-community.component';
import { BuildingService } from 'src/app/services/basedata/building.service';
import { FieldUtils } from 'src/app/utils/field-utils';
import { FloorComponent } from '../floor/floor.component';

@Component({
    selector: 'app-building',
    templateUrl: './building.component.html',
    styleUrls: ['./building.component.scss']
})
export class BuildingComponent extends TreeEntityComponent<BuildingService> {

    showSwitcher = true;

    @Input() community: any;

    @ViewChild('floorsComponent', { static: true }) floorsComponent: EditOneToManyComponent;

    constructor(
        private location: Location,
        protected entity: BuildingService,
        protected modal: NzModalService,
        protected message: NzMessageService
    ) {
        super(entity, modal, message);
    }

    get lazy(): boolean {
        return false;
    }

    get parentParams(): any {
        if (this.community) {
            return { 'community.id': this.community.id };
        } else {
            return null;
        }
    }

    initFields(): void {
        super.initFields();
        this.fields.splice(1, 0,
            FieldUtils.buildSelect({ code: 'community', name: '社区', list: { visible: false }, edit: { visible: false } }),
            FieldUtils.buildTextForName()
        );
    }

    initListToolbarButtons(): void {
        super.initListToolbarButtons();
        if (this.showSwitcher) {
            this.listToolbarButtons.splice(1, 0,
                { name: '切换社区', type: 'link', authority: 'basedata::community::find_all', action: () => this.switchCommunity() }
            );
        }
    }

    initListActionButtons(): void {
        super.initListActionButtons();
        this.listActionButtons.splice(0, 0, {
            name: '楼层', type: 'link', divider: true, width: 47, authority: 'basedata::floor::find_page',
            action: (building: any) => this.editFloors(building)
        });
    }

    editFloors(building: any): void {
        this.floorsComponent.component = FloorComponent;
        this.floorsComponent.width = '70%';
        this.floorsComponent.params = { title: building.name, building, showSwitcher: false };
        this.floorsComponent.show();
    }

    initForm(): void {
        if (this.community) {
            this.filterForm = { 'community.id': this.community.id };
            this.editForm = { community: this.community };
        }
    }

    afterInit(): void {
        if (!this.community) {
            this.switchCommunity();
        } else {
            this.initForm();
            this.list();
        }
    }

    afterEditFormFilled(): void {
        if (this.community) {
            this.editForm.community = this.community;
        }
    }

    afterFilterFormReset(): void {
        if (this.community) {
            this.filterForm['community.id'] = this.community.id;
        }
    }

    switchCommunity(): void {
        this.modal.create({
            nzTitle: '切换社区',
            nzContent: SwitchCommunityComponent,
            nzOnOk: component => {
                this.community = component.form.community;
                this.title = this.community.name;
                this.afterInit();
            },
            nzOnCancel: () => {
                if (!this.community) {
                    this.modal.confirm({
                        nzTitle: '确认',
                        nzContent: '如不选择社区，将退回到最近一次打开页面',
                        nzOnOk: () => this.location.back()
                    });
                    return false;
                }
            }
        });
    }

}
