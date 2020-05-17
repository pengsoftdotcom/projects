import { OnInit, ViewChild } from '@angular/core';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { BeanService } from 'src/app/services/commons/bean.service';
import { TreeBeanService } from 'src/app/services/commons/tree-bean.service';
import { BaseComponent } from './base.component';
import { Button } from './button/button';
import { EditComponent } from './edit/edit.component';
import { Field } from './form-item/field';
import { FilterComponent } from './filter/filter.component';
import { ListComponent } from './list/list.component';
import { Page } from './list/page';

export abstract class BeanComponent<S extends BeanService> extends BaseComponent implements OnInit {

    filterForm: any = {};

    filterWidth = 600;

    filterSpan = 12;

    editForm: any = {};

    editSpan = 24;

    listData = [];

    pageData: Page = { page: 1, size: 20, total: 1, sort: [] };

    errors = {};

    @ViewChild('listComponent', { static: true }) listComponent: ListComponent;

    @ViewChild('editComponent', { static: true }) editComponent: EditComponent;

    constructor(protected bean: S, protected modal: NzModalService, protected message: NzMessageService) { super(); }

    ngOnInit() {
        this.list();
    }

    abstract get fields(): Array<Field>;

    get editButtons(): Array<Button> {
        return [
            { name: '保存', action: () => this.save() }
        ];
    }

    get listToolbarButtons(): Array<Button> {
        return [
            {
                name: '搜索', type: 'link', action: () => this.filter(),
                authority: [
                    this.getAuthority('findPage'),
                    this.getAuthority('findAll'),
                    this.getAuthority('findAllByParent')
                ].join(',')
            },
            { name: '新增', type: 'primary', action: () => this.edit(), authority: this.getAuthority('findOne') },
            { name: '批量删除', type: 'primary', danger: true, action: () => this.delete(), authority: this.getAuthority('delete') }
        ];
    }

    get listActionButtons(): Array<Button> {
        return [
            { name: '修改', type: 'link', divider: true, width: 45, action: (row: any) => this.edit(row), authority: this.getAuthority('findOne') },
            { name: '删除', type: 'link', danger: true, width: 28, action: (row: any) => this.delete(row), authority: this.getAuthority('delete') }
        ];
    }

    get editToolbarButtons(): Array<Button> {
        return [
            { name: '保存', type: 'primary', size: 'default', action: () => this.save(), authority: this.getAuthority('save') }
        ];
    }

    edit(row?: any): void {
        this.errors = {};
        this.editForm = {};
        const id = row ? row.id : null;
        this.editComponent.show();
        this.bean.findOne(id, {
            before: () => this.editComponent.loading = true,
            success: (res: any) => this.editForm = res,
            after: () => this.editComponent.loading = false
        });
    }

    save(): void {
        this.bean.save(this.editForm, {
            errors: this.errors,
            before: () => this.editComponent.loading = true,
            success: (res: any) => {
                this.editForm = res;
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
                this.list();
            }
        });
    }

    list(): void {
        this.bean.findPage(this.filterForm, this.pageData, {
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
        const ids = row ? [row.id] : this.listData.filter(bean => bean.checked).map(bean => bean.id);
        if (ids.length > 0) {
            this.modal.confirm({
                nzTitle: '确定要删除这些数据吗？',
                nzOnOk: () => new Promise(resolve => {
                    this.bean.delete(ids, {
                        success: () => {
                            this.listComponent.allChecked = false;
                            this.listComponent.indeterminate = false;
                            this.message.info('删除成功');
                            resolve();
                            if (this.bean instanceof TreeBeanService) {
                                ids.forEach(id => {
                                    const deleted = this.listData.splice(this.listData.findIndex(value => value.id === id), 1)[0];
                                    // delete all children
                                    this.listData.filter(value => value.parentIds.indexOf(deleted.parentIds) === 0)
                                        .forEach(value => this.listData.splice(this.listData.findIndex(v => v.id = value.id), 1));
                                    // update the 'leaf' property of the deleted node's parent
                                    this.listData.filter(value => value.id === deleted.parent.id)
                                        .forEach(value => value.leaf = !this.listData.some(v => v.parentIds === deleted.parentIds));
                                });
                            } else {
                                this.list();
                            }
                        }
                    });
                })
            });
        }
    }

    getAuthority(action: string): string {
        if (action.indexOf('::') === -1) {
            const moduleCode = this.bean.getModulePath().replace(/\//g, '_').replace(/-/g, '_');
            const entityCode = this.bean.getEntityPath().replace(/\//g, '_').replace(/-/g, '_');
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

}
