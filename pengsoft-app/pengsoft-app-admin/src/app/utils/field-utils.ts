import { DomSanitizer } from '@angular/platform-browser';
import { Edit } from '../components/commons/form-item/edit';
import { Field } from '../components/commons/form-item/field';
import { Input } from '../components/commons/form-item/input';
import { Label } from '../components/commons/form-item/label';
import { List } from '../components/commons/form-item/list';
import { InputType } from '../enums/input-type.enum';
import { DateUtils } from './date-utils';

export class FieldUtils {

    static buildAvatar(field?: Field): Field {
        field = Object.assign({ code: 'avatar', name: '头像' }, field);
        field = this.getList(field, { visible: false });
        field = this.getInput(field, { width: 100, height: 100 });
        field = this.getEdit(field);
        field.edit.input.type = InputType.AVATAR;
        return field;
    }

    static buildTextForBusinessKey(): Field {
        return this.buildText({
            code: 'businessKey', name: '业务键',
            list: { sortable: true },
            edit: { label: { tooltip: '外部系统中的唯一标志符' } },
            filter: {}
        });
    }

    static buildTextForCode(list?: List): Field {
        return this.buildText({
            code: 'code', name: '编码',
            list: Object.assign({
                sortable: true, sortPriority: 1,
                render: (field: Field, row: any, sanitizer: DomSanitizer) => sanitizer.bypassSecurityTrustHtml(`<code>${row.code}</code>`)
            }, list),
            edit: { required: true },
            filter: {}
        });
    }

    static buildTextForName(field?: Field): Field {
        field = Object.assign({ code: 'name', name: field && field.name ? field.name : '名称' }, field);
        field = this.getList(field, { sortable: true });
        field = this.getEdit(field, { required: true });
        field.filter = {};
        return this.buildText(field);
    }

    static buildText(field: Field): Field {
        field = this.getList(field);
        field = this.getEdit(field);
        field.edit.input.type = InputType.TEXT;
        return field;
    }

    static buildTexareaForRemark(): Field {
        return this.buildTextarea({ code: 'remark', name: '备注' });
    }

    static buildTextarea(field: Field): Field {
        field = this.getList(field);
        field.list.visible = false;
        field = this.getEdit(field);
        field.edit.input.type = InputType.TEXTAREA;
        return field;
    }

    static buildNumber(field: Field): Field {
        field = this.getList(field);
        field.list.align = 'right';
        field = this.getEdit(field);
        field.edit.input.type = InputType.NUMBER;
        return field;
    }

    static buildPassword(field: Field): Field {
        field = this.getList(field);
        field.list.visible = false;
        field = this.getEdit(field);
        field.edit.input.type = InputType.PASSWORD;
        return field;
    }

    static buildSelect(field: Field): Field {
        field = this.getList(field, { render: (f: Field, row: any) => row[field.code] ? row[field.code].name : null });
        field = this.getInput(field, { lazy: false, multiple: false, options: [], placeholder: '请选择' });
        field = this.getEdit(field);
        field.edit.input.type = InputType.SELECT;
        return field;
    }

    static buildTreeSelect(field: Field): Field {
        field = this.getList(field, { render: (f: Field, row: any) => row[field.code] ? row[field.code].name : null });
        field = this.getInput(field, { lazy: false, multiple: false, options: [], placeholder: '请选择' });
        field = this.getEdit(field);
        field.edit.input.type = InputType.TREE_SELECT;
        return field;
    }

    static buildCascader(field: Field): Field {
        field = this.getList(field, { render: (f: Field, row: any) => row[field.code] ? row[field.code].name : null });
        field = this.getInput(field, { lazy: false, multiple: false, options: [], placeholder: '请选择' });
        field = this.getEdit(field);
        field.edit.input.type = InputType.CASCADER;
        return field;
    }

