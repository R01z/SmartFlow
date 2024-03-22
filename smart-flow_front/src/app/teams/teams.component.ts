import { Component } from '@angular/core';
import { TeamsRegisterComponent } from '../teams-register/teams-register.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-teams',
  templateUrl: './teams.component.html',
  styleUrl: './teams.component.css'
})
export class TeamsComponent {

  constructor(private dialog: MatDialog){}

  openUserRegisterModal(): void {
    this.dialog.open(TeamsRegisterComponent, {
      width: '600px' // Defina a largura da janela modal conforme necessário
    });
  }
}
