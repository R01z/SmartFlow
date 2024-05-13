import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Team } from '../models/team.model';
import { ActivatedRoute } from '@angular/router';
import { TeamsService } from '../teams.service';
import { AddInformationComponent } from '../add-information/add-information.component';
import { MatDialog } from '@angular/material/dialog';
import { Information } from '../models/information.model';
import { InformationFilter } from '../models/information-filter.model';
import { HttpClient } from '@angular/common/http';
import { ChangeEvent } from '@ckeditor/ckeditor5-angular/ckeditor.component';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import { AddMuralMessageComponent } from '../add-mural-message/add-mural-message.component';

@Component({
  selector: 'app-team-details',
  templateUrl: './team-details.component.html',
  styleUrl: './team-details.component.css',
  encapsulation: ViewEncapsulation.None
})
export class TeamDetailsComponent implements OnInit {
  team: Team;
  teamInformations: Information[];
  dialogRef: any;
  public Editor = ClassicEditor;
  initialMessage: string  = '';
  editorData: string = '';
  isReadOnly: boolean = true;

  constructor(private route: ActivatedRoute, private teamsService: TeamsService, private dialog: MatDialog, private http: HttpClient) {
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
          this.loadTeamInformation(teamId);
          this.loadTeamWikiText(teamId);
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

  loadTeamInformation(teamId: number): void {
    const filter: InformationFilter = {
        teamId: [teamId] // Convertendo o teamId em uma lista
    };
    this.teamsService.getTeamInformation(filter).subscribe(informations => {
        this.teamInformations = informations;
    });
} 

  loadTeamWikiText(teamId: number): void{
    this.http.get<any>(`http://localhost:8090/api/v1/wiki/getByTeamId/${teamId}`)
      .subscribe(
        response => {
          if (response && response.text) {
            this.initialMessage = response.text;
          } else {
            console.error('Não foi possível encontrar o texto da Wiki para o time com ID:', teamId);
          }
        },
        error => {
          console.error('Erro ao carregar o texto da Wiki:', error);
        }
      );
  }

  getDownloadUrl(informationId: number): string {
    return `http://localhost:8090/api/v1/file/download/${informationId}`;
  }
  
  formatLink(link: string): string {
    if (!/^https?:\/\//i.test(link)) {
      return 'http://' + link;
    }
    return link;
  }

  deleteInfo(informationId: number): void {
    this.http.delete(`http://localhost:8090/api/v1/information/delete/${informationId}`, { responseType: 'text' }).subscribe((response: string) => {
      console.log('Informação deletada com sucesso:', response);
      // Atualiza a página
      window.location.reload();
    }, (error: any) => {
      console.error('Erro ao deletar informação:', error);
    });
  }

  public onChange({ editor }: ChangeEvent): void {
    this.editorData = editor.getData();
  }
  
  public saveWikiText(): void {
    const text = this.editorData; // supondo que você tenha a propriedade 'editorData' que contém o texto atual do editor
    const teamId = this.team.teamId; // supondo que você tenha a propriedade 'team' carregada
    this.saveOrUpdateWikiText(teamId, text);
    this.isReadOnly = true; // após salvar, o usuário não está mais editando
  }

  private saveOrUpdateWikiText(teamId: number, text: string): void {
    const request = { teamId, text };
    this.http.post<any>('http://localhost:8090/api/v1/wiki/saveOrUpdate', request)
      .subscribe(
        response => {
          console.log('Texto da Wiki salvo com sucesso:', response);
        },
        error => {
          console.error('Erro ao salvar o texto da Wiki:', error);
        }
      );
  }

  toggleReadOnly(): void {
    this.isReadOnly = !this.isReadOnly;
  }

  openAddMuralModal(): void {
    const dialogRef = this.dialog.open(AddMuralMessageComponent, {
      width: '600px', // Defina a largura da janela modal conforme necessário
      data: { teamId: this.team.teamId } // Passe o ID do time para o modal
    });
    dialogRef.afterClosed().subscribe(result => {
      window.location.reload();
    });
  }
}
