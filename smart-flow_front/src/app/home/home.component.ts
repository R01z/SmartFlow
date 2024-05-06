import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { UserService } from '../user.service';
import { InformationFilter } from '../models/information-filter.model';
import { Information } from '../models/information.model';
import { InformationService } from '../information.service';
import { Team } from '../models/team.model';
import { InformationDetailsComponent } from '../information-details/information-details.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})

export class HomeComponent implements OnInit {
  userName:string = '';
  searchFilters: InformationFilter = {};
  showResults: boolean = false;
  informationResults: Information[] = [];
  userTeams: Team[] = [];
  selectedTeams: number[] = [];
  tags: string = '';

  constructor(private dialog: MatDialog, private userService: UserService, private informationService: InformationService) { }

  ngOnInit(): void {
    this.userService.getLoggedUser().subscribe((user: any) => {
      this.userName = user.name;
      this.loadUserTeams(user.userId);
    });
  }

  loadUserTeams(userId: number): void {
    this.userService.getUserTeams(userId).subscribe((teams: Team[]) => {
      this.userTeams = teams;
    });
  }

  processTags(): void {
    // Verifica se há tags inseridas no campo de pesquisa
    if (this.tags) {
      // Separar as tags por vírgula e remover espaços em branco
      this.searchFilters.tags = this.tags.split(',').map(tag => tag.trim());
    }
  }

  search(): void {
    this.processTags();
    this.searchFilters.teamId = this.selectedTeams.length > 0 ? this.selectedTeams : undefined;

    if (this.searchFilters.startDate) {
      this.searchFilters.startDate = new Date(this.searchFilters.startDate);
    }
    if (this.searchFilters.endDate) {
      this.searchFilters.endDate = new Date(this.searchFilters.endDate);
    }

    // Realizar busca com base nos filtros preenchidos
    this.informationService.searchInformations(this.searchFilters).subscribe((results: Information[]) => {
      this.informationResults = results;
      this.showResults = true;
    });
  }

  getDownloadUrl(informationId: number): string {
    return `http://localhost:8090/api/v1/file/download/${informationId}`;
  }

  openDetailsModal(info: Information): void {
    const dialogRef = this.dialog.open(InformationDetailsComponent, {
      width: '600px',
      data: info
    });
  
    dialogRef.afterClosed().subscribe(result => {
      console.log('O modal foi fechado');
    });
  }

  formatLink(link: string): string {
    if (!/^https?:\/\//i.test(link)) {
      return 'http://' + link;
    }
    return link;
  }
  
}
