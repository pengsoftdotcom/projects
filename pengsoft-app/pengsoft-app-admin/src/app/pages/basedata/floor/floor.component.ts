import { Location } from '@angular/common';
import { Component, Input, ViewChild } from '@angular/core';
import { NzMessageService, NzModalService, NzFormatEmitEvent } from 'ng-zorro-antd';
import { EntityComponent } from 'src/app/components/commons/entity.component';
import { SwitchCommunityComponent } from 'src/app/components/modal/switch-community/switch-community.component';
import { FloorService } from 'src/app/services/basedata/floor.service';
import { FieldUtils } from 'src/app/utils/field-utils';
import { BuildingService } from 'src/app/services/basedata/building.service';
import { EntityUtils } from 'src/app/utils/entity-utils';
import { EditOneToManyComponent } from 'src/app/components/commons/edit-one-to-many/edit-one-to-many.component';
import { HouseComponent } from '../house/house.component';


@Component({
    selector: 'app-floor',
    templateUrl: './floor.component.html',
    styleUrls: ['./floor.component.scss']
})
export class FloorComponent extends EntityComponent<FloorService> {

    showSwitcher = true;

    community: any;

    @Input() building: any;

    @ViewChild('housesComponent', { static: true }) housesComponent: EditOneToManyComponent;

    constructor(
        private buildingService: BuildingService,
        private location: Location,
        protected entity: FloorService,
        protected modal: NzModalService,
        protected message: NzMessageService
    ) {
        super(entity, modal, message);
    }

    initFields(): void {
        this.fields = [
            FieldUtils.buildTreeSelect({ code: 'building', name: '楼栋', list: { visible: false }, edit: { visible: false } }),
            FieldUtils.buildTextForName()
        ];
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
            name: '房屋', type: 'link', divider: true, width: 47, authority: 'basedata::house::find_page',
            action: (floor: any) => this.editHouses(floor)
        });
    }

    editHouses(floor: any): void {
        this.housesComponent.component = HouseComponent;
        this.housesComponent.width = '60%';
        this.housesComponent.params = { title: floor.name, floor, showSwitcher: false };
        this.housesComponent.show();
    }

    initForm(): void {
        if (this.building) {
            this.filterForm = { 'building.id': this.building.id };
            this.editForm = { building: this.building };
        }
    }

    afterInit(): void {
        if (this.building) {
            this.initForm();
            this.list();
        } else {
            if (!this.community) {
                this.switchCommunity();
            } else {
                this.loadNavData();
            }
        }
    }

    afterEditFormFilled(): void {
        if (this.building) {
            this.editForm.building = this.building;
        }
    }

    afterFilterFormReset(): void {
        if (this.building) {
            this.filterForm['building.id'] = this.building.id;
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

    private loadNavData() {
        if (this.allowLoadNavData) {
            this.buildingService.findAll({ 'community.id': this.community.id }, {
                success: (res: any) => {
                    this.navData = EntityUtils.convertListToTree(res);
                    this.navData = [{
                        key: this.community.id,
                        title: this.community.name,
                        value: this.community,
                        isLeaf: !this.navData || this.navData.length === 0,
                        children: this.navData
                    }];
                }
            });
        }
    }

    nav(event: NzFormatEmitEvent): void {
        if (this.community) {
            if (this.community.id === event.node.key) {
                this.filterForm['building.community.id'] = this.community.id;
                this.building = null;
                delete this.filterForm['building.id'];
            } else {
                this.building = event.node.origin.value;
                this.initForm();
                delete this.filterForm['building.community.id'];
            }
        }
        this.list();
    }

}
