import { Routes } from '@angular/router';
import { RegistroUsuarioComponent } from './registro-usuario/registro-usuario.component';
import { IniciarUsuarioComponent } from './iniciar-usuario/iniciar-usuario.component';
import { VehiculosComponent } from './vehiculos/vehiculos.component';
import { IniciarAdminComponent } from './iniciar-admin/iniciar-admin.component';
import path from 'path';
import { AdminComponent } from './admin/admin.component';

export const routes: Routes = [
    { path: 'registro-usuario', component: RegistroUsuarioComponent},
    { path: 'iniciar-sesion', component: IniciarUsuarioComponent },
    { path: 'vehiculos', component: VehiculosComponent },
    { path: '', redirectTo: 'iniciar-sesion', pathMatch: 'full' },
    { path: 'iniciar-admin', component: IniciarAdminComponent },
    { path: 'admin', component: AdminComponent}

];
