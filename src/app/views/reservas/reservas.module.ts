import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReservasRoutingModule } from './reservas-routing.module';
import { ReservasComponent } from './reservas.component';
import { SharedModule } from "../../shared/shared.module";
import { HttpClient, HttpClientModule } from '@angular/common/http';



@NgModule({
  declarations: [ReservasComponent],
  imports: [
    CommonModule,
    ReservasRoutingModule,
    SharedModule,
    HttpClientModule
]
})
export class ReservasModule { }
