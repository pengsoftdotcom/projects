import { Input } from '@angular/core';

export class BaseComponent {

    @Input() title: string;

    @Input() width = '100%';

    @Input() height = '100%';

    @Input() visible = true;

    @Input() loading = false;

    show(): void {
        this.visible = true;
    }

    hide(): void {
        this.visible = false;
    }

}
