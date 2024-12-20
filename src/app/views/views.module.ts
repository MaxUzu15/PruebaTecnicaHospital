import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ViewsComponent } from './views.component';
import { ViewsRoutingModule } from './views-routing.module';
import { SharedModule } from '../shared/shared.module';
import { HttpClient, HttpClientModule } from '@angular/common/http';




@NgModule({
  declarations: [
    ViewsComponent
  ],
  imports: [
    CommonModule,
    ViewsRoutingModule,
    SharedModule,
    HttpClientModule
  ]
})
export class ViewsModule { }
