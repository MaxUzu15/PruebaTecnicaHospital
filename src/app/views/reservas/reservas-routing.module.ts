import { Component, NgModule } from "@angular/core";
import { RouterModule,Routes } from "@angular/router";
import { ReservasComponent } from "./reservas.component";

const routes: Routes = [{
    path: '', redirectTo: 'reservas', pathMatch: 'full'},
    {path: '', component: ReservasComponent} 
];

   @NgModule({
    imports:[RouterModule.forChild(routes)],
    exports:[RouterModule]
   })
   export class ReservasRoutingModule{}