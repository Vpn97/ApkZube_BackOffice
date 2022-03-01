export interface IProgrammingLangDTO {
  langId?: number;
  langName?: string;
  langCode?: string;
  langMode?: string;
  langTheme?: string;
  mimeType?: string;
  iconURL?: string;
  imgURL?: string;
  createdBy?: string | null;
  createdDate?: Date | null;
  updatedBy?: string | null;
  updatedDate?: Date | null;
  active?: boolean;
  totalExamples?: number;
}

export class ProgrammingLangDTO implements IProgrammingLangDTO {
  constructor(
    public programCatId: number | null,
    public langName: string,
    public langCode: string,
    public langMode: string,
    public langTheme: string,
    public mimeType: string,
    public iconURL: string,
    public imgURL: string,
    public createdBy: string | null,
    public createdDate: Date | null,
    public updatedBy: string | null,
    public updatedDate: Date | null
  ) {}
}
