import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Usuario } from '../entities/usuario';
import { UsuarioService } from '../servicio/usuario.service';

@Component({
  selector: 'app-registro-usuario',
  standalone: true,
  imports: [CommonModule,FormsModule],
  templateUrl: './registro-usuario.component.html',
  styleUrl: './registro-usuario.component.css'
})
export class RegistroUsuarioComponent implements OnInit {
  ngOnInit(): void {
  }

  //Conexion con Usuario entidad
  usuario: Usuario = new Usuario;

  //Servicio
  constructor(private usuarioServicio: UsuarioService){

  }
  
  registrarUsuario(){
    this.usuarioServicio.registrar(this.usuario).subscribe(dato => {
      console.log(dato);
      if (dato != null) {
        alert("Estas Registrado");
        this.ngOnInit()

      } else {
        alert("Registro no guardado");
      }
    });

  }

}