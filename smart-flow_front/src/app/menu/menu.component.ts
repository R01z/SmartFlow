import { Component, OnInit } from '@angular/core';
import { Team } from '../models/team.model';
import { UserService } from '../user.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css'
})
export class MenuComponent implements OnInit {
  userTeams: Team[] = [];
 
  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getLoggedUser().subscribe(user => {
      const userId = user.userId;
      this.userService.getUserTeams(userId).subscribe(teams => {
        this.userTeams = teams;
      });
    });
  }

}
