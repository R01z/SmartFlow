import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) { }

  getLoggedUser(): Observable<any> {
    return this.http.get(`${this.apiUrl}/api/v1/users/loggedUser`);
  }

  getAllUsers(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/api/v1/users/getAllUsers`);
  }

  getUserTeams(userId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/api/v1/users/${userId}/teams`);
  }

  isUserAdmin(userId: number): Observable<boolean> {
    return this.http.get<boolean>(`${this.apiUrl}/api/v1/users/${userId}/isAdmin`);
  }
}
