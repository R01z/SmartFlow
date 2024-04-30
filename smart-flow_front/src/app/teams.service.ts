import { HttpClient, HttpParams } from '@angular/common/http';
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
    let params = new HttpParams();
    if (filter.name) params = params.set('name', filter.name);
    if (filter.description) params = params.set('description', filter.description);
    if (filter.startDate) params = params.set('startDate', filter.startDate.toISOString());
    if (filter.endDate) params = params.set('endDate', filter.endDate.toISOString());
    if (filter.teamId) params = params.set('teamId', filter.teamId.toString());
    if (filter.tags) params = params.set('tags', filter.tags.join(','));

    return this.http.get<Information[]>('http://localhost:8090/api/v1/information/getInformations', { params: params });
  }
}
