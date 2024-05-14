import { Component, OnInit } from '@angular/core';
import { TeamsRegisterComponent } from '../teams-register/teams-register.component';
import { MatDialog } from '@angular/material/dialog';
import { TeamsService } from '../teams.service';
import { Router } from '@angular/router';
import { AddTeamMembersComponent } from '../add-team-members/add-team-members.component';

@Component({
  selector: 'app-teams',
  templateUrl: './teams.component.html',
  styleUrl: './teams.component.css'
})
export class TeamsComponent implements OnInit{
  teams: any[] = [];

  constructor(private dialog: MatDialog, private teamsService: TeamsService, private router: Router){}

  ngOnInit(): void {
    this.loadTeams();
  }

  openTeamRegisterModal(): void {
    const dialogRef = this.dialog.open(TeamsRegisterComponent, {
      width: '600px' // Defina a largura da janela modal conforme necessário
    });
    dialogRef.afterClosed().subscribe(result => {
      window.location.reload();
    });
  }

  loadTeams(): void {
    this.teamsService.getAllTeams().subscribe(
      (data: any) => {
        this.teams = data;
      },
      error => {
        console.log('Error fetching teams:', error);
      }
    );
  }

  editTeam(teamId: string): void {
    const dialogRef = this.dialog.open(TeamsRegisterComponent, {
      width: '600px',
      data: { teamId }
    });
    dialogRef.afterClosed().subscribe(result => {
      window.location.reload();
    });
  }

  addMembers(teamId: string): void {
    this.dialog.open(AddTeamMembersComponent, {
      width: '600px',
      data: { teamId }
    });
  }
}
