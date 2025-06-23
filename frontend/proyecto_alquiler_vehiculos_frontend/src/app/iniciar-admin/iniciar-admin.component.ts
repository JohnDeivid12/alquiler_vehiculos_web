import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AdminService } from '../servicio/admin.service';
import { Admin } from '../entities/admin';

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

  admin : Admin= new Admin;

  constructor(private router: Router,
    private adminService: AdminService
  ) {}

  iniciarSesion() {
    if (this.admin.usuarioAdmin && this.admin.contrasena_admin) {
      this.adminService.iniciarSesion(this.admin.usuarioAdmin, this.admin.contrasena_admin).subscribe({        next: (data) => {
          console.log('Sesión de admin iniciada correctamente:', data);
          alert('¡Inicio de sesión de admin exitoso!');
          this.router.navigate(['/admin']); // o cualquier ruta de administrador
        },
        error: (err) => {
          console.error('Error en inicio de sesión de admin:', err);
          alert('Credenciales inválidas para administrador');
        }
      });
    } else {
      alert('Por favor, complete todos los campos');
    }
  }

}
