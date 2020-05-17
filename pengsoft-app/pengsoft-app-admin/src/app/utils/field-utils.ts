import { Edit } from '../components/commons/form-item/edit';
import { Field } from '../components/commons/form-item/field';
import { Input } from '../components/commons/form-item/input';
import { Label } from '../components/commons/form-item/label';
import { List } from '../components/commons/form-item/list';
import { InputType } from '../enums/input-type.enum';
import { DomSanitizer } from '@angular/platform-browser';
import { fileURLToPath } from 'url';

export class FieldUtils {

    static buildBooleanForEnabled(field?: Field): Field {
        field = Object.assign({}, field);
        field = Object.assign(field, {
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
        });
        return this.buildBoolean(field);
    }

    static buildBooleanForLocked(): Field {
        const field: Field = {
            code: 'locked', name: '是否锁定',
            list: {
                render: (f: Field, row: any, sanitizer: DomSanitizer) => {
                    const locked = row.locked;
                    if (locked) {
                        return sanitizer.bypassSecurityTrustHtml('<span style="color: #0b8235">是</span>');
                    } else {
                        return sanitizer.bypassSecurityTrustHtml('<span style="color: #ff4d4f">否</span>');
                    }
                }
            },
            edit: { disabled: true }
        };
        return this.buildBoolean(field);
    }

    static buildText(field: Field): Field {
        field = this.getList(field);
        field = this.getEdit(field);
        field.edit.input.type = InputType.TEXT;
        return field;
    }

    static buildTexarea(field: Field): Field {
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
        field = this.getList(field);
        field = this.getInput(field, { lazy: false, multiple: false, options: [], placeholder: '请选择' });
        field = this.getEdit(field);
        field.edit.input.type = InputType.SELECT;
        return field;
    }

    static buildTreeSelect(field: Field): Field {
        field = this.getList(field);
        field = this.getInput(field, { lazy: false, multiple: false, options: [], placeholder: '请选择' });
        field = this.getEdit(field);
        field.edit.input.type = InputType.TREE_SELECT;
        return field;
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

    static buildBoolean(field: Field): Field {
        field = this.getList(field);
        field.list.width = 80;
        field.list.align = 'center';
        field = this.getEdit(field);
        field.edit.input.type = InputType.BOOLEAN;
        return field;
    }

    static getList(field: Field): Field {
        field.list = Object.assign({
            filterable: false,
            sortable: false,
            sortPriority: 99,
            visible: true,
            align: 'left'
        }, field.list);
        return field;
    }

    static getEdit(field: Field, edit?: Edit): Field {
        field.edit = Object.assign({ visible: true }, field.edit);
        if (edit && edit.label) {
            field = this.getLabel(field, edit.label);
        } else {
            field = this.getLabel(field);
        }
        if (edit && edit.input) {
            field = this.getInput(field, edit.input);
        } else {
            field = this.getInput(field);
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
