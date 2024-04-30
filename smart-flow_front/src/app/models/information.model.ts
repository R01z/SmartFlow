export interface Information {
    informationId: number;
    name: string;
    description: string;
    uploadDate: Date;
    file?: string;
    link?: string;
    tags: string[];
  }
  