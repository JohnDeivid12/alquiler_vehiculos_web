import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Usuario } from '../entities/usuario';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private bdURL= "http://localhost:8080/usuario/listarUsuarios";

  constructor(private httpClient: HttpClient) { }

  //Ver los usuarios ingresados
  obtenerListaUsuarios(): Observable<Usuario[]>{
  return this.httpClient.get<Usuario[]>(`${this.bdURL}`);
  }
}
