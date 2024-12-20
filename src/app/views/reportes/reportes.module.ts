import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReportesComponent } from './reportes.component';
import { ReportesRoutingModule } from '../reportes/reportes-routing.module';
import { SharedModule } from "../../shared/shared.module";



@NgModule({
  declarations: [ReportesComponent],
  imports: [
    CommonModule,
    ReportesRoutingModule,
    SharedModule
]
})
export class ReportesModule { }
