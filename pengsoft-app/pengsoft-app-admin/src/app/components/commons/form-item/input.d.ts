import { NzTreeNodeOptions, NzCascaderOption } from "ng-zorro-antd";
import { InputType } from "src/app/enums/input-type.enum";
import { InputComponent } from "../input/input.component";
import { Option } from "./option";

export interface Input {

    visible?: boolean,

    placeholder?: string,

    span?: number;

    type?: InputType,

    prefixIcon?: string,

    suffixIcon?: string,

    modelChange?: (component: InputComponent) => void;

    options?: Array<Option | NzTreeNodeOptions>

    loaded?: boolean;

    load?: (component: any, event?: any | number) => any;

    rows?: number;

    lazy?: boolean;

    multiple?: boolean;

    width?: number;

    height?: number;

}