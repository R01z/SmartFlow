import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Team } from './models/team.model';
import { Information } from './models/information.model';
import { InformationFilter } from './models/information-filter.model';

@Injectable({
  providedIn: 'root'
})
export class TeamsService {

  constructor(private http: HttpClient) { }

  getAllTeams(): Observable<any[]> {
    return this.http.get<any[]>('http://localhost:8090/api/v1/teams/getAllTeams');
  }

  getTeamDetails(teamId: number): Observable<Team> {
    return this.http.get<Team>(`http://localhost:8090/api/v1/teams/getTeamById/${teamId}`);
  }

  getTeamInformation(filter: InformationFilter): Observable<Information[]> {
    return this.http.post<Information[]>('http://localhost:8090/api/v1/information/getInformations', filter);
  }
}
