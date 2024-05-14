import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { UserService } from '../user.service';
import { InformationFilter } from '../models/information-filter.model';
import { Information } from '../models/information.model';
import { InformationService } from '../information.service';
import { Team } from '../models/team.model';
import { InformationDetailsComponent } from '../information-details/information-details.component';
import { TypeInformation } from '../models/type-information.model';

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
  sortColumn: string = ''; // Coluna atualmente selecionada para ordenação
  sortDirection: string = 'asc'; // Direção da ordenação: 'asc' ou 'desc'
  availableTypes: TypeInformation[] = [];
  selectedTypes: number[] = [];

  constructor(private dialog: MatDialog, private userService: UserService, private informationService: InformationService) { }

  ngOnInit(): void {
    this.userService.getLoggedUser().subscribe((user: any) => {
      this.userName = user.name;
      this.loadUserTeams(user.userId);
      this.loadAvailableTypes();
    });
  }

  loadUserTeams(userId: number): void {
    this.userService.getUserTeams(userId).subscribe((teams: Team[]) => {
      this.userTeams = teams;
    });
  }

  loadAvailableTypes(): void {
    this.informationService.getAllTypes().subscribe((types: TypeInformation[]) => {
      this.availableTypes = types;
      console.log("Tipos : ", types);
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

    this.searchFilters.typeId = this.selectedTypes.length > 0 ? this.selectedTypes : undefined;
    console.log("Tipos de informações selecionados: ", this.selectedTypes);

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

  sort(column: keyof Information): void {
    if (this.sortColumn === column) {
      this.sortDirection = this.sortDirection === 'asc' ? 'desc' : 'asc';
    } else {
      this.sortColumn = column;
      this.sortDirection = 'asc';
    }
  
    // Lógica de ordenação dos resultados com base na coluna e na direção da ordenação
    this.informationResults.sort((a, b) => {
      const valueA = a[column];
      const valueB = b[column];
  
      // Verificar se as propriedades existem antes de compará-las
      if (valueA !== undefined && valueB !== undefined) {
        if (this.sortDirection === 'asc') {
          return valueA > valueB ? 1 : -1;
        } else {
          return valueA < valueB ? 1 : -1;
        }
      } else {
        if (valueA === undefined && valueB !== undefined) {
          return -1;
        } else if (valueA !== undefined && valueB === undefined) {
          return 1;
        } else {
          return 0;
        }
      }
    });
  }
  
}
