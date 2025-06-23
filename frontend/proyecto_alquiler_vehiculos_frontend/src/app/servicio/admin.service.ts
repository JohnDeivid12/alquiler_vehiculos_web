import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Admin } from '../entities/admin';
import { Vehiculo } from '../entities/vehiculo';



@Injectable({
  providedIn: 'root'
})


export class AdminService {

  private bdURLS = "http://localhost:8080/admin/iniciarSesion";
  private bdURLD = "http://localhost:8080/alquiler/devolucion";
  private bdURLP = "http://localhost:8080/admin/vehiculosPendientes";
  private bdURLE = "http://localhost:8080/admin/entregarVehiculo";
  private bdURLR = "http://localhost:8080/admin/vehiculos-disponibles";

  constructor(private httpClient: HttpClient) { }

   iniciarSesion(usuarioAdmin:string, contrasena_admin:string ): Observable<any> {
    const params = new HttpParams()
      .set('usuarioAdmin', usuarioAdmin)
      .set('contrasena_admin', contrasena_admin);

    return this.httpClient.post(this.bdURLS, null, { params }); // null como body, params como query
  

  
}
obtenerTodosVehiculos(): Observable<Vehiculo[]> {
  return this.httpClient.get<Vehiculo[]>('http://localhost:8080/admin/todos-vehiculos');
}

obtenerVehiculosPendientes(): Observable<Vehiculo[]> {
    return this.httpClient.get<Vehiculo[]>(`${this.bdURLP}`);
  }
  obtenerVehiculosDisponibles(): Observable<Vehiculo[]> {
  return this.httpClient.get<Vehiculo[]>(`${this.bdURLR}`);
}

  entregarVehiculo(placa: string): Observable<string> {
    const params = new HttpParams().set('placa', placa);
    return this.httpClient.post(`${this.bdURLE}`, null, { params, responseType: 'text' });
  }

   //BY JOHN DEIVID: CALCULAR COSTO EXTRA Y ACTUALIZAR ESTADO DEL VEHICULO A DISPONIBLE
costoExtra(idAlquiler: number, fechaEntregaReal: string, idAdmin: number): Observable<any> {
  const body = new URLSearchParams();
  body.set('idAlquiler', idAlquiler.toString());
  body.set('fechaEntregaReal', fechaEntregaReal);
  body.set('idAdmin', idAdmin.toString());

  return this.httpClient.post<any>(this.bdURLD, body.toString(), {
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    }
  });
}
}
