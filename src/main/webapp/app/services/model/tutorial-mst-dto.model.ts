export interface ITutorialMstDTO {
  tutMstId?: number | null;
  tutCatMstId?: number;
  title?: string;
  description?: string;
  iconURL?: string;
  imgURL?: string;
  createdBy?: string;
  createdDate?: Date | null;
  updatedBy?: string;
  updatedDate?: Date | null;
  active?: boolean;
}

export class TutorialMstDTO implements ITutorialMstDTO {
  constructor(
    public tutMstId: number | null,
    public tutCatMstId: number,
    public title: string,
    public description: string,
    public iconURL: string,
    public imgURL: string,
    public createdBy: string,
    public createdDate: Date | null,
    public updatedBy: string,
    public updatedDate: Date | null,
    public active: boolean
  ) {}
}
