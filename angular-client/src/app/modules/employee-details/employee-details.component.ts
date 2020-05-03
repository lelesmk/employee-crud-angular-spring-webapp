/**
 * EMPLOYEE DETAILS COMPONENT
 * ==========================
 * The EmployeeDetailsComponent component used to display a particular employee detail.
 *
*/

import { Employee } from '../../shared/models/employee';
import { Component, OnInit, Input } from '@angular/core';
import { EmployeeService } from '../../shared/services/employee.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-employee-details',
  templateUrl: './employee-details.component.html',
  styleUrls: ['./employee-details.component.css']
})
export class EmployeeDetailsComponent implements OnInit {

  id: number;
  employee: Employee;

  constructor(private route: ActivatedRoute,private router: Router,
    private employeeService: EmployeeService) { }

  ngOnInit() {
    this.employee = new Employee();

    this.id = this.route.snapshot.params['id'];

    this.employeeService.getEmployee(this.id)
      .subscribe(data => {
        console.log(data)
        this.employee = data;
      }, error => console.log(error));
  }

  list(){
    this.router.navigate(['employees']);
  }

  updateEmployee(id: number){
    this.router.navigate(['update', id]);
  }
}
