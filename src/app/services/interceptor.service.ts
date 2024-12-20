import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InterceptorService {

  public titulo = new Subject<string>();
  public subtitulo = new Subject<string>();
  
  
  valorTitulos(ttulo:any,sbtitulo:any){
    this.titulo.next(ttulo);
    this.subtitulo.next(sbtitulo);
  }


  
  
}