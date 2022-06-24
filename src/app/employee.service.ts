import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Employee } from './employee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private baseURL = "http://localhost:8080/api/v1/employees";
  //private baseURL = "./api/v1/employees";

  constructor(private httpClient: HttpClient ) { }

  getEmployeesList(): Observable<Employee[]> {
    
   return this.httpClient.get<Employee[]>(this.baseURL);
 //  return this.httpClient.get<Employee[]>("http://localhost:8080/api/v1/employees");
  }

  createEmployee(employee:Employee): Observable<Object>
  {
    return this.httpClient.post("http://localhost:8080/api/v1/employees",employee);
  }

  getEmployeeById(id:number): Observable<Employee>{
  // return this.httpClient.get<Employee>('http://localhost:8080/api/v1/employees/${id}');
   return this.httpClient.get<Employee>(this.baseURL+'/'+id);
  }

  updateEmployee(id:number, employee:Employee): Observable<object>{
    return this.httpClient.put('http://localhost:8080/api/v1/employees/'+id,employee);

  }

  deleteEmployee(id:number): Observable<object>{
    return this.httpClient.delete('http://localhost:8080/api/v1/employees/'+id);

  }

}
