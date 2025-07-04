import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Usuario } from '../entities/usuario';
import { Observable } from 'rxjs';
import { Vehiculo } from '../entities/vehiculo';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
  
  private bdURL = "http://localhost:8080/usuario/guardarUsuario";
  private bdURLB = "http://localhost:8080/usuario/buscarVehiculosPorTipoYEstado";
  private bdURLV = "http://localhost:8080/usuario/verAlquileres";
  private bdURLC = "http://localhost:8080/usuario/cancelarAlquiler";
  private bdURLS = "http://localhost:8080/usuario/iniciarSesion";
  
  constructor(private httpClient: HttpClient) { }

  registrar(usuario: Usuario): Observable<Object> {
    return this.httpClient.post(`${this.bdURL}`, usuario);
  }
  
  iniciarSesion(correo: string, contrasena_usuario: string): Observable<any> {
    const params = new HttpParams()
      .set('correo', correo)
      .set('contrasena_usuario', contrasena_usuario);

    return this.httpClient.post(this.bdURLS, null, { params });
  }

  buscarVehiculo(tipo: string, estado: string = 'disponible'): Observable<Vehiculo[]> {
  const params = new HttpParams()
    .set('tipo', tipo)
    .set('estado', estado);

  return this.httpClient.get<Vehiculo[]>(this.bdURLB, { params });
}

//By JOHN DEIVID
// Obtener alquileres por correo
verMisAlquileres(correo: string): Observable<any[]> {
  const params = new HttpParams().set('correo', correo);
  return this.httpClient.get<any[]>(`${this.bdURLV}`, { params });
}


// Cancelar alquiler por idAlquiler y correo
cancelarAlquilerVehiculo(idAlquiler: number, correo: string): Observable<string> {
  const params = new HttpParams()
    .set('idAlquiler', idAlquiler)
    .set('correo', correo);

  return this.httpClient.post(`${this.bdURLC}`, null, {
    params,
    responseType: 'text'
  });
}
}
