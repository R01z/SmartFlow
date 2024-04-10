import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { UserControlComponent } from './user-control/user-control.component';
import { UserRegisterComponent } from './user-register/user-register.component';
import { TeamsComponent } from './teams/teams.component';
import { TeamDetailsComponent } from './team-details/team-details.component';

const routes: Routes = [
  {
    path: '',
    component: LoginComponent
  },
  
  {
  path: 'home',
  component: HomeComponent,
  
  },
  {
  
  path: 'user-control',
  component: UserControlComponent,
  },

  {
    path: 'user/:id', 
    component: UserRegisterComponent,
  },

  {
    path: 'teams',
    component: TeamsComponent
  },

  { path: 'teams/:teamId', 
    component: TeamDetailsComponent 
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
