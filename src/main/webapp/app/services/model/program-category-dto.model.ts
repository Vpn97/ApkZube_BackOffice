export interface IProgramCategoryDTO {
  programCatId?: number | null;
  appId?: number;
  catName?: string;
  catDescription?: string;
  catIconURL?: string;
  createdBy?: string | null;
  createdDate?: Date | null;
  updatedBy?: string | null;
  updatedDate?: Date | null;
  active?: boolean;
  totalExamples?: number;
}

export class ProgramCategoryDTO implements IProgramCategoryDTO {
  constructor(
    public programCatId: number | null,
    public appId: number,
    public catName: string,
    public catDescription: string,
    public catIconURL: string,
    public createdBy: string | null,
    public createdDate: Date | null,
    public updatedBy: string | null,
    public updatedDate: Date | null,
    public active: boolean,
    public totalExamples: number
  ) {}
}
