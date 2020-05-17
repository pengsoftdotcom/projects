export interface Button {

    name: string;

    authority?: string;

    type?: 'primary' | 'default' | 'link';

    danger?: boolean;

    divider?: boolean;

    width?: number;

    size?: 'large' | 'small' | 'default';

    action: (params?: any) => void;

}