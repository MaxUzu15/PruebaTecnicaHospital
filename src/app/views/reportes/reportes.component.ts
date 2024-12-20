import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import {DataTableComponent} from '../../shared/components/data-table/data-table.component';
import {BASE_URL_BACKEND,CONSULTA_RESERVAS} from '../../shared/home.configurations';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Component({
  selector: 'app-reportes',
  standalone: false,
  
  templateUrl: './reportes.component.html',
  styleUrl: './reportes.component.css'
})
@Injectable({
  providedIn: 'root'
})
export class ReportesComponent implements OnInit{
  
  @ViewChild(DataTableComponent, { static: false })dataTable: DataTableComponent;
  constructor(private http: HttpClient,private router: Router){
  
  }

  ngOnInit(): void {
      
  }

  consultarReporte(){
    this.dataTable.mostrarReporte(false,false,true);
  }

  ngAfterViewInit(): void { this.consultarReporte(); }
}
