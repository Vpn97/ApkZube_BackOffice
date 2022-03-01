export interface IProgramMstDTO {
  programId?: number | null;
  appId?: number | null;
  catId?: number | null;
  title?: string | null;
  programText?: string;
  outputText?: string;
  programInput?: string | null;
  explainText?: string;
  programBy?: string;
  programURL?: string;
  programIconURL?: string | null;

  createdBy?: string | null;
  createdDate?: Date | null;
  updatedBy?: string | null;
  updatedDate?: Date | null;
}

export class ProgramMstDTO implements IProgramMstDTO {
  constructor(
    public programId: number | null,
    public appId: number | null,
    public catId: number | null,
    public title: string,
    public programText: string,
    public outputText: string,
    public programInput: string | null,
    public explainText: string,
    public programBy: string,
    public programURL: string,
    public programIconURL: string | null,
    public createdBy: string | null,
    public createdDate: Date | null,
    public updatedBy: string | null,
    public updatedDate: Date | null
  ) {}
}
