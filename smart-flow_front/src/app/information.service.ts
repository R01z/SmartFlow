import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { InformationFilter } from './models/information-filter.model';
import { Information } from './models/information.model';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class InformationService {
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) { }

  searchInformations(filters: InformationFilter): Observable<Information[]> {
    // Criar parâmetros de consulta a partir dos filtros
    let params = new HttpParams();
    if (filters.name) {
      params = params.set('name', filters.name);
    }
    if (filters.description) {
      params = params.set('description', filters.description);
    }
    if (filters.startDate) {
      params = params.set('startDate', filters.startDate.toISOString());
    }
    if (filters.endDate) {
      params = params.set('endDate', filters.endDate.toISOString());
    }
    if (filters.teamId && filters.teamId.length > 0) {
      params = params.set('teamId', filters.teamId.join(','));
    }
    if (filters.tags && filters.tags.length > 0) {
      params = params.set('tags', filters.tags.join(','));
    }
    if (filters.typeId && filters.typeId.length > 0) {
      params = params.set('typeId', filters.typeId.join(','));
    }

    // Fazer a solicitação GET com os parâmetros de consulta
    return this.http.get<Information[]>(`${this.apiUrl}/api/v1/information/getInformations`, { params: params });
  }

  getAllTypes(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/api/v1/typeinformation/getAllTypes`);
  }

}
