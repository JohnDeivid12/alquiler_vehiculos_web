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

  alquilerCancela = {
  idAlquiler: 0
};
  correoCancela = ''; // Aquí irá el correo del usuario
  alquileresUsuario: any[] = [];
  correo: string = '';


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

//By JOHN DEIVID
verMisAlquileres() {
  if (!this.correo) {
    alert("Debes ingresar tu correo.");
    return;
  }

  this.usuarioServicio.verMisAlquileres(this.correo).subscribe({
    next: (data) => {
      this.alquileresUsuario = data;
    },
    error: (err) => {
      console.error(err);
      alert("No se pudieron cargar los alquileres.");
    }
  });
}

cancelarAlquiler(idAlquiler: number) {
  if (!this.correo) {
    alert("Correo no válido.");
    return;
  }

  this.usuarioServicio.cancelarAlquilerVehiculo(idAlquiler, this.correo).subscribe({
    next: (mensaje) => {
      alert(mensaje);
      this.verMisAlquileres(); // refrescar la tabla
    },
    error: (err) => {
      console.error(err);
      alert(err.error || "No se pudo cancelar el alquiler.");
    }
  });
}
}
