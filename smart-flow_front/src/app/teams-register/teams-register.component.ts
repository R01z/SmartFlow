import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-teams-register',
  templateUrl: './teams-register.component.html',
  styleUrls: ['./teams-register.component.css']
})
export class TeamsRegisterComponent {
  teamData = {
    name: '',
    description: '',
    membersEmails: [] as string[]
  };
  memberEmails: string = '';

  constructor(private http: HttpClient) {}

  submitForm(): void {
    // Separar os emails por vírgula
    this.teamData.membersEmails = this.memberEmails.split(',').map((email: string) => email.trim());
    
    console.log(this.teamData);
    // Envie os dados do formulário para o servidor usando HttpClient
    this.http.post('http://localhost:8090/api/v1/teams/save', this.teamData).subscribe(response => {
      console.log(response);
      // Aqui você pode lidar com a resposta do servidor, por exemplo, exibindo uma mensagem de sucesso ou redirecionando para outra página
    });
  }
}
