/**
 * APP ROUTING MODULE
 * ==================
 * Routing for the Angular app is configured as an array of Routes.
 * Each component is mapped to a path so the Angular Router knows which
 * component to display based on the URL in the browser address bar.
 *
*/

import { EmployeeDetailsComponent } from './modules/employee-details/employee-details.component';
import { CreateEmployeeComponent } from './modules/create-employee/create-employee.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EmployeeListComponent } from './modules/employee-list/employee-list.component';
import { UpdateEmployeeComponent } from './modules/update-employee/update-employee.component';

const routes: Routes = [
  { path: '', redirectTo: 'employees', pathMatch: 'full' },
  { path: 'employees', component: EmployeeListComponent },
  { path: 'add', component: CreateEmployeeComponent },
  { path: 'update/:id', component: UpdateEmployeeComponent },
  { path: 'details/:id', component: EmployeeDetailsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
