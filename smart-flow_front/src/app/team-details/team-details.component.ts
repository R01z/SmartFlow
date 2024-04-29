import { Component, OnInit } from '@angular/core';
import { Team } from '../models/team.model';
import { ActivatedRoute } from '@angular/router';
import { TeamsService } from '../teams.service';
import { AddInformationComponent } from '../add-information/add-information.component';
import { MatDialog } from '@angular/material/dialog';
import { Information } from '../models/information.model';
import { InformationFilter } from '../models/information-filter.model';

@Component({
  selector: 'app-team-details',
  templateUrl: './team-details.component.html',
  styleUrl: './team-details.component.css'
})
export class TeamDetailsComponent implements OnInit {
  team: Team;
  teamInformations: Information[];

  constructor(private route: ActivatedRoute, private teamsService: TeamsService, private dialog: MatDialog) {
    this.team = {} as Team;
    this.teamInformations = [];
   }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const teamIdParam = params.get('teamId');
      if (teamIdParam !== null && teamIdParam !== undefined) {
        const teamId = +teamIdParam;
        if (!isNaN(teamId)) {
          // Carregue os detalhes do time com o novo 'teamId'
          this.loadTeamDetails(teamId);
          //this.loadTeamInformation(teamId);
        } else {
          console.error('O parâmetro "teamId" não é um número válido.');
        }
      } else {
        console.error('O parâmetro "teamId" é nulo ou indefinido.');
      }
    });
  }

  loadTeamDetails(teamId: number): void {
    this.teamsService.getTeamDetails(teamId).subscribe(team => {
      this.team = team;
    });
  }

  openAddInformationModal(): void {
    const dialogRef = this.dialog.open(AddInformationComponent, {
      width: '600px', // Defina a largura da janela modal conforme necessário
      data: { teamId: this.team.teamId } // Passe o ID do time para o modal
    });

  }

  /*loadTeamInformation(teamId: number): void {
    const filter: InformationFilter = {
      teamId: teamId
    };
    this.teamsService.getTeamInformation(filter).subscribe(informations => {
      this.teamInformations = informations;
    });
  }*/
}
