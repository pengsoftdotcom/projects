import { Field } from "./field";
import { DomSanitizer, SafeHtml } from "@angular/platform-browser";

export interface List {

    sortable?: boolean;

    sortPriority?: number;

    visible?: boolean;

    align?: 'left' | 'center' | 'right';

    label?: string;

    width?: number;

    render?: (field: Field, row: any, sanitizer: DomSanitizer) => string | SafeHtml;

}