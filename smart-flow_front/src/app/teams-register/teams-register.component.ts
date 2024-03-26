import { Component, Inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

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
  title: string = 'Adicionar Novo Time'; // Título padrão
  isEditMode: boolean = false; // Define o modo de edição

  constructor(private http: HttpClient, private route: ActivatedRoute, 
    public dialogRef: MatDialogRef<TeamsRegisterComponent>, @Inject(MAT_DIALOG_DATA) public data: any) {}

  ngOnInit(): void {
   if (this.data && this.data.teamId) {
      this.getTeam(this.data.teamId);
      this.title = 'Editar Time'; // Altera o título para "Editar Usuário"
      this.isEditMode = true;
    }
  }
  
  getTeam(teamId: string): void {
    this.http.get<any>('http://localhost:8090/api/v1/teams/getTeamById/' + teamId).subscribe(team => {
      this.teamData = team;
    });
  }

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

  deleteTeam(): void {
    const teamId = this.data.teamId;
    this.http.delete(`http://localhost:8090/api/v1/teams/deleteTeam/${teamId}`).subscribe(response => {
      console.log('Time deletado com sucesso:', response);
      this.dialogRef.close(); // Fecha a janela modal após a exclusão
    }, error => {
      console.error('Erro ao deletar o usuário:', error);
    });
  }
}
