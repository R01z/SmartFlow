import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  name: string ="";
  email: string ="";
  password: string ="";


  constructor(private http: HttpClient )
  {

  }
  save()
  {
  
    let bodyData = {
      "name" : this.name,
      "email" : this.email,
      "password" : this.password
    };
    this.http.post("http://localhost:8090/api/v1/users/save",bodyData,{responseType: 'text'}).subscribe((resultData: any)=>
    {
        console.log(resultData);
        alert("Employee Registered Successfully");

    });
  }

}