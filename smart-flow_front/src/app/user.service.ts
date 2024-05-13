import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getLoggedUser(): Observable<any> {
    return this.http.get('http://localhost:8090/api/v1/users/loggedUser');
  }

  getAllUsers(): Observable<any[]> {
    return this.http.get<any[]>('http://localhost:8090/api/v1/users/getAllUsers');
  }

  getUserTeams(userId: number): Observable<any[]> {
    return this.http.get<any[]>(`http://localhost:8090/api/v1/users/${userId}/teams`);
  }

  isUserAdmin(userId: number): Observable<boolean> {
    return this.http.get<boolean>(`http://localhost:8090/api/v1/users/${userId}/isAdmin`);
  }
}
