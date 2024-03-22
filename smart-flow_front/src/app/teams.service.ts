import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TeamsService {

  constructor(private http: HttpClient) { }

  getAllTeams(): Observable<any[]> {
    return this.http.get<any[]>('http://localhost:8090/api/v1/teams/getAllTeams');
  }
}
