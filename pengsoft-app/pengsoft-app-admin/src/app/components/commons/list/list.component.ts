import { Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { BaseComponent } from '../base.component';
import { Button } from '../button/button';
import { Field } from '../form-item/field';
import { Page } from './page';
import { Sort } from './sort';

@Component({
    selector: 'app-list',
    templateUrl: './list.component.html',
    styleUrls: ['./list.component.scss']
})
export class ListComponent extends BaseComponent implements OnInit {

    @Input() fields: Array<Field> = [];

    tdFields = [];

    @Input() data = [];

    @Input() page: Page;

    @Input() toolbarButtons: Array<Button> = [];

    @Input() actionButtons: Array<Button> = [];

    @Output() pageChange = new EventEmitter<Page>();

    @Output() expandChange = new EventEmitter<any>();

    @Input() sortable = false;

    @Output() sequenceChange = new EventEmitter<any>();

    @ViewChild('table', { static: true }) table: any;

    tableBodyHeight: any = 0;

    tableWidthConfig = [];

    allChecked = false;

    indeterminate = false;

    checkedCount = 0;

    pageable = true;

    treeable = false;

    groupable = false;

    firstVisibleFieldIndex = -1;

    constructor(public sanitizer: DomSanitizer) {
        super();
        this.title = '列表';
    }

    ngOnInit(): void {
        this.initTdFields();
        this.initGroupable();
        this.initTreeable();
        this.initPageable();
        this.initTableWidthConfig();
        this.initTableBodyHeight();
    }

    private initTdFields() {
        this.fields.filter(field => field.list.visible).forEach(field => {
            if (field.children) {
                field.children.filter(subfield => subfield.list.visible).forEach(subfield => {
                    subfield.list.code = field.code + '.' + subfield.code;
                    this.tdFields.push(subfield);
                });
            } else {
                field.list.code = field.code;
                this.tdFields.push(field);
            }
        });
    }

    private initGroupable() {
        this.groupable = this.fields.some(field => field.children);
    }

    private initTreeable() {
        this.treeable = this.fields.some(field => field.code === 'parent');
    }

    private initPageable() {
        if (this.page === undefined) {
            this.pageable = false;
            this.page = { page: 1, size: 20 };
        }
    }

    private initTableBodyHeight() {
        const totalHeight = this.table.elementRef.nativeElement.parentNode.parentNode.offsetHeight;
        const listTitleHeight = 41;
        const toolbarButtonsHeight = 49;
        const tableHeaderHeight = this.groupable ? 47 * 2 : 47;
        const paginationHeight = 41;

        this.tableBodyHeight = totalHeight;
        this.tableBodyHeight -= listTitleHeight;
        this.tableBodyHeight -= toolbarButtonsHeight;
        this.tableBodyHeight -= tableHeaderHeight;
        if (!this.treeable) {
            this.tableBodyHeight -= paginationHeight;
        }
        this.tableBodyHeight += 'px';
    }

    private initTableWidthConfig() {
        const totalWidth = this.table.elementRef.nativeElement.offsetWidth;
        const checkAllWidth = 46;
        const sortWidth = 82;
        let actionWidth = 17;
        this.actionButtons.forEach(button => actionWidth += button.width);

        let leftWidth = totalWidth;
        let noWidthColumbCount = 0;
        leftWidth -= checkAllWidth;
        if (this.sortable) {
            leftWidth -= sortWidth;
        }
        leftWidth -= actionWidth;
        this.fields.forEach(field => {
            if (field.children) {
                field.children.forEach(subfield =>
                    ({ leftWidth, noWidthColumbCount } = this.initLefWidthAndCount(subfield, leftWidth, noWidthColumbCount)));
            } else {
                ({ leftWidth, noWidthColumbCount } = this.initLefWidthAndCount(field, leftWidth, noWidthColumbCount));
            }
        });
        const defaultColumnWidth = leftWidth / noWidthColumbCount;

        this.tableWidthConfig.push(checkAllWidth);
        this.fields.forEach(field => {
            if (field.children) {
                field.children.forEach(subfield => this.fillWidthConfig(subfield, defaultColumnWidth));
            }
            else {
                this.fillWidthConfig(field, defaultColumnWidth);
            }
        });
        if (this.sortable) {
            this.tableWidthConfig.push(sortWidth);
        }
        this.tableWidthConfig.push(actionWidth);
        this.tableWidthConfig = this.tableWidthConfig.map(width => width + 'px');
    }

    private fillWidthConfig(field: Field, defaultColumnWidth: number) {
        if (field.list.visible) {
            if (field.list.width) {
                this.tableWidthConfig.push(field.list.width);
            }
            else {
                this.tableWidthConfig.push(defaultColumnWidth);
            }
        }
    }

    private initLefWidthAndCount(field: Field, leftWidth: any, noWidthColumbCount: number) {
        if (field.list.visible) {
            if (field.list.width) {
                leftWidth -= field.list.width;
            } else {
                noWidthColumbCount++;
            }
        }
        return { leftWidth, noWidthColumbCount };
    }

    checkAll(allChecked: boolean): void {
        this.indeterminate = false;
        this.allChecked = allChecked;
        this.checkedCount = allChecked ? this.data.length : 0;
        this.data.forEach(data => data.checked = allChecked);
    }

    check(): void {
        this.checkedCount = this.data.filter(d => d.checked).length;
        this.allChecked = this.data.every(d => d.checked);
        this.indeterminate = !this.allChecked && !this.data.every(d => !d.checked);
    }

    isRowVisible(row: any): boolean {
        if (this.treeable) {
            const parents = this.data.filter((data, i) => row.id !== data.id && row.parentIds.indexOf(data.id) > -1);
            if (parents.length > 0) {
                return parents.every(parent => parent.expand);
            }
        }
        return true;
    }

    getRowspan(field?: Field): number {
        if (this.groupable && (!field || !field.children)) {
            return 2;
        } else {
            return 1;
        }
    }

    getColspan(field: Field): number {
        if (field.children) {
            return field.children.filter(subfield => subfield.list.visible).length;
        } else {
            return 1;
        }
    }

    render(field: Field, row: any): any {
        let value = null;
        if (field.list.render) {
            let data;
            if (field.list.code.indexOf('.') > -1) {
                data = row[field.list.code.split('.')[0]];
            } else {
                data = row;
            }
            value = field.list.render(field, data, this.sanitizer);
        } else {
            // tslint:disable-next-line: no-eval
            value = eval('row.' + field.list.code);
        }
        if (value === undefined || value === null) {
            return '-';
        } else {
            return value;
        }
    }

    sortChange(field: Field, direction: string): void {
        const sort: Sort = { code: field.code, direction: null, priority: field.list.sortPriority };
        switch (direction) {
            case 'ascend':
                sort.direction = 'asc';
                break;
            case 'descend':
                sort.direction = 'desc';
                break;
            default:
                sort.direction = null;
                break;
        }
        const index = this.page.sort.findIndex(value => value.code === field.code);
        if (index > -1) {
            this.page.sort.splice(index, 1);
        }
        if (sort.direction) {
            this.page.sort.push(sort);
        }
        this.page.sort.sort((a, b) => {
            if (a.priority < b.priority) {
                return -1;
            } else if (a.priority === b.priority) {
                return 0;
            } else {
                return 1;
            }
        });
        this.pageChange.emit();
    }

}
