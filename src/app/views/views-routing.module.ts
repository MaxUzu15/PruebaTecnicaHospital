import { NgModule } from "@angular/core";
import { RouterModule,Routes } from "@angular/router";
import { ViewsComponent } from "./views.component";


const routes: Routes = [{
    path: '', component: ViewsComponent,
    children:[
        {path: '', redirectTo: 'menu', pathMatch: 'full'},
        {path: 'reservas',loadChildren: () => import('./reservas/reservas.module').then(child => child.ReservasModule)},
        {path: 'menu',loadChildren: () => import('./menu/menu.module').then(child => child.MenuModule)},
        {path: 'reportes',loadChildren: () => import('./reportes/reportes.module').then(child => child.ReportesModule)}
    ]
   }]

   @NgModule({
    imports:[RouterModule.forChild(routes)],
    exports:[RouterModule]
   })
   export class ViewsRoutingModule{}