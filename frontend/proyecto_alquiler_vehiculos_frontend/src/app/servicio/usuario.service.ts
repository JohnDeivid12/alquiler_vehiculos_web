import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Usuario } from '../entities/usuario';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
  
  private bdURL = "http://localhost:8080/usuario/guardarUsuario";
  private bdURLS = "http://localhost:8080/usuario/iniciarSesion";
  constructor(private httpClient: HttpClient) { }

  registrar(usuario: Usuario): Observable<Object> {
    return this.httpClient.post(`${this.bdURL}`, usuario);
  }

  iniciarSesion(usuario: Usuario): Observable<Object> {
    return this.httpClient.post(`${this.bdURLS}`, usuario);
  }
}