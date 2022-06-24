import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {

  employees?: Employee[];

  constructor(private employeeService: EmployeeService,private router:Router) { }

  ngOnInit(): void {
    console.log('hello');
   this.getEmployees();
  /* this.employees = [{"id":1,"firstName":"admin","lastName":"admin","emailId":"admin@gmail.com"},
   {"id":2,"firstName":"test","lastName":"test","emailId":"test@gmail.com"}];*/
  }

  private getEmployees(){
   // console.log('hello');
  // this.employeeService.getEmployeesList().forEach
    this.employeeService.getEmployeesList().subscribe(data=> {
      this.employees = data;
    });
  }

  updateEmployee(id:number){
    this.router.navigate(['updated-employee',id]);


  }

  deleteEmployee(id:number){
    this.employeeService.deleteEmployee(id).subscribe(data => {
      this.getEmployees();
    })

  }

  EmployeeDetails(id:number){
    this.router.navigate(['employee-details',id]);
  }
  
    
 

}
