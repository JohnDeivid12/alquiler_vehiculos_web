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
  private bdURLS = "http://localhost:8080/usuario/iniciarSesion";
   private bdURLB = "http://localhost:8080/usuario/buscarVehiculosPorTipoYEstado";
  constructor(private httpClient: HttpClient) { };
  

  registrar(usuario: Usuario): Observable<Object> {
    return this.httpClient.post(`${this.bdURL}`, usuario);
  }

  iniciarSesion(correo: string, contrasena_usuario: string): Observable<any> {
    const params = new HttpParams()
      .set('correo', correo)
      .set('contrasena_usuario', contrasena_usuario);

    return this.httpClient.post(this.bdURLS, null, { params });
  }

  buscarVehiculo(tipo: string, estado: string = 'Disponible'): Observable<Vehiculo[]> {
  const params = new HttpParams()
    .set('tipo', tipo)
    .set('estado', estado);

  return this.httpClient.get<Vehiculo[]>(this.bdURLB, { params });
}
}
