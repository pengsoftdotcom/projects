import { Component, ViewChild } from '@angular/core';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { EditOneToManyComponent } from 'src/app/components/commons/edit-one-to-many/edit-one-to-many.component';
import { TreeEntityComponent } from 'src/app/components/commons/tree-entity.component';
import { CommunityService } from 'src/app/services/basedata/community.service';
import { RegionService } from 'src/app/services/system/region.service';
import { FieldUtils } from 'src/app/utils/field-utils';
import { BuildingComponent } from '../building/building.component';

@Component({
    selector: 'app-community',
    templateUrl: './community.component.html',
    styleUrls: ['./community.component.scss']
})
export class CommunityComponent extends TreeEntityComponent<CommunityService> {

    @ViewChild('buildingsComponent', { static: true }) buildingsComponent: EditOneToManyComponent;

    constructor(
        private region: RegionService,
        protected entity: CommunityService,
        protected modal: NzModalService,
        protected message: NzMessageService
    ) {
        super(entity, modal, message);
    }

    get lazy(): boolean {
        return false;
    }
    get parentParams(): any {
        return null;
    }

    initFields(): void {
        super.initFields();

        this.fields.splice(1, 0,
            FieldUtils.buildTextForName({ list: { width: 200, align: 'center' } }),
            FieldUtils.buildCascaderForRegion(this.region, { edit: { required: true } }),
            FieldUtils.buildTextarea({ code: 'address', name: '详细地址', list: { visible: true } })
        );
    }

    initListActionButtons(): void {
        super.initListActionButtons();
        this.listActionButtons.splice(0, 0, {
            name: '楼栋', type: 'link', divider: true, width: 47, authority: 'basedata::building::find_all',
            action: (community: any) => this.editBuildings(community)
        });
    }

    editBuildings(community: any): void {
        this.buildingsComponent.component = BuildingComponent;
        this.buildingsComponent.params = { title: community.name, community, showSwitcher: false };
        this.buildingsComponent.show();
    }

}
