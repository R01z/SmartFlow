import { Role } from "./role.model";
import { Team } from "./team.model";

export interface User {
    userId: number;
    name: string;
    email: string;
    password: string;
    registrationDate: Date; // Se preferir, pode usar string e fazer a conversão de data no front-end
    roles: Role[]; // Se Role também for um modelo, importe e use aqui
    teams: Team[]; // Adicione o atributo teams para representar a associação entre usuários e times
  }
  