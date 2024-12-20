import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DataTableComponent } from './components/data-table/data-table.component';
import { SharedRoutingModule } from './shared-routing.module';
import { MatTableModule } from '@angular/material/table';
import {FormsModule} from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { DatePipe } from '@angular/common'


@NgModule({
  declarations: [DataTableComponent],
  imports: [
    CommonModule,
    SharedRoutingModule,
    MatTableModule,
    MatInputModule,
    MatButtonModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [DatePipe],
  exports: [
    DataTableComponent
  ]
})
export class SharedModule { }
