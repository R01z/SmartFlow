import { Component, OnInit } from '@angular/core';
import { TeamsRegisterComponent } from '../teams-register/teams-register.component';
import { MatDialog } from '@angular/material/dialog';
import { TeamsService } from '../teams.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-teams',
  templateUrl: './teams.component.html',
  styleUrl: './teams.component.css'
})
export class TeamsComponent implements OnInit{
  teams: any[] = [];

  constructor(private dialog: MatDialog, private teamsService: TeamsService, private router: Router){}

  ngOnInit(): void {
    this.loadTeams(); // Carrega os usuários ao inicializar o componente
  }

  openUserRegisterModal(): void {
    this.dialog.open(TeamsRegisterComponent, {
      width: '600px' // Defina a largura da janela modal conforme necessário
    });
  }

  loadTeams(): void {
    this.teamsService.getAllTeams().subscribe(
      (data: any) => {
        this.teams = data; // Atribui os usuários obtidos do serviço à variável 'users'
      },
      error => {
        console.log('Error fetching users:', error);
      }
    );
  }
}
