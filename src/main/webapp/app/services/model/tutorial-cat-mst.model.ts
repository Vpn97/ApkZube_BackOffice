export interface ITutorialCategoryMst {
  tutCatMstId?: number | null;
  appId?: number;
  categoryName?: string;
  categoryDesc?: string;
  catType?: string;
  iconURL?: string;
  imgURL?: string;
  createdBy?: number | null;
  createdDate?: Date | null;
  updatedBy?: string | null;
  updatedDate?: Date | null;
  active?: boolean;
}

export class TutorialCategoryMst implements ITutorialCategoryMst {
  constructor(
    public tutCatMstId: number | null,
    public appId: number,
    public categoryName: string,
    public categoryDesc: string,
    public catType: string,
    public iconURL: string,
    public imgURL: string,
    public createdBy: number | null,
    public createdDate: Date | null,
    public updatedBy: string | null,
    public updatedDate: Date | null,
    public active: boolean
  ) {}
}
