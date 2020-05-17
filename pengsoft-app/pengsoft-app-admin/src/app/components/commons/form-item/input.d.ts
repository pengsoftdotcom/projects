import { InputType } from "src/app/enums/input-type.enum";
import { Field } from "./field";

export interface Input {
    visible?: boolean,
    placeholder?: string,
    span?: number;
    type?: InputType,
    prefixIcon?: string,
    suffixIcon?: string,
    modelChange?: ((form: any, field: Field) => void) | string;
}