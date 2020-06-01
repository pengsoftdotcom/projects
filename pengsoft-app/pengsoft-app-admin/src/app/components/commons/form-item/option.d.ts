import { DomSanitizer, SafeHtml } from "@angular/platform-browser";

export interface Option {
    label: string;
    value: string;
    disabled?: boolean;
    hide?: boolean;
    customContent?: boolean;
    customRender?: (sanitizer: DomSanitizer) => string | SafeHtml;
    checked?: boolean;
}