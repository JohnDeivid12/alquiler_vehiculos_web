import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { UsuarioService } from '../servicio/usuario.service';
import { Vehiculo } from '../entities/vehiculo';

@Component({
  selector: 'app-usuario',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './usuario.component.html',
  styleUrl: './usuario.component.css'
})
export class UsuarioComponent implements OnInit{
alquiler_vehiculos: any;
ngOnInit(): void {
  
}
 vehiculo: Vehiculo = new Vehiculo;

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
}
