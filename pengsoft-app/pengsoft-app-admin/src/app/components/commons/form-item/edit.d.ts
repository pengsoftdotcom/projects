import { Input } from "./input";
import { Label } from "./label";


export interface Edit {
    required?: boolean;
    disabled?: boolean;
    visible?: boolean;
    label?: Label;
    input?: Input;
}