import { HttpClient } from '@angular/common/http';
import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { environment } from '../../environments/environment';

@Component({
  selector: 'app-add-information',
  templateUrl: './add-information.component.html',
  styleUrls: ['./add-information.component.css']
})
export class AddInformationComponent {
  teamId: number;
  informationData = {
    name: '',
    description: '',
    link: '',
    file: null as File | null,
    tags: [] as string[],
    teamId: null as number | null,
    typeId: null as number | null
  };
  tags: string = '';
  title: string = 'Adicionar Nova Informação'; // Título padrão
  types: any[] = [];
  private apiUrl = environment.apiUrl;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private http: HttpClient, private route: ActivatedRoute, 
  public dialogRef: MatDialogRef<AddInformationComponent>) {
    this.teamId = data.teamId;
  }

  ngOnInit(): void {
    this.loadTypes();
  }

  loadTypes(): void {
    this.http.get<any[]>(`${this.apiUrl}/api/v1/typeinformation/getAllTypes`).subscribe(data => {
      this.types = data;
    });
  }

  submitForm(): void {
    // Separar as tags por vírgula
    this.informationData.tags = this.tags.split(',').map(tag => tag.trim());
    
    console.log(this.informationData);

    const formData = new FormData();
    formData.append('name', this.informationData.name);
    formData.append('description', this.informationData.description);
    formData.append('typeId', this.informationData.typeId?.toString() || '');
    formData.append('link', this.informationData.link);
    formData.append('teamId', this.teamId.toString());
    if (this.informationData.file) {
      formData.append('file', this.informationData.file);
    }
    this.informationData.tags.forEach((tag: string, index: number) => {
      formData.append(`tags[${index}]`, tag);
    });

    this.http.post(`${this.apiUrl}/api/v1/information/save`, formData).subscribe(response => {
      console.log(response);
      
    });
  }

  handleFileInput(event: any): void {
    if (event.target.files.length > 0) {
      const file = event.target.files[0];
      this.informationData.file = file;
    }
  }
}
