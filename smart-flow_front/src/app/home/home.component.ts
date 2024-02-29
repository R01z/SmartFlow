import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})

export class HomeComponent implements OnInit {
  userName:string = '';

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getLoggedUser().subscribe((user: any) => {
      this.userName = user.name;
    });
  }
}
