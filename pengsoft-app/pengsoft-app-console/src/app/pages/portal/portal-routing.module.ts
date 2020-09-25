import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ColumnComponent } from './column/column.component';


const routes: Routes = [
    {
        path: 'portal',
        data: { name: '门户管理', icon: 'global' },
        children: [
            { path: 'column', component: ColumnComponent, data: { code: 'portal::column::find_all', name: '栏目' } },
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class PortalRoutingModule { }
