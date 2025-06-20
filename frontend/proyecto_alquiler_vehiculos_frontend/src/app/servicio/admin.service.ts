import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Admin } from '../entities/admin';
import { Observable } from 'rxjs';
import { Vehiculo } from '../entities/vehiculo';

@Injectable({
  providedIn: 'root'
})

export class AdminService { 
  private bdURLS = "http://localhost:8080/admin/iniciarSesion";
  private bdURLP = "http://localhost:8080/admin/vehiculosPendientes";
  private bdURLE = "http://localhost:8080/admin/entregarVehiculo";
  constructor(private httpClient: HttpClient) { }

  iniciarSesion(usuarioAdmin:string, contrasena_admin:string ): Observable<any> {
    const params = new HttpParams()
      .set('usuarioAdmin', usuarioAdmin)
      .set('contrasena_admin', contrasena_admin);

    return this.httpClient.post(this.bdURLS, null, { params }); // null como body, params como query
  

  /*iniciarSesion(usuarioAdmin: string, contrasena_admin: string): Observable<Admin[]> {
  return this.httpClient.post<Admin[]>(`${this.bdURLS}/iniciarSesion`, {
    usuarioAdmin,
    contrasena_admin
  });
}*/
  }

   obtenerVehiculosPendientes(): Observable<Vehiculo[]> {
    return this.httpClient.get<Vehiculo[]>(`${this.bdURLP}`);
  }

  entregarVehiculo(placa: string): Observable<string> {
    const params = new HttpParams().set('placa', placa);
    return this.httpClient.post(`${this.bdURLE}`, null, { params, responseType: 'text' });
  }
}

