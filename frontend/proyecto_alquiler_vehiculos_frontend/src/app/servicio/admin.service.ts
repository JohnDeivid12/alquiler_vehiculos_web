import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Admin } from '../entities/admin';



@Injectable({
  providedIn: 'root'
})


export class AdminService {

  private bdURLS = "http://localhost:8080/admin/iniciarSesion";

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
}
