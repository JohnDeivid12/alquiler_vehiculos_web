import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { UsuarioService } from '../servicio/usuario.service';
import { Usuario } from '../entities/usuario';
import { Vehiculo } from '../entities/vehiculo';
import { Alquiler } from '../entities/alquiler';

@Component({
  selector: 'app-usuario',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './usuario.component.html',
  styleUrl: './usuario.component.css'
})
export class UsuarioComponent implements OnInit {
   ngOnInit(): void {    
  }

  vehiculo: Vehiculo = new Vehiculo;

  usuarioCancela: Usuario = new Usuario;

  alquilerCancela: Alquiler =new Alquiler;

  constructor(private usuarioServicio: UsuarioService) {}
  
  buscarTipoDisponible(){
    this.usuarioServicio.buscarVehiculo(this.vehiculo.tipo).subscribe(dato => {
      console.log(dato);
      if (dato != null) {
        alert("Vehiculos encontrados");
        this.ngOnInit() 

      } else {
        alert("Vehiculos no encontrados");
      }
    });
}

cancelarAlquiler() {
  if (this.alquilerCancela.idAlquiler > 0 && this.usuarioCancela.idUsuario > 0) {
    this.usuarioServicio.cancelarAlquilerVehiculo(this.alquilerCancela.idAlquiler, this.usuarioCancela.idUsuario).subscribe({
      next: (mensaje) => {
        alert(mensaje);
      },
      error: (err) => {
        console.error(err);
        alert(err.error || 'Error al cancelar el alquiler.');
      }
    });
  } else {
    alert("Debes ingresar ID de alquiler e ID de usuario.");
  }
}


}