    static buildDatetimeForExpiredAt(): Field {
        return this.buildDatetime({
            code: 'expiredAt', name: '过期时间',
            list: {
                render: (field: Field, row: any, sanitizer: DomSanitizer) => {
                    const expiredAt = row[field.code];
                    if (expiredAt) {
                        if (DateUtils.isAfter(expiredAt)) {
                            return sanitizer.bypassSecurityTrustHtml(`<span style="color: #0b8235">${expiredAt}</span>`);
                        } else {
                            return sanitizer.bypassSecurityTrustHtml(`<span style="color: #ff4d4f">${expiredAt}</span>`);
                        }
                    }
                    return null;
                }
            },
            filter: { input: { placeholder: '早于输入的时间' } }
        });
    }

    static buildDatetime(field: Field): Field {
        field = this.getList(field);
        field.list.width = 170;
        field.list.align = 'center';
        field = this.getInput(field, { placeholder: '请选择' });
        field = this.getEdit(field);
        field.edit.input.type = InputType.DATETIME;
        return field;
    }

    static buildBooleanForEnabled(field?: Field): Field {
        field = Object.assign({
            code: 'enabled', name: '是否启用', list: {
                render: (f: Field, row: any, sanitizer: DomSanitizer) => {
                    const enabled = row.enabled;
                    if (enabled) {
                        return sanitizer.bypassSecurityTrustHtml('<span style="color: #0b8235">启用</span>');
                    } else {
                        return sanitizer.bypassSecurityTrustHtml('<span style="color: #ff4d4f">禁用</span>');
                    }
                }
            }
        }, field);
        return this.buildBoolean(field);
    }

    static buildBooleanForLocked(field?: Field): Field {
        field = Object.assign({
            code: 'locked', name: '是否锁定',
            list: {
                render: (f: Field, row: any, sanitizer: DomSanitizer) => {
                    if (row[field.code]) {
                        return sanitizer.bypassSecurityTrustHtml('<span style="color: #0b8235">是</span>');
                    } else {
                        return sanitizer.bypassSecurityTrustHtml('<span style="color: #ff4d4f">否</span>');
                    }
                }
            },
            edit: { disabled: true }
        }, field);
        return this.buildBoolean(field);
    }

    static buildBoolean(field: Field): Field {
        field = this.getList(field);
        field.list.width = 80;
        field.list.align = 'center';
        field = this.getEdit(field);
        field.edit.input.type = InputType.BOOLEAN;
        return field;
    }

    static getList(field: Field, list?: List): Field {
        list = Object.assign({
            filterable: false,
            sortable: false,
            sortPriority: 99,
            visible: true,
            align: 'left'
        }, list);
        field.list = Object.assign(list, field.list);
        return field;
    }

    static getEdit(field: Field, edit?: Edit): Field {
        field.edit = Object.assign({ visible: true }, field.edit);
        if (edit) {
            for (const key in edit) {
                if (edit.hasOwnProperty(key) && key !== 'label' && key !== 'input' && field.edit[key] === undefined) {
                    field.edit[key] = edit[key];
                }
            }
            if (edit.label) {
                field = this.getLabel(field, edit.label);
            }
            if (edit.input) {
                field = this.getInput(field, edit.input);
            }
        } else {
            field = this.getLabel(field);
            field = this.getInput(field);

        }
        if (field.filter) {
            field.filter.label = Object.assign(field.edit.label, field.filter.label);
            field.filter.input = Object.assign(field.edit.input, field.filter.input);
        }
        return field;
    }

    static getLabel(field: Field, label?: Label): Field {
        field.edit = Object.assign({}, field.edit);
        field.edit.label = Object.assign({ span: 4 }, field.edit.label);
        if (label) {
            field.edit.label = Object.assign(label, field.edit.label);
        }
        return field;
    }

    static getInput(field: Field, input?: Input): Field {
        field.edit = Object.assign({}, field.edit);
        field.edit.input = Object.assign({ span: 20 }, field.edit.input);
        if (input) {
            field.edit.input = Object.assign(input, field.edit.input);
        }
        return field;
    }

}
