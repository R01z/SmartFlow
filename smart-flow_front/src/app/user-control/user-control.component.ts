import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { UserRegisterComponent } from '../user-register/user-register.component';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-control',
  templateUrl: './user-control.component.html',
  styleUrls: ['./user-control.component.css']
})
export class UserControlComponent implements OnInit {
  users: any[] = []; // Array para armazenar os usuários

  constructor(private dialog: MatDialog, private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    this.loadUsers(); // Carrega os usuários ao inicializar o componente
  }

  openUserRegisterModal(): void {
    this.dialog.open(UserRegisterComponent, {
      width: '600px' // Defina a largura da janela modal conforme necessário
    });
  }

  loadUsers(): void {
    this.userService.getAllUsers().subscribe(
      (data: any) => {
        this.users = data; // Atribui os usuários obtidos do serviço à variável 'users'
      },
      error => {
        console.log('Error fetching users:', error);
      }
    );
  }

  editUser(userId: string): void {
    this.dialog.open(UserRegisterComponent, {
      width: '600px',
      data: { userId }
    });
  }
}
