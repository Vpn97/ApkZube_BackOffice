export interface ICompilerDTO {
  code?: string;
  lang?: string;
  input?: string;
  output?: string;
  error?: string;
}

export class CompilerDTO implements ICompilerDTO {
  constructor(public code?: string, public lang?: string, public input?: string, public output?: string, public error?: string) {}
}
