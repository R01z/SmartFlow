import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Information } from '../models/information.model';
import { environment } from '../../environments/environment';

@Component({
  selector: 'app-information-details',
  templateUrl: './information-details.component.html',
  styleUrls: ['./information-details.component.css']
})
export class InformationDetailsComponent {
  private apiUrl = environment.apiUrl;

  constructor(@Inject(MAT_DIALOG_DATA) public data: Information) {}

  getDownloadUrl(informationId: number): string {
    return `${this.apiUrl}/api/v1/file/download/${informationId}`;
  }

  formatLink(link: string): string {
    if (!/^https?:\/\//i.test(link)) {
      return 'http://' + link;
    }
    return link;
  }

}
