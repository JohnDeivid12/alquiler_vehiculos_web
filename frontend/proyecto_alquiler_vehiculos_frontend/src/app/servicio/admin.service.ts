import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Admin {
  usuarioAdmin: string;
  contrasena_admin: string;
}

@Injectable({
  providedIn: 'root'
})


export class AdminService {

  private bdURLS = "http://localhost:8080/usuario/iniciarSesionAdmin";

  constructor(private httpClient: HttpClient) { }

  iniciarSesion(admin: Admin): Observable<Object> {
      return this.httpClient.post(`${this.bdURLS}`, admin);
    }
}
