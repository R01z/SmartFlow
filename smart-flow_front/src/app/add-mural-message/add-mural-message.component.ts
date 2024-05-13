import { HttpClient } from '@angular/common/http';
import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-add-mural-message',
  templateUrl: './add-mural-message.component.html',
  styleUrl: './add-mural-message.component.css'
})
export class AddMuralMessageComponent {
  teamId: number;
  avisoData = {
    title: '',
    mensagem: ''
  };

  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private http: HttpClient, private route: ActivatedRoute, 
  public dialogRef: MatDialogRef<AddMuralMessageComponent>) {
    this.teamId = data.teamId;
  }

  submitForm(): void {
    console.log(this.avisoData);

    const formData = new FormData();
    formData.append('title', this.avisoData.title);
    formData.append('text', this.avisoData.mensagem);
    formData.append('teamId', this.teamId.toString());

    this.http.post('http://localhost:8090/api/v1/mural/save', formData).subscribe(response => {
      console.log(response);
      this.dialogRef.close();
    });
  }
}
