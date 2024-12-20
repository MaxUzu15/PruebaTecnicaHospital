import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import {DataTableComponent} from '../../shared/components/data-table/data-table.component';
import {BASE_URL_BACKEND,CONSULTA_RESERVAS} from '../../shared/home.configurations';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Component({
  selector: 'app-reservas',
  standalone: false,
  
  templateUrl: './reservas.component.html',
  styleUrl: './reservas.component.css'
})
@Injectable({
  providedIn: 'root'
})
export class ReservasComponent implements OnInit{
  @ViewChild(DataTableComponent, { static: false })
  dataTable: DataTableComponent;

  constructor(private http: HttpClient,private router: Router){
  
  }

    ngOnInit(): void {
        this.consultarReservas();
    }

    consultarReservas(){
      let url = BASE_URL_BACKEND + CONSULTA_RESERVAS;
      this.http.get<any>(url).subscribe({
        next: (data:any) =>{
          this.dataTable.iniciarTabla(data,true,true,false);
        }
      });
    }

  

}
