import { Edit } from "./edit";
import { List } from "./list";

export interface Field {
    code: string;
    name?: string;
    list?: List;
    edit?: Edit;
}