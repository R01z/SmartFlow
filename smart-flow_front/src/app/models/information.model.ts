export interface Information {
    informationId: number;
    name: string;
    description: string;
    uploadDate: Date;
    file?: File;
    link?: string;
    tags: string[];
  }
  