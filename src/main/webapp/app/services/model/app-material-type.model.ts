export interface IAppMaterialType {
  typeId?: number;
  typeName?: string;
  typeCode?: string;
  createdDate?: Date;
}

export class AppMaterialType implements IAppMaterialType {
  constructor(public typeId?: number, public typeName?: string, public typeCode?: string, public createdDate?: Date) {}
}
