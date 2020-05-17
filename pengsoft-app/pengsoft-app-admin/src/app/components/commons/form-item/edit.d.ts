import { Field } from "./field";
import { Input } from "./input";
import { Label } from "./label";


export interface Edit {

    required?: boolean;

    disabled?: boolean;

    visible?: boolean | ((field: Field, form: any) => boolean);

    label?: Label;

    input?: Input;

}