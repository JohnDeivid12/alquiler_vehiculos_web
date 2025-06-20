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
  
  registrarUsuario() {
  const hoy = new Date();

  const fechaExp = new Date(this.usuario.fecha_expedicion_licencia);
  const vigencia = new Date(this.usuario.vigencia_licencia);

  // Validación: no permitir fechas futuras
  if (fechaExp > hoy) {
    alert("La fecha de expedición de la licencia no puede ser mayor a la fecha actual.");
    return;
  }
  if (vigencia < hoy) {
    alert("La vigencia de la licencia no puede ser menor a la fecha actual.");
    return;
  }

  // Si todo está bien, continúa con el registro
  this.usuarioServicio.registrar(this.usuario).subscribe(dato => {
    console.log(dato);
    if (dato != null) {
      alert("Estás registrado");
      this.usuario = new Usuario();  // reinicia el formulario
    } else {
      alert("Registro no guardado");
    }
  }, error => {
    console.error(error);
    alert("Error al registrar usuario");
  });
}


}

