import { AppMst, IAppMst } from './app-mst.model';

export interface IAppMstInfoDTO {
  appMstDTO?: AppMst;
  totalCategory?: number;
  totalProgramCategory?: number;
  totalExample?: number;
  totalTutorial?: number;
  totalMaterial?: number;
  totalBlogs?: number;
  totalNewspost?: number;
}

export class AppMstInfoDTO implements IAppMstInfoDTO {
  constructor(
    public appMstDTO?: AppMst,
    public totalCategory?: number,
    public totalProgramCategory?: number,
    public totalExample?: number,
    public totalTutorial?: number,
    public totalMaterial?: number,
    public totalBlogs?: number,
    public totalNewspost?: number
  ) {}
}
