export interface IAppMaterialDTO {
  materialId?: number;
  appId?: number;
  title?: string;
  typeCode?: string;
  detail?: string;
  materialURL?: string;
  iconURL?: string;
  clickActionCode?: string;
  createdBy?: string;
  createdDate?: Date;
  updatedBy?: any;
  updatedDate?: any;
  active?: boolean;
}

export class AppMaterialDTO implements IAppMaterialDTO {
  constructor(
    public materialId?: number,
    public appId?: number,
    public title?: string,
    public typeCode?: string,
    public detail?: string,
    public materialURL?: string,
    public iconURL?: string,
    public clickActionCode?: string,
    public createdBy?: string,
    public createdDate?: Date,
    public updatedBy?: any,
    public updatedDate?: any,
    public active?: boolean
  ) {}
}
