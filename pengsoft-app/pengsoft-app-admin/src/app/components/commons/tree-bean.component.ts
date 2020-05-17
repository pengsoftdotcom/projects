import { OnInit } from '@angular/core';
import { NzFormatEmitEvent } from 'ng-zorro-antd';
import { TreeBeanService } from 'src/app/services/commons/tree-bean.service';
import { EntityUtils } from 'src/app/utils/entity-utils';
import { FieldUtils } from 'src/app/utils/field-utils';
import { BeanComponent } from './bean.component';
import { Field } from './form-item/field';
import { InputComponent } from './input/input.component';

export abstract class TreeBeanComponent<S extends TreeBeanService> extends BeanComponent<S> implements OnInit {

    get fields(): Array<Field> {
        return [
            FieldUtils.buildTreeSelect({
                code: 'parent', name: '上级',
                list: { visible: false },
                edit: {
                    input: {
                        lazy: this.lazy,
                        load: (inputComponent: InputComponent, event?: NzFormatEmitEvent) => {
                            const self = this.editForm;
                            if (this.lazy) {
                                const parent = event ? event.node.origin.value : null;
                                this.bean.findAllExcludeSelfAndItsChildrenByParent(parent, self, null, {
                                    before: () => inputComponent.loading = true,
                                    success: (res: any) => {
                                        if (event) {
                                            event.node.addChildren(EntityUtils.convertListToTree(res));
                                        } else {
                                            inputComponent.field.edit.input.options = EntityUtils.convertListToTree(res);
                                        }
                                    },
                                    after: () => inputComponent.loading = false
                                });
                            } else {
                                this.bean.findAllExcludeSelfAndItsChildren(self, null, {
                                    before: () => inputComponent.loading = true,
                                    success: (res: any) =>
                                        inputComponent.field.edit.input.options = EntityUtils.convertListToTree(res),
                                    after: () => inputComponent.loading = false
                                });
                            }
                        }
                    }
                }
            })
        ];
    }

    abstract get lazy(): boolean;

    list(): void {
        if (this.lazy) {
            this.bean.findAllByParent(null, this.filterForm, {
                before: () => this.listComponent.loading = true,
                success: (res: any) => {
                    this.listData = EntityUtils.convertTreeToList(EntityUtils.convertListToTree(res), node => {
                        const value = node.value;
                        value.expand = false;
                        value.loaded = false;
                        value.children = node.children;
                        return value;
                    });
                },
                after: () => this.listComponent.loading = false
            });
        } else {
            this.bean.findAll(this.filterForm, {
                before: () => this.listComponent.loading = true,
                success: (res: any) => {
                    this.listData = EntityUtils.convertTreeToList(EntityUtils.convertListToTree(res), node => {
                        const value = node.value;
                        value.expand = true;
                        value.loaded = true;
                        value.children = node.children;
                        return value;
                    });
                },
                after: () => this.listComponent.loading = false
            });
        }
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
