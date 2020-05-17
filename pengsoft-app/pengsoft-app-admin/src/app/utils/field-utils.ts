import { Edit } from '../components/commons/form-item/edit';
import { Field } from '../components/commons/form-item/field';
import { Input } from '../components/commons/form-item/input';
import { Label } from '../components/commons/form-item/label';
import { List } from '../components/commons/form-item/list';
import { InputType } from '../enums/input-type.enum';
import { DomSanitizer } from '@angular/platform-browser';

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

    static buildText(field: Field): Field {
        const edit = this.getEdit(field.edit);
        edit.input.type = InputType.TEXT;
        return { code: field.code, name: field.name, list: this.getList(field.list), edit };
    }

    static buildTexarea(field: Field): Field {
        const list = this.getList(field.list);
        list.visible = false;
        const edit = this.getEdit(field.edit);
        edit.input.type = InputType.TEXTAREA;
        return { code: field.code, name: field.name, list, edit };
    }

    static buildNumber(field: Field): Field {
        const edit = this.getEdit(field.edit);
        edit.input.type = InputType.NUMBER;
        return { code: field.code, name: field.name, list: this.getList(field.list), edit };
    }

    static buildPassword(field: Field): Field {
        const list = this.getList(field.list);
        list.visible = false;
        const edit = this.getEdit(field.edit);
        edit.input.type = InputType.PASSWORD;
        return { code: field.code, name: field.name, list, edit };
    }

    static buildSelect(field: Field): Field {
        const edit = this.getEdit(field.edit);
        edit.input.type = InputType.SELECT;
        return { code: field.code, name: field.name, list: this.getList(field.list), edit };
    }

    static buildDatetime(field: Field): Field {
        const edit = this.getEdit(field.edit);
        edit.input.type = InputType.DATETIME;
        edit.input.placeholder = '请选择日期';
        return { code: field.code, name: field.name, list: this.getList(field.list), edit };
    }

    static buildBoolean(field: Field): Field {
        const edit = this.getEdit(field.edit);
        edit.input.type = InputType.BOOLEAN;
        return { code: field.code, name: field.name, list: this.getList(field.list), edit };
    }

    static getList(list: List): List {
        return Object.assign({
            filterable: false,
            sortable: false,
            sortPriority: 99,
            visible: true,
            align: 'left'
        }, list);
    }

    static getEdit(edit: Edit): Edit {
        return Object.assign({
            visible: true,
            label: this.getLabel(),
            input: this.getInput()
        }, edit);
    }

    static getLabel(): Label {
        return { span: 4 };
    }

    static getInput(): Input {
        return { span: 20 };
    }

}
