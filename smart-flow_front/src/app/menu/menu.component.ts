import { Component, OnInit } from '@angular/core';
import { Team } from '../models/team.model';
import { UserService } from '../user.service';
import { DropdownService } from '../dropdown.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css'
})
export class MenuComponent implements OnInit {
  userTeams: Team[] = [];
  dropdownOpen: boolean = false;
  isAdmin: boolean = false;
 
  constructor(private userService: UserService, private dropdownService: DropdownService) { }

  ngOnInit(): void {
    this.userService.getLoggedUser().subscribe(user => {
      const userId = user.userId;
      this.userService.getUserTeams(userId).subscribe(teams => {
        this.userTeams = teams;
      });

      this.userService.isUserAdmin(userId).subscribe(isAdmin => {
        this.isAdmin = isAdmin;
      });
    });

    this.dropdownService.isOpen$.subscribe(isOpen => {
      this.dropdownOpen = isOpen;
    });
  }

  toggleDropdown() {
    if (this.dropdownOpen) {
      this.dropdownService.closeDropdown();
    } else {
      this.dropdownService.openDropdown();
    }
  }
  

}
