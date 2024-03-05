import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';

import { UserRegisterComponent } from '../user-register/user-register.component';

@Component({
  selector: 'app-user-control',
  templateUrl: './user-control.component.html',
  styleUrl: './user-control.component.css'
})
export class UserControlComponent {
  constructor(private dialog: MatDialog) { }

  openUserRegisterModal(): void {
    this.dialog.open(UserRegisterComponent, {
      width: '400px' // Defina a largura da janela modal conforme necessário
    });
  }
}
