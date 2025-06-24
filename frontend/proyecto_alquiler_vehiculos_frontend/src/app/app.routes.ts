import { Routes } from '@angular/router';
import { RegistroUsuarioComponent } from './registro-usuario/registro-usuario.component';
import { IniciarUsuarioComponent } from './iniciar-usuario/iniciar-usuario.component';
import { VehiculoComponent } from './vehiculo/vehiculo.component';
import { IniciarAdminComponent } from './iniciar-admin/iniciar-admin.component';
import { AdminComponent } from './admin/admin.component';
import { UsuarioComponent } from './usuario/usuario.component';
import { HomeComponent } from './home/home.component';

export const routes: Routes = [
    {path: 'registro-usuario', component: RegistroUsuarioComponent},
    { path: 'iniciar-sesion', component: IniciarUsuarioComponent },
    { path: 'vehiculo', component: VehiculoComponent },
    { path: 'iniciar-admin', component: IniciarAdminComponent },
    { path: 'alquiler-pendiente', component: AdminComponent },
    { path: 'usuario', component: UsuarioComponent },
    { path: '', component: HomeComponent },
];
