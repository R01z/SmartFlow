import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-user-register',
  templateUrl: './user-register.component.html',
  styleUrls: ['./user-register.component.css']
})
export class UserRegisterComponent implements OnInit {
  userData = {
    name: '',
    email: '',
    password: '',
    roles: []
  };
  roles: any[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.getAllRoles();
  }

  getAllRoles(): void {
    this.http.get<any[]>('http://localhost:8090/api/v1/roles/getAllRoles').subscribe(data => {
      this.roles = data;
    });
  }

  submitForm(): void {
    console.log(this.userData);
    // Envie os dados do formulário para o servidor usando HttpClient
    this.http.post('http://localhost:8090/api/v1/users/save', this.userData).subscribe(response => {
      console.log(response);
      // Aqui você pode lidar com a resposta do servidor, por exemplo, exibindo uma mensagem de sucesso ou redirecionando para outra página
    });
  }
}
