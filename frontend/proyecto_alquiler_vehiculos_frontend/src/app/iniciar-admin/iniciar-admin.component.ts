import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-iniciar-admin',
  standalone: true,
  imports: [CommonModule,FormsModule],
  templateUrl: './iniciar-admin.component.html',
  styleUrl: './iniciar-admin.component.css'
})
export class IniciarAdminComponent implements OnInit{
  ngOnInit(): void {
  }

  admin = {
    usuarioadmin: '',
    contrasena_admin: ''
  };

  constructor(private router: Router) {}

  iniciarSesionAdmin() {
    if (this.admin.usuarioadmin && this.admin.contrasena_admin) {
      console.log('Iniciando sesión como admin con:', this.admin);
      // Aquí puedes conectar con un servicio que valide al admin
      alert('¡Inicio de sesión de admin exitoso!');
      this.router.navigate(['/vehiculos']);

      
    } else {
      console.log('Por favor, complete todos los campos');
      alert('Por favor, complete todos los campos');
    }
  }
  

}
