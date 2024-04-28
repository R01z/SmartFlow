import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-add-information',
  templateUrl: './add-information.component.html',
  styleUrls: ['./add-information.component.css']
})
export class AddInformationComponent {
  teamId: number;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any) {
    this.teamId = data.teamId;
  }
}
