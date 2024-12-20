import { HttpClient } from '@angular/common/http';
import { ChangeDetectorRef, Component, OnInit,ViewChild } from '@angular/core';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { Injectable } from '@angular/core';
import {Subscription} from 'rxjs'
import {BASE_URL_BACKEND,CONSULTA_RESERVAS,BASE_URL_BACKEND_POST,URL_REPORTE} from '../../home.configurations';
import { ReservasComponent } from '../../../views/reservas/reservas.component';
import { ReportesRoutingModule } from '../../../views/reportes/reportes-routing.module';
import { DatePipe } from '@angular/common';


@Component({
  selector: 'app-data-table',
  standalone: false, 
  templateUrl: './data-table.component.html',
  styleUrl: './data-table.component.css'
})
@Injectable({
  providedIn: 'root'
})
export class DataTableComponent implements OnInit{

  @ViewChild(MatTable) tabla1!: MatTable<Reserva>;
  constructor(private http: HttpClient,private cdRef: ChangeDetectorRef,private datePipe: DatePipe){
    this.dataSource = new MatTableDataSource(this.datos);
  }
  ngOnInit(): void {

  }
  columnas: string[] = ['id', 'fechaInicio', 'fechaFin', 'idHabitacion', 'nombreCliente','estadoReserva','editar','borrar'];
  datos: Reserva[] = [];
  dataSource = new MatTableDataSource<Reserva>();

  tablaVisible:boolean = false;
  formVisible: boolean = false;
  filterVisible:boolean = false;
    iniciarTabla(data:any[],isTableVisible:boolean, isFormVisible: boolean,isFilterVisible:boolean){
      this.formVisible = isFormVisible;
      this.tablaVisible = isTableVisible;
      this.filterVisible = isFilterVisible;
      data.forEach(element => {
        this.datos.push(new Reserva(
          element.id,
          element.fechaInicio,
          element.fechaFin,
          element.idHabitacion,
          element.nombreCliente,
          element.estadoReserva
        ));
      });
      //this.dataSource.sort = this.sort;
      this.dataSource = new MatTableDataSource(data);
      this.cdRef.detectChanges();
      
    }

    consultarReservas(){
      let url = BASE_URL_BACKEND + CONSULTA_RESERVAS;
      this.http.get<any>(url).subscribe({
        next: (data:any) =>{
          this.iniciarTabla(data,true,true,false);
        }
      });
    }

    borrar(data: any){
      if(confirm("¿Esta seguro que desea borrar esta reserva?")){
      let url = BASE_URL_BACKEND + data.id;
      this.http.delete<any>(url).subscribe({
        next: (response) =>{
          if (response.status === 200 || response.status === 201) { 
            console.log('Reserva eliminada con éxito:', response.body); // Aquí puedes agregar lógica adicional, como actualizar la vista }
          }
        }, 
        error: (err) => { 
          if(err.status === 201){
             console.log('Reserva eliminada con éxito');
             this.consultarReservas();
          }else{
            confirm(err.error.text);
            console.error('Error al eliminar la reserva:', err); 
          }
        },complete: () => { 
          this.consultarReservas();
        }
      });
    }
    }

    editarReserva(data:any){
      if(confirm("¿Esta seguro que desea editar esta reserva?")){
      let url = BASE_URL_BACKEND + data.id;
      let reservaActualizada = new Reserva(
        data.id,
        data.fechaInicio,
        data.fechaFin,
        data.idHabitacion,
        data.nombreCliente,
        data.estadoReserva
      );
      this.http.put<any>(url,reservaActualizada).subscribe({
        next: (response) => { 
          console.log('Reserva actualizada con éxito:', response); 
          this.consultarReservas();
         }, 
         error: (err) => { 
          if(err.status === 201 && err.error.text == 'Reserva actualizada.'){
              console.log('Reserva actualizada con éxito');
              this.consultarReservas();
          }else{
            confirm(err.error.text);
          console.error('Error al actualizar la reserva:', err); 
          }
        }, 
        complete: () => { 
          this.consultarReservas();
        }
      });
    }

    }
    reservaNueva: Reserva = new Reserva(0,"","",0,"","");
    crearReserva(){
      
      this.reservaNueva.fechaInicio = this.datePipe.transform(this.reservaNueva.fechaInicio, 'dd/MM/yyyy HH:mm:ss') ?? '';
      this.reservaNueva.fechaFin = this.datePipe.transform(this.reservaNueva.fechaFin, 'dd/MM/yyyy HH:mm:ss') ?? '';
      let url = BASE_URL_BACKEND_POST;
      this.http.post<any>(url,this.reservaNueva).subscribe({
        next: (response) => { 
          console.log('Reserva creada con éxito:', response); 
          this.reservaNueva  = new Reserva(0,"","",0,"","");
          this.consultarReservas();
         }, 
         error: (err) => { 
          confirm(err.error.text);
          console.error('Error al actualizar la reserva:', err); 
          this.consultarReservas();
        }, 
        complete: () => { 
          this.consultarReservas();
        }
      });
    }
    reporteFiltro: Reserva = new Reserva(0,"","",0,"","");
    consultarReporte(){
      this.reporteFiltro.fechaInicio = this.datePipe.transform(this.reporteFiltro.fechaInicio, 'dd/MM/yyyy HH:mm:ss') ?? '';
      let url = BASE_URL_BACKEND + URL_REPORTE;
      this.http.post<any>(url,this.reporteFiltro).subscribe({
        next: (data:any) =>{
          this.iniciarTabla(data,true,false,true);
        }
      });

    }

    mostrarReporte(isTableVisible:boolean,isFormVisible:boolean,isFilterVisible:boolean){
      this.formVisible = isFormVisible;
      this.tablaVisible = isTableVisible;
      this.filterVisible = isFilterVisible;
    }

  
}


export class Reserva {
  constructor(
    public id: number, 
    public fechaInicio: string, 
    public fechaFin: string, 
    public idHabitacion: number,
    public nombreCliente: string,
    public estadoReserva: string) {
  }


}
