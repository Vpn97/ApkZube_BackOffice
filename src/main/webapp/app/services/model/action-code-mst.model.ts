export interface IActionCodeMst {
  actionCodeId?: number;
  actionCode?: string;
  actionDesc?: string;
  createdBy?: number | null;
  createdDate?: Date | null;
  updatedBy?: string | null;
  updatedDate?: Date | null;
}

export class ActionCodeMst implements IActionCodeMst {
  constructor(
    public actionCodeId?: number,
    public actionCode?: string,
    public actionDesc?: string,
    public createdBy?: number | null,
    public createdDate?: Date | null,
    public updatedBy?: string | null,
    public updatedDate?: Date | null
  ) {}
}
