import { NzTreeNodeOptions } from "ng-zorro-antd";
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

    load?: (component: InputComponent, event?: any) => void;

    rows?: number;

    lazy?: boolean;

    multiple?: boolean;

}