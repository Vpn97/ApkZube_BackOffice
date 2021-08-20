export interface IErrorDTO {
  code?: string;
  message?: string;
  type?: string;
}

export class ErrorDTO implements IErrorDTO {
  constructor(public code?: string, public message?: string, public description?: string) {}
}
