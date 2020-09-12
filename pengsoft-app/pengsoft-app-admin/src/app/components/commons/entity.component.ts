import { OnInit, ViewChild, Input } from '@angular/core';
import { NzMessageService, NzModalService, NzTreeNodeOptions } from 'ng-zorro-antd';
import { EntityService } from 'src/app/services/commons/entity.service';
import { BaseComponent } from './base.component';
import { Button } from './button/button';
import { EditComponent } from './edit/edit.component';
import { FilterComponent } from './filter/filter.component';
import { Field } from './form-item/field';
import { ListComponent } from './list/list.component';
import { Page } from './list/page';

export abstract class EntityComponent<S extends EntityService> extends BaseComponent implements OnInit {

    filterForm: any = {};

    filterWidth = 900;

    filterSpan = 12;

    editForm: any = {};

    editToolbarButtons: Array<Button> = [];

    fields: Array<Field> = [];

    listData: Array<any> = [];

    pageData: Page = { page: 1, size: 20, total: 1, sort: [] };

    listToolbarButtons: Array<Button> = [];

    listActionButtons: Array<Button> = [];

    @Input() allowLoadNavData = true;

    navData: Array<NzTreeNodeOptions>;

    errors = {};

    @ViewChild('listComponent', { static: true }) listComponent: ListComponent;

    @ViewChild('editComponent', { static: true }) editComponent: EditComponent;

    constructor(
        protected entity: S,
        protected modal: NzModalService,
        protected message: NzMessageService
    ) {
        super();
    }

    ngOnInit(): void {
        this.initFields();
        this.fields.filter(field => field.children).forEach(field => field.children.forEach(subfield => subfield.parentCode = field.code));
        this.intEditToolbarButtons();
        this.initListToolbarButtons();
        this.initListActionButtons();
        this.afterInit();
    }

    abstract initFields(): void;

    intEditToolbarButtons(): void {
        this.editToolbarButtons = [
            { name: '保存', type: 'primary', size: 'default', action: () => this.save(), authority: this.getAuthority('save') }
        ];
    }

    initListToolbarButtons(): void {
        this.listToolbarButtons = [
            { name: '新增', type: 'primary', action: () => this.edit(), authority: this.getAuthority('findOne') },
            { name: '批量删除', type: 'primary', danger: true, action: () => this.delete(), authority: this.getAuthority('delete') },
            { name: '刷新', type: 'default', action: () => this.list() }
        ];
        if (this.fields.some(field => field.filter)
            || this.fields.filter(field => field.children).some(field => field.children.some(subfield => subfield.filter))) {
            this.listToolbarButtons.splice(0, 0, { name: '搜索', type: 'link', alignRight: true, action: () => this.filter() });
        }
    }

    initListActionButtons(): void {
        this.listActionButtons = [
            { name: '修改', type: 'link', divider: true, width: 47, action: (row: any) => this.edit(row), authority: this.getAuthority('findOne') },
            { name: '删除', type: 'link', danger: true, width: 30, action: (row: any) => this.delete(row), authority: this.getAuthority('delete') }
        ];
    }

    afterInit(): void {
        this.list();
    }

    edit(row?: any): void {
        this.beforeEditFormFilled();
        const id = row ? row.id : null;
        this.entity.findOne(id, {
            before: () => this.editComponent.loading = true,
            success: (res: any) => {
                this.editForm = res;
                this.afterEditFormFilled();
                this.editComponent.show();
            },
            after: () => this.editComponent.loading = false
        });
    }

    beforeEditFormFilled(): void {
        this.editForm = {};
        this.errors = {};
    }

    afterEditFormFilled(): void { }

    save(): void {
        const form = Object.assign({}, this.editForm);
        for (const key in form) {
            if (form.hasOwnProperty(key) && key.indexOf('.') > -1) {
                const code = key.split('.');
                if (!form[code[0]]) {
                    form[code[0]] = {};
                }
                form[code[0]][code[1]] = form[key];
                delete form[key];
            }
        }
        this.entity.save(form, {
            errors: this.errors,
            before: () => this.editComponent.loading = true,
            success: (res: any) => {
                // this.editForm = res;
                this.message.info('保存成功');
                this.editComponent.hide();
                this.list();
            },
            after: () => this.editComponent.loading = false
        });
    }

    filter(): void {
        this.modal.create({
            nzBodyStyle: { padding: '16px', marginBottom: '-24px' },
            nzTitle: '搜索',
            nzWidth: this.filterWidth,
            nzContent: FilterComponent,
            nzComponentParams: {
                span: this.filterSpan,
                form: this.filterForm,
                fields: this.fields
            },
            nzOnOk: () => this.list(),
            nzCancelText: '重置',
            nzOnCancel: () => {
                this.filterForm = {};
                this.afterFilterFormReset();
            }
        });
    }

    afterFilterFormReset(): void {
        this.list();
    }

    list(): void {
        this.entity.findPage(this.filterForm, this.pageData, {
            before: () => this.listComponent.loading = true,
            success: (res: any) => {
                this.listComponent.allChecked = false;
                this.listComponent.indeterminate = false;
                this.listData = res.content;
                this.pageData.total = res.totalElements;
            },
            after: () => this.listComponent.loading = false
        });
    }

    delete(row?: any): void {
        const ids = row ? [row.id] : this.listData.filter(entity => entity.checked).map(entity => entity.id);
        if (ids.length > 0) {
            this.modal.confirm({
                nzTitle: '确定要删除这些数据吗？',
                nzOnOk: () => new Promise(resolve => {
                    this.entity.delete(ids, {
                        success: () => {
                            this.afterListDataDeleted(ids.map(id => this.listData.find(value => value.id === id)));
                            this.listComponent.allChecked = false;
                            this.listComponent.indeterminate = false;
                            this.message.info('删除成功');
                            resolve();
                        }
                    });
                })
            });
        }
    }

    afterListDataDeleted(deletedRows: Array<any>): void {
        this.list();
    }

    getAuthority(action: string): string {
        if (action.indexOf('::') === -1) {
            const moduleCode = this.entity.modulePath.replace(/\//g, '_').replace(/-/g, '_');
            const entityCode = this.entity.entityPath.replace(/\//g, '_').replace(/-/g, '_');
            let actionCode = '';
            const length = action.length;
            for (let index = 0; index < length; index++) {
                const c = action.charAt(index);
                if (c === c.toUpperCase()) {
                    actionCode += '_' + c.toLowerCase();
                } else {
                    actionCode += c;
                }
            }
            return [moduleCode, entityCode, actionCode].join('::');
        } else {
            return action;
        }
    }

    sort(): void {
        const sortInfo = {};
        this.listData.filter(d => d.dirty).forEach(data => sortInfo[data.id] = data.sequence);
        if (Object.keys(sortInfo).length > 0) {
            this.entity.sort(sortInfo, {
                before: () => this.loading = true,
                success: () => {
                    this.message.info('排序成功');
                    this.list();
                },
                after: () => this.loading = false
            });
        }
    }

}
