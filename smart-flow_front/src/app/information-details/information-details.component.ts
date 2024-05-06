import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Information } from '../models/information.model';

@Component({
  selector: 'app-information-details',
  templateUrl: './information-details.component.html',
  styleUrls: ['./information-details.component.css']
})
export class InformationDetailsComponent {

  constructor(@Inject(MAT_DIALOG_DATA) public data: Information) {}

}
