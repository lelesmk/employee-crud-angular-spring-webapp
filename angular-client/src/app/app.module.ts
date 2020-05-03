/**
 * APP MODULE
 * ==========
 * Defines the root module, named AppModule, that tells Angular
 * how to assemble the application. Initially declares only the
 * AppComponent. As you add more components to the app, they must
 * be declared here.
 *
*/

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CreateEmployeeComponent } from './modules/create-employee/create-employee.component';
import { EmployeeDetailsComponent } from './modules/employee-details/employee-details.component';
import { EmployeeListComponent } from './modules/employee-list/employee-list.component';
import { UpdateEmployeeComponent } from './modules/update-employee/update-employee.component';
import { EmployeeService } from './shared/services/employee.service';

@NgModule({
  declarations: [
    AppComponent,
    CreateEmployeeComponent,
    EmployeeDetailsComponent,
    EmployeeListComponent,
    UpdateEmployeeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [EmployeeService],
  bootstrap: [AppComponent]
})
export class AppModule { }
