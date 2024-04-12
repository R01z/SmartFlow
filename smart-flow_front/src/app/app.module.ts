import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatDialogModule } from '@angular/material/dialog';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { UserService } from './user.service';
import { MenuComponent } from './menu/menu.component';
import { UserControlComponent } from './user-control/user-control.component';
import { UserRegisterComponent } from './user-register/user-register.component';
import { TeamsComponent } from './teams/teams.component';
import { TeamsRegisterComponent } from './teams-register/teams-register.component';
import { AddTeamMembersComponent } from './add-team-members/add-team-members.component';
import { TeamDetailsComponent } from './team-details/team-details.component';
import { DropdownService } from './dropdown.service';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    MenuComponent,
    UserControlComponent,
    UserRegisterComponent,
    TeamsComponent,
    TeamsRegisterComponent,
    AddTeamMembersComponent,
    TeamDetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    MatDialogModule
  ],
  exports: [
    UserRegisterComponent
  ],
  providers: [UserService, DropdownService],
  bootstrap: [AppComponent]
})
export class AppModule { }
