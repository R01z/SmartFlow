import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { InformationFilter } from './models/information-filter.model';
import { Information } from './models/information.model';

@Injectable({
  providedIn: 'root'
})
export class InformationService {

  constructor(private http: HttpClient) { }

  searchInformations(filters: InformationFilter): Observable<Information[]> {
    return this.http.post<Information[]>('http://localhost:8090/api/v1/information/getInformations', filters);
  }

}
