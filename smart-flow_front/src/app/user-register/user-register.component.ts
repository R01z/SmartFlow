import { Component, Inject, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';

import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';


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
  title: string = 'Adicionar Novo Usuário'; // Título padrão
  submitButtonLabel: string = 'Cadastrar'; // Rótulo do botão padrão

  constructor(private http: HttpClient, private route: ActivatedRoute, 
    public dialogRef: MatDialogRef<UserRegisterComponent>, @Inject(MAT_DIALOG_DATA) public data: any) {}

  ngOnInit(): void {
    this.getAllRoles();

    if (this.data && this.data.userId) {
      this.getUser(this.data.userId);
      this.title = 'Editar Usuário'; // Altera o título para "Editar Usuário"
      this.submitButtonLabel = 'Salvar Alterações'; // Altera o rótulo do botão para "Salvar Alterações"
    }
  }

  getUser(userId: string): void {
    this.http.get<any>('http://localhost:8090/api/v1/users/getUserById/' + userId).subscribe(user => {
      this.userData = user;
    });
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
