import { AppMst } from './app-mst.model';
import { TutorialMstDTO } from './tutorial-mst-dto.model';

export interface ITutorialCategoryMstDTO {
  tutCatMstId?: number;
  appId?: number;
  categoryName?: string;
  categoryDesc?: string;
  catType?: string;
  iconURL?: string;
  imgURL?: string;
  createdBy?: number;
  createdDate?: Date;
  updatedBy?: string;
  updatedDate?: Date;
  active?: boolean;
  createdUserName?: string;
  updatedUserName?: string;
  appMst?: AppMst;
  tutorialMstDTOS?: TutorialMstDTO[];
}

export class TutorialCategoryMstDTO implements ITutorialCategoryMstDTO {
  constructor(
    public tutCatMstId: number,
    public appId: number,
    public categoryName: string,
    public categoryDesc: string,
    public catType: string,
    public iconURL: string,
    public imgURL: string,
    public createdBy: number,
    public createdDate: Date,
    public updatedBy: string,
    public updatedDate: Date,
    public active: boolean,
    public createdUserName: string,
    public updatedUserName: string,
    public appMst: AppMst,
    public tutorialMstDTOS: TutorialMstDTO[]
  ) {}
}
