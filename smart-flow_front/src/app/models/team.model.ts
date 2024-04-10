import { User } from "./user.model";

export interface Team {
    teamId: number;
    name: string;
    members: User[]; // Considerando que User é outro modelo que representa um usuário
    description: string;
  }
  