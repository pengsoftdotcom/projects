import { Location } from '@angular/common';
import { Component, Input } from '@angular/core';
import { NzMessageService, NzModalService, NzFormatEmitEvent } from 'ng-zorro-antd';
import { EntityComponent } from 'src/app/components/commons/entity.component';
import { FloorService } from 'src/app/services/basedata/floor.service';
import { HouseService } from 'src/app/services/basedata/house.service';
import { FieldUtils } from 'src/app/utils/field-utils';
import { SwitchCommunityComponent } from 'src/app/components/modal/switch-community/switch-community.component';
import { BuildingService } from 'src/app/services/basedata/building.service';
import { EntityUtils } from 'src/app/utils/entity-utils';
import { PersonComponent } from '../person/person.component';
import { DictionaryItemService } from 'src/app/services/system/dictionary-item.service';

@Component({
    selector: 'app-house',
    templateUrl: './house.component.html',
    styleUrls: ['./house.component.scss']
})
export class HouseComponent extends EntityComponent<HouseService> {

    showSwitcher = true;

    community: any;

    building: any;

    @Input() floor: any;

    constructor(
        private dictionaryItem: DictionaryItemService,
        private floorService: FloorService,
        private buildingService: BuildingService,
        private location: Location,
        protected entity: HouseService,
        protected modal: NzModalService,
        protected message: NzMessageService
    ) {
        super(entity, modal, message);
    }

    initFields(): void {
        PersonComponent.prototype.dictionaryItem = this.dictionaryItem;
        PersonComponent.prototype.initFields();
        PersonComponent.prototype.fields.forEach(field => {
            switch (field.code) {
                case 'nickname':
                case 'gender':
                    field.list.visible = false;
                    break;
                default: break;
            }
        });
        this.fields = [
            FieldUtils.buildSelect({ code: 'floor', name: '楼层', list: { visible: false }, edit: { visible: false } }),
            FieldUtils.buildTextForCode(),
            FieldUtils.buildTextForName({ edit: { required: false } }),
            FieldUtils.buildNumber({ code: 'grossFloorArea', name: '建筑面积(m²)' }),
            FieldUtils.buildNumber({ code: 'netFloorArea', name: '实用面积(m²)' }),
            FieldUtils.buildText({ code: 'owner', name: '业主', children: PersonComponent.prototype.fields })
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

    initForm(): void {
        if (this.floor) {
            this.filterForm = { 'floor.id': this.floor.id };
            this.editForm = { floor: this.floor };
        }
    }

    afterInit(): void {
        if (this.floor) {
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
        if (this.floor) {
            this.editForm.floor = this.floor;
        }
        if (!this.editForm.owner) {
            this.editForm.owner = {};
        }
    }

    afterFilterFormReset(): void {
        if (this.floor) {
            this.filterForm['floor.id'] = this.floor.id;
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
                success: (buildings: any) => {
                    this.floorService.findAll({ 'building.id': EntityUtils.getEntityIds(buildings) }, {
                        success: (floors: any) => {
                            this.navData = EntityUtils.convertListToTree(buildings);
                            const leaves = [];
                            const queue = [];
                            this.navData.forEach(b => queue.push(b));
                            while (queue.length > 0) {
                                const node = queue.pop();
                                if (node.isLeaf) {
                                    leaves.push(node);
                                } else {
                                    node.children.forEach(child => queue.push(child));
                                }
                            }

                            leaves.forEach(leaf => {
                                leaf.children = EntityUtils.convertListToTree(floors.filter(f => f.building.id === leaf.key),
                                    (entity: any) => Object.assign({
                                        title: entity.name,
                                        value: entity,
                                        key: entity.id,
                                        isLeaf: true
                                    }));
                                if (leaf.children && leaf.children.length > 0) {
                                    leaf.isLeaf = false;
                                } else {
                                    leaf.isLeaf = true;
                                }
                            });

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
            });
        }
    }

    nav(event: NzFormatEmitEvent): void {
        if (this.community) {
            if (this.community.id === event.node.key) {
                this.filterForm['community.id'] = this.community.id;
                this.building = null;
                delete this.filterForm['building.id'];
                delete this.filterForm['floor.id'];
            } else if (event.node.origin.value.community && this.community.id === event.node.origin.value.community.id) {
                this.building = event.node.origin.value;
                this.filterForm['building.id'] = this.building.id;
                delete this.filterForm['community.id'];
                delete this.filterForm['floor.id'];
            } else {
                this.floor = event.node.origin.value;
                this.initForm();
                delete this.filterForm['community.id'];
                delete this.filterForm['building.id'];
            }
        }
        this.list();
    }

}
