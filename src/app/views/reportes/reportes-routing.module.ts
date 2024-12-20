import { Component, NgModule } from "@angular/core";
import { RouterModule,Routes } from "@angular/router";
import { ReportesComponent } from "./reportes.component";

const routes: Routes = [{
    path: '', redirectTo: 'reportes', pathMatch: 'full'},
    {path: '', component: ReportesComponent} 
];

   @NgModule({
    imports:[RouterModule.forChild(routes)],
    exports:[RouterModule]
   })
   export class ReportesRoutingModule{}