import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Vehiculo } from '../entities/vehiculo';

@Injectable({
  providedIn: 'root'
})
export class VehiculoService {

    private bdURLD = "http://localhost:8080/vehiculo/buscarDisponibles";
    private bdURLDT = 'http://localhost:8080/vehiculo/buscarDisponiblesPorTipo';

    constructor(private httpClient: HttpClient) { }

    obtenerDisponibles(): Observable<Vehiculo[]> {
    return this.httpClient.get<Vehiculo[]>(this.bdURLD);
    }

    buscarDisponiblesPorTipo(tipo: string): Observable<Vehiculo[]> {
      return this.httpClient.get<Vehiculo[]>(this.bdURLDT, {
      params: { tipo }
  });
}

}