import { Field } from "./field";
import { Input } from "./input";
import { Label } from "./label";


export interface Edit {

    code?: string;

    required?: boolean;

    disabled?: boolean | ((field: Field, form: any) => boolean);

    visible?: boolean | ((field: Field, form: any) => boolean);

    label?: Label;

    input?: Input;

}