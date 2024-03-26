import { HttpClient } from '@angular/common/http';
import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { TeamsRegisterComponent } from '../teams-register/teams-register.component';

@Component({
  selector: 'app-add-team-members',
  templateUrl: './add-team-members.component.html',
  styleUrl: './add-team-members.component.css'
})
export class AddTeamMembersComponent {
  teamId!: number;
  memberEmails: string = '';
  title: string = 'Adicionar Membro'; // Título padrão

  constructor(private http: HttpClient, private route: ActivatedRoute, 
    public dialogRef: MatDialogRef<TeamsRegisterComponent>, @Inject(MAT_DIALOG_DATA) public data: any) {}

  ngOnInit(): void {
    if (this.data && this.data.teamId) {
      this.teamId = this.data.teamId;
     }
  }

  submitForm(): void {
    // Separe os emails por vírgula
    const emails = this.memberEmails.split(',').map(email => email.trim());
    
    console.log(emails);
    // Envie os dados do formulário para o servidor usando HttpClient
    this.http.post(`http://localhost:8090/api/v1/teams/${this.teamId}/addMembersByEmail`, emails).subscribe(response => {
      console.log(response);
      // Aqui você pode lidar com a resposta do servidor, por exemplo, exibindo uma mensagem de sucesso ou fechando a janela modal
      this.dialogRef.close(true); // Fecha a janela modal após adicionar os membros com sucesso
    });
  }
}
