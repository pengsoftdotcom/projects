import { OnInit } from '@angular/core';
import { NzFormatEmitEvent } from 'ng-zorro-antd';
import { TreeBeanService } from 'src/app/services/commons/tree-bean.service';
import { EntityUtils } from 'src/app/utils/entity-utils';
import { FieldUtils } from 'src/app/utils/field-utils';
import { BeanComponent } from './bean.component';
import { InputComponent } from './input/input.component';

export abstract class TreeBeanComponent<S extends TreeBeanService> extends BeanComponent<S> implements OnInit {

    abstract get lazy(): boolean;

    abstract get parentParams(): any;

    initFields(): void {
        this.fields = [
            FieldUtils.buildTreeSelect({
                code: 'parent', name: '上级',
                list: { visible: false },
                edit: {
                    input: {
                        lazy: this.lazy,
                        load: (component: InputComponent, event?: NzFormatEmitEvent) => {
                            const self = this.editForm;
                            if (this.lazy) {
                                const parent = event ? event.node.origin.value : null;
                                this.bean.findAllExcludeSelfAndItsChildrenByParent(parent, self, this.parentParams, {
                                    before: () => component.loading = true,
                                    success: (res: any) => {
                                        if (event) {
                                            event.node.addChildren(EntityUtils.convertListToTree(res));
                                        } else {
                                            component.edit.input.options = EntityUtils.convertListToTree(res);
                                        }
                                    },
                                    after: () => component.loading = false
                                });
                            } else {
                                this.bean.findAllExcludeSelfAndItsChildren(self, this.parentParams, {
                                    before: () => component.loading = true,
                                    success: (res: any) =>
                                        component.edit.input.options = EntityUtils.convertListToTree(res),
                                    after: () => component.loading = false
                                });
                            }
                        }
                    }
                }
            })
        ];
    }

    list(): void {
        if (this.lazy) {
            this.bean.findAllByParent(null, this.filterForm, {
                before: () => this.listComponent.loading = true,
                success: (res: any) => {
                    const tree = EntityUtils.convertListToTree(res);
                    const list = EntityUtils.convertTreeToList(tree, node => {
                        const value = node.value;
                        value.expand = false;
                        value.loaded = false;
                        value.children = node.children;
                        return value;
                    });
                    this.listData = list;
                },
                after: () => this.listComponent.loading = false
            });
        } else {
            this.bean.findAll(this.filterForm, {
                before: () => this.listComponent.loading = true,
                success: (res: any) => {
                    const tree = EntityUtils.convertListToTree(res);
                    const list = EntityUtils.convertTreeToList(tree, node => {
                        const value = node.value;
                        value.expand = true;
                        value.loaded = true;
                        value.children = node.children;
                        const parentIds = value.parentIds ? value.parentIds + '::' + value.id : value.id;
                        value.leaf = !res.some(data => data.parentIds.startsWith(parentIds));
                        return value;
                    });
                    this.listData = list;
                },
                after: () => this.listComponent.loading = false
            });
        }
    }

    afterListDataDeleted(deletedRows: Array<any>): void {
        deletedRows.forEach(row => {
            const i = this.listData.indexOf(row);
            this.listData.splice(i, 1);
            const parent = this.listData.find(value => row.parent && value.id === row.parent.id);
            if (parent) {
                parent.leaf = !this.listData.some(value => value.parentIds === row.parentIds);
            }
            let j: number;
            do {
                j = this.listData.findIndex(value => value.parentIds.endsWith(row.id));
                this.listData.splice(j, 1);
            } while (j > -1);
        });
    }

    loadChildren(row: any): void {
        if (!row.loaded) {
            let index = this.listData.findIndex(value => value.id === row.id);
            this.bean.findAllByParent(row, this.filterForm, {
                success: (res: any) => {
                    row.loaded = true;
                    row.expand = true;
                    res.forEach(child => {
                        child.parent = null;
                        this.listData.splice(++index, 0, child);
                    });
                }
            });
        }
    }

}
